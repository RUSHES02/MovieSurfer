package com.example.moviesurfer.ui.fragments.series

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
import com.example.moviesurfer.modals.Series

class SeriesAdapter(private val context: Context): RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private val differCallBack = object: DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }


    private val differ = AsyncListDiffer(this,differCallBack)

    fun saveData( dataResponse: List<Series>){
        differ.submitList(dataResponse)
        Log.d("in adapter", "${differ.currentList}")

    }


    inner class SeriesViewHolder(val binding : MovieHolderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(Series: Series) {
            binding.imageViewMovie.tag = Series.thumbnail
            binding.imageViewMovie.setImageDrawable(null)

            Glide.with(context)
                .load(Series.image)
                .placeholder(R.drawable.img_user)
                .error(R.drawable.img_user)
                .into(binding.imageViewMovie)

            binding.textViewTittle.text = Series.title
            binding.textViewYear.text = Series.year.toString()
            binding.textViewDescription.text = Series.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        Log.d("adapter", "initialized")
       return SeriesViewHolder(MovieHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val Series = differ.currentList[position]
        holder.bind(Series)
    }
}