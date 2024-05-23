package com.example.moviesurfer.ui.activities.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesurfer.R
import com.example.moviesurfer.databinding.CoroselHolderBinding
import com.example.moviesurfer.modals.Movie

class CoroselAdapter(private val context: Context) : RecyclerView.Adapter<CoroselAdapter.CoroselViewHolder>() {

    private var topMovie : MutableList<Movie> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoroselAdapter.CoroselViewHolder {
        Log.d("adapter", "initialized")
        return CoroselViewHolder(CoroselHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}

    override fun onBindViewHolder(holder: CoroselViewHolder, position: Int) {
        // Bind data to your custom view
        if(topMovie.isNotEmpty()) {
            holder.bind(topMovie[position])
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun saveData(movies: List<Movie>?) {
        if (movies != null) {
            topMovie.clear()
            topMovie.addAll(movies)
        }
        notifyDataSetChanged()
    }

    inner class CoroselViewHolder(val binding: CoroselHolderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            Glide.with(context)
                .load(movie.image)
                .placeholder(R.drawable.img_user)
                .error(R.drawable.img_user)
                .into(binding.imageMovie)
        }
    }

}