package fr.hamtec.nenuphar

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieDto>>(emptyList())
    val movies: StateFlow<List<MovieDto>> = _movies

    private val dataSource = MovieRemoteDataSource()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        dataSource.fetchPopularMovies(
            onSuccess = { movieList ->
                Log.d("MovieRemoteDataSource", "Films reçus : ${movieList.size}")
                _movies.value = movieList
            },
            onError = { error ->
                Log.e("MovieRemoteDataSource", "Erreur réseau : ${error.message}")
            }
        )
    }
}