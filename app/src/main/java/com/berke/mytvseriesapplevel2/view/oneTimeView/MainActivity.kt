package com.berke.mytvseriesapplevel2.view.oneTimeView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.berke.mytvseriesapplevel2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}