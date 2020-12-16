package com.example.madlevel6task2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.withTimeout

class MovieRepository{

    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movies: MutableLiveData<MovieApiService.Response> = MutableLiveData()
    private val _movie: MutableLiveData<MovieItem> = MutableLiveData()
    private val movie: LiveData<MovieItem>
        get() = _movie

    val movies: LiveData<MovieApiService.Response>
        get() = _movies

    /**
     * suspend function that calls a suspend function from the numbersApi call
     */
    suspend fun getMovies(year: Int)  {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                movieApiService.getList(year)
            }

            _movies.value = result
        } catch (error: Throwable) {
            throw MovieError("ERROR ERROR ERROR", error)
        }
    }


    suspend fun getDetails(id: Int){
        try{
            val result =
                movieApiService.getDetails(id)
            _movie.value = result
        }
        catch(error: Throwable){
            throw MovieError("ERROR", error)
        }
    }

    class MovieError(message: String, cause: Throwable) : Throwable(message, cause)

}


