package fr.hamtec.nenuphar

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MovieRemoteDataSource {

    private val apiKey = "62d96ef75676fba47c537de195f1b3c6"

    fun fetchPopularMovies(
        onSuccess: (List<MovieDto>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey&language=fr-FR&page=1"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val results = response.getJSONArray("results")
                val movies = mutableListOf<MovieDto>()
                for (i in 0 until results.length()) {
                    val obj = results.getJSONObject(i)
                    movies.add(
                        MovieDto(
                            id = obj.getInt("id"),
                            title = obj.getString("title"),
                            overview = obj.getString("overview"),
                            poster_path = obj.getString("poster_path")
                        )
                    )
                }
                onSuccess(movies)
            },
            { error ->
                onError(error)
            }
        )

        VolleyClient.getQueue().add(request)
    }
}