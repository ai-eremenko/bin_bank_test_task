package com.example.bankcard.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class BankCardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.take(19)
        val formatted = buildString {
            for (i in digits.indices) {
                append(digits[i])
                when (i) {
                    3, 7, 11, 15 -> if (i != digits.lastIndex) append(" ")
                }
            }
        }

        return TransformedText(AnnotatedString(formatted), object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 0 -> 0
                    offset <= 4 -> offset
                    offset <= 8 -> offset + 1
                    offset <= 12 -> offset + 2
                    offset <= 16 -> offset + 3
                    offset <= 19 -> offset + 4
                    else -> 23
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 0 -> 0
                    offset <= 4 -> offset
                    offset <= 9 -> offset - 1
                    offset <= 14 -> offset - 2
                    offset <= 19 -> offset - 3
                    offset <= 23 -> offset - 4
                    else -> 19
                }
            }
        })
    }
}
