package com.example.desde0_2

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object HttpUtils {
    //carles te amo
    @Throws(IOException::class)
    fun get(dataUrl: String?): String? {
        val url = URL(dataUrl)
        var response: String? = null

        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in`: InputStream = BufferedInputStream(urlConnection.inputStream)
            response = readStream(`in`)
        } finally {
            urlConnection.disconnect()
        }
        return response
    }

    @Throws(IOException::class)
    private fun readStream(`in`: InputStream): String {
        val `is` = InputStreamReader(`in`)
        val rd = BufferedReader(`is`)
        var line: String?
        val response = StringBuilder()
        while ((rd.readLine().also { line = it }) != null) {
            response.append(line)
            response.append('\r')
        }
        rd.close()
        return response.toString()
    }
}