package com.example

import androidx.appcompat.app.AppCompatActivity
import com.lagradost.cloudstream3.TvType
import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.SearchResponse

class ExampleProvider(val plugin: TestPlugin) : MainAPI() { // all providers must be an intstance of MainAPI
    override var mainUrl = "https://example.com/" 
    override var name = "Example provider"
    override val supportedTypes = setOf(TvType.Movie)

    override var lang = "en"

    // enable this when your provider has a main page
    override val hasMainPage = true

    // this function gets called when you search for something
    override suspend fun search(query: String): List<SearchResponse> {
        return listOf<SearchResponse>()
    }
}
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class VietsubAnimeProvider : MainAPI() {
    override val mainUrl = "https://animevietsub.app"
    override val name = "VietsubAnime"
    override val lang = "vi"

    fun fetchAnimeData(): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$mainUrl/api/anime")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
            .build()

        return try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string() ?: ""
            } else {
                "Error: ${response.code}"
            }
        } catch (e: Exception) {
            "Exception: ${e.message}"
        }
    }

    // Additional configurations or functions as needed
}
