package com.example.movietime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter(private var movieList: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun updateData(movie: Movie, index: Int){
        movieList[index] = movie
        notifyItemChanged(index)
    }

    fun insertData(movie: Movie, index: Int){
        movieList.add(index, movie)
        notifyItemInserted(index)
    }

    fun deleteData(index: Int){
        movieList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addData(movie: Movie){
        movieList.add(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun binding(movie: Movie){
            itemView.tv_item_name.text = movie.name
            itemView.tv_item_detail.text = movie.popularity.toString()

            //image
            Glide.with(itemView)
                .load(movie.imagePath)
                .apply(RequestOptions.circleCropTransform().override(60,60))
                .into(itemView.iv_logo)

            //click listener
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Movie Time ${movie.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}