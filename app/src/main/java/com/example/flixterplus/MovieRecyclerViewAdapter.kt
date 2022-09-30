package com.example.flixterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * [RecyclerView.Adapter] that can display a [Movie] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MovieRecyclerViewAdapter(
    private val MoviesList: List<Movie>,
    private val mListener: OnListFragmentInteractionListener? // Can't seem to remove this line without breaking the app, despite it not being used anywhere. Probably a syntax issue I'm overlooking.
)
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movie_title) as TextView
        val mMovieSynopsis: TextView = mView.findViewById<View>(R.id.movie_synopsis) as TextView
        val mMoviePoster: ImageView = mView.findViewById<View>(R.id.movie_poster) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString()
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = MoviesList[position]
        val theURL = "https://image.tmdb.org/t/p/w500"
        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieSynopsis.text = movie.overview

        Glide.with(holder.mView)
            .load(theURL + (movie.moviePosterUrl))
            .centerInside()
            .into(holder.mMoviePoster)

    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return MoviesList.size
    }
}