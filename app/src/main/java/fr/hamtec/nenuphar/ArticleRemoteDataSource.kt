package fr.hamtec.nenuphar

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest

class ArticleRemoteDataSource {

    fun fetchArticles(
        onSuccess: (List<ArticleDto>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val url = "https://example.com/articles" // remplace par ton vrai endpoint

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val articles = mutableListOf<ArticleDto>()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    articles.add(
                        ArticleDto(
                            id = obj.getInt("id"),
                            title = obj.getString("title"),
                            body = obj.getString("body")
                        )
                    )
                }
                onSuccess(articles)
            },
            { error ->
                onError(error)
            }
        )

        VolleyClient.getQueue().add(request)
    }
}