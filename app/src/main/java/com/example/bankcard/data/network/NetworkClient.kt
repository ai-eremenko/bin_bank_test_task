package com.example.bankcard.data.network

import com.example.bankcard.data.dto.RequestDto
import okhttp3.Response

interface NetworkClient {
    suspend fun doRequest(dto: RequestDto): Response
}