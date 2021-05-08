package com.example.movietime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movietime.Fragment.MovieFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_fragmenteditmovie, MovieFragment())
            .commit()
    }
}