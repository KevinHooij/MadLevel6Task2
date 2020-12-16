package com.example.madlevel6task2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()

    /**
     * This property points direct to the LiveData in the repository, that value
     * get's updated when user clicks FAB. This happens through the getTriviaNumber() in this class :)
     */
    val movies = movieRepository.movies

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from view for error showing
     * Encapsulation :)
     */
    val errorText: LiveData<String>
        get() = _errorText

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    val movieId = MutableLiveData<Int>()


    fun setMovieId(id: Int){
        movieId.value = id
    }


    fun getList(year: Int) {
        viewModelScope.launch {
            try {
                //the triviaRepository sets it's own livedata property
                //our own trivia LiveData property points to te one in that repository
                movieRepository.getMovies(year)
            } catch (error: MovieRepository.MovieError) {
                _errorText.value = error.message
                Log.e("movie error", error.cause.toString())
            }
        }
    }

    fun getDetails(id: Int){
        viewModelScope.launch{
            try{
                movieRepository.getDetails(id)
            }
            catch(error: MovieRepository.MovieError){
                _errorText.value = error.message
                Log.e("movie error", error.cause.toString())
            }
        }
    }
}
