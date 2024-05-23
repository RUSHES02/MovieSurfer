package com.example.moviesurfer.ui.fragments.movies

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesurfer.R
import com.example.moviesurfer.databinding.MovieHolderBinding
import com.example.moviesurfer.modals.Movie

class MovieAdapter(private val context: Context): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val differCallBack = object: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }


    private val differ = AsyncListDiffer(this,differCallBack)

    fun saveData( dataResponse: List<Movie>){
        differ.submitList(dataResponse)
        Log.d("in adapter", "${differ.currentList}")

    }


    inner class MovieViewHolder(val binding : MovieHolderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.imageViewMovie.tag = movie.thumbnail
            binding.imageViewMovie.setImageDrawable(null)

            Glide.with(context)
                .load(movie.image)
                .placeholder(R.drawable.img_user)
                .error(R.drawable.img_user)
                .into(binding.imageViewMovie)

            binding.textViewTittle.text = movie.title
            binding.textViewYear.text = movie.year.toString()
            binding.textViewDescription.text = movie.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("adapter", "initialized")
       return MovieViewHolder(MovieHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }
}