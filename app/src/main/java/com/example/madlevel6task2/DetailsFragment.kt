package com.example.madlevel6task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddMovieResults()
        observeMovie()
    }

    private fun observeAddMovieResults(){
        arguments?.let{
            val id = it.getString(MOVIE_BUNDLE_KEY)
            if (id != null){
                val movieId = id.toInt()
                viewModel.getDetails(movieId)
            }
        }
    }

    private fun observeMovie(){
        viewModel.movie.observe(viewLifecycleOwner, {
            val imageSource = "https://image.tmdb.org/t/p/original/%s"
            Glide.with(this).load(String.format(imageSource, it.backdrop)).into(backdropImage)
            Glide.with(this).load(String.format(imageSource, it.poster)).into(imgMovieBanner)

            txtMovieTitle.text = it.title
            movieDate.text = it.release
            txtViewRating.text = it.rating.toString()
            txtMovieDescription.text = it.overview
        }


        )
    }
}