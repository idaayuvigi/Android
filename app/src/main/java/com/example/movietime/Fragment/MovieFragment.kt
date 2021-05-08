package com.example.movietime.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietime.Movie
import com.example.movietime.MovieAdapter
import com.example.movietime.R
import com.example.movietime.Utils
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter(Utils.movies)
        movieAdapter.notifyDataSetChanged()
        rv_item.adapter = movieAdapter
        rv_item.setHasFixedSize(true)
        rv_item.layoutManager = LinearLayoutManager(context)

        btn_update.setOnClickListener{
            val index = getIndex()
            if (isNotOutRange(index)) movieAdapter.updateData(getForm(), index)
            else noticeOutRange()
            clearForm()
        }

        btn_insert.setOnClickListener{
            val index = getIndex()
            if (isNotOutRange(index)) movieAdapter.insertData(getForm(), index)
            else noticeOutRange()
            clearForm()
        }

        btn_delete.setOnClickListener{
            val index = getIndex()
            if (isNotOutRange(index)) movieAdapter.deleteData(index)
            else noticeOutRange()
            clearForm()
        }

        btn_tambah.setOnClickListener{
            movieAdapter.addData(getForm())
            clearForm()
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
                MovieFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    private fun getForm(): Movie{
        val name = ti_name.text.toString()
        val popularity = ti_popularity.text.toString()
        val imagePath = (0 until Utils.images.size).random()

        return Movie(name, popularity.toDouble(), Utils.images[imagePath])
    }

    private fun getIndex(): Int{
        val index = ti_row.text.toString()
        return if(index.isNotEmpty()) index.toInt() else 0
    }

    private fun clearForm() {
        ti_name.setText("")
        ti_popularity.setText("")
        ti_row.setText("")
    }

    private fun isNotOutRange(index: Int): Boolean = index < Utils.movies.size

    private fun noticeOutRange(){
        Toast.makeText(context, "Out of range", Toast.LENGTH_LONG).show()
    }
}