package com.example.mymusicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_track_info.*

class TrackInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_info)

        val bundle: Bundle? = intent.extras
        val track = bundle!!.getString("Track")
        val genre = bundle!!.getString("Genre")

        tvTrackTitle.text=track
        tvTrackGenre.text=genre








    }


}
