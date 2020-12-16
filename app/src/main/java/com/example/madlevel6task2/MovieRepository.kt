package com.example.madlevel6task2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.withTimeout

class MovieRepository{

    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movies: MutableLiveData<MovieApiService.Response> = MutableLiveData()

    val movie: LiveData<MovieApiService.Response>
        get() = _movies

    /**
     * suspend function that calls a suspend function from the numbersApi call
     */
    suspend fun getMovies()  {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                movieApiService.getList()
            }

            _movies.value = result
        } catch (error: Throwable) {
            throw MovieError("ERROR ERROR ERROR", error)
        }
    }

    class MovieError(message: String, cause: Throwable) : Throwable(message, cause)

}


