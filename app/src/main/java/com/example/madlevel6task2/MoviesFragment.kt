package com.example.madlevel6task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

const val MOVIE_BUNDLE_KEY = "MOVIE_BUNDLE_KEY"

class MoviesFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    private lateinit var movieAdapter: MovieAdapter
    private val movies = arrayListOf<MovieItem>()

    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(movies, ::OnPosterClick)
        rvMovies.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieAdapter

        btnSubmit.setOnClickListener{
            addMovies()
        }
    }

    private fun OnPosterClick(movieItem: MovieItem){
      val bundle = bundleOf(Pair(MOVIE_BUNDLE_KEY, movieItem.id))

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

    }

    private fun addMovies(){
        val year = etYear.text.toString().toInt()

        viewModel.getList(year)

        viewModel.movies.observe(viewLifecycleOwner, {
            movies.clear()
            val list : List<MovieItem> = it.results
            movies.addAll(list)
            movieAdapter.notifyDataSetChanged()
        }
        )
    }


}