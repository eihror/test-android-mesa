package com.eihror.mesatestigor.providers.api.json_adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

object DateJsonAdapter {
    private const val datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    @FromJson
    fun fromJson(string: String): Date {
        val dateformat = SimpleDateFormat(datePattern)
        dateformat.timeZone = TimeZone.getTimeZone("UTC")
        return dateformat.parse(string)
    }

    @ToJson
    fun toJson(value: Date): String {
        val dateformat = SimpleDateFormat(datePattern)
        dateformat.timeZone = TimeZone.getTimeZone("UTC")
        return dateformat.format(value)
    }
}