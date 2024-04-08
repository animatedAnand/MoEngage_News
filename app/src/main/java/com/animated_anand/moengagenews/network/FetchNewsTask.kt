package com.animated_anand.moengagenews.network

import android.os.AsyncTask
import com.animated_anand.moengagenews.utils.Constants
import com.animated_anand.moengagenews.model.News
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FetchNewsTask(private val listener: OnFetchCompleteListener) :
    AsyncTask<Void, Void, List<News>>() {

    interface OnFetchCompleteListener {
        fun onFetchComplete(newsItems: List<News>?)
    }

    override fun doInBackground(vararg params: Void?): List<News>? {
        var connection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        var result: String? = null
        val newsItems = mutableListOf<News>()

        try {
            // Create URL connection
            val url = URL(Constants.API_URL)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            // Read and parse JSON response
            val inputStream = connection.inputStream
            val buffer = StringBuffer()
            reader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = null
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
            result = buffer.toString()

            // Parse JSON response and create NewsItem objects
            val jsonObject = JSONObject(result)
            val articlesArray = jsonObject.getJSONArray("articles")

            for (i in 0 until articlesArray.length()) {
                val articleObject = articlesArray.getJSONObject(i)
                val newsItem = News(
                    sourceId = articleObject.getJSONObject("source").getString("id"),
                    sourceName = articleObject.getJSONObject("source").getString("name"),
                    author = articleObject.optString("author", null),
                    title = articleObject.getString("title"),
                    description = articleObject.optString("description", null),
                    url = articleObject.getString("url"),
                    urlToImage = articleObject.optString("urlToImage", null),
                    publishedAt = articleObject.getString("publishedAt"),
                    content = articleObject.optString("content", null),
                    isBookmarked = false // Default to false
                )
                newsItems.add(newsItem)
            }

        } catch (e: Exception) {
            // Handle any exceptions
            e.printStackTrace()
        } finally {
            // Disconnect and close resources
            connection?.disconnect()
            try {
                reader?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return newsItems
    }

    override fun onPostExecute(result: List<News>?) {
        // Notify listener with fetched news items
        listener.onFetchComplete(result)
    }
}
