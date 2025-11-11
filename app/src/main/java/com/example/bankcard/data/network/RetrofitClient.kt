package com.example.bankcard.data.network

import com.example.bankcard.data.dto.RequestDto
import com.example.bankcard.utils.NetworkManager
import okhttp3.Response
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RetrofitClient(
    private val api: BinApiService,
    private val networkManager: NetworkManager
) : NetworkClient {

    override suspend fun doRequest(dto: RequestDto): Response {
        if (!networkManager.isConnected()) {
            return Response().apply { status = ResponseStatus.NO_INTERNET }
        }

        return try {
            when (dto) {
                is RequestDto.BinRequest -> handleBinRequest(dto)
            }
        } catch (e: SocketTimeoutException) {
            Response().apply {
                status = ResponseStatus.NO_INTERNET
                errorMessage = "Timeout: ${e.message}"
            }
        } catch (e: HttpException) {
            handleHttpException(e)
        } catch (e: Exception) {
            Response().apply {
                status = ResponseStatus.UNKNOWN_ERROR
                errorMessage = "Unexpected error: ${e.message}"
            }
        }
    }

    private suspend fun handleBinRequest(dto: RequestDto.BinRequest): BinResponse {
        val binApiResponse = api.getBinInfo(dto.bin)
        val binInfo = mapToBinInfo(dto.bin, binApiResponse)

        return BinResponse(binInfo).apply {
            status = ResponseStatus.SUCCESS
        }
    }

    private fun mapToBinInfo(bin: String, apiResponse: BinApiResponse): BinInfo {
        return BinInfo(
            bin = bin,
            scheme = apiResponse.scheme,
            type = apiResponse.type,
            brand = apiResponse.brand,
            prepaid = apiResponse.prepaid,
            countryName = apiResponse.country?.name,
            countryAlpha2 = apiResponse.country?.alpha2,
            countryEmoji = apiResponse.country?.emoji,
            countryCurrency = apiResponse.country?.currency,
            countryLatitude = apiResponse.country?.latitude,
            countryLongitude = apiResponse.country?.longitude,
            bankName = apiResponse.bank?.name,
            bankUrl = apiResponse.bank?.url,
            bankPhone = apiResponse.bank?.phone,
            bankCity = apiResponse.bank?.city,
            numberLength = apiResponse.number?.length,
            numberLuhn = apiResponse.number?.luhn
        )
    }

    private fun handleHttpException(e: HttpException): Response {
        return when (e.code()) {
            HTTP_NOT_FOUND -> Response().apply {
                status = ResponseStatus.NOT_FOUND
                errorMessage = "BIN not found"
            }
            HTTP_TOO_MANY_REQUESTS -> Response().apply {
                status = ResponseStatus.TOO_MANY_REQUESTS
                errorMessage = "Rate limit exceeded. Try again later."
            }
            HTTP_SERVER_ERROR -> Response().apply {
                status = ResponseStatus.SERVER_ERROR
                errorMessage = "Server error"
            }
            else -> Response().apply {
                status = ResponseStatus.UNKNOWN_ERROR
                errorMessage = "HTTP error: ${e.code()} - ${e.message()}"
            }
        }
    }

    companion object {
        const val HTTP_NOT_FOUND = 404
        const val HTTP_TOO_MANY_REQUESTS = 429
        const val HTTP_SERVER_ERROR = 500
    }
}
enum class ResponseStatus {
    SUCCESS,
    NO_INTERNET,
    NOT_FOUND,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    UNKNOWN_ERROR
}
