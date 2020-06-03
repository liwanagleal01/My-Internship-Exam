package com.example.mymusicplayer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_ticket.*
import kotlinx.android.synthetic.main.item_ticket.view.*
import kotlinx.android.synthetic.main.item_ticket.view.btnPlay
import java.lang.Exception

abstract class MainActivity : AppCompatActivity() {


    var songList = ArrayList<SongInfo>()
    var adapter:myTrackAdapter?=null
    var mp:MediaPlayer?=null // media player instance



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadURLOnline()
        adapter=myTrackAdapter(songList)
        lvMusic.adapter=adapter

        var myTrack = mySongTrack()
        myTrack.start()



        ivSeeMore.setOnClickListener() {
            val intent = Intent(this, TrackInfo::class.java)
            intent.putExtra("Track", tvCurrentTrack.text)
            intent.putExtra("Genre", tvCurrentGenre.text)

   //         intent.putExtra("Image", forCurrentTrack[0])
            startActivity(intent)
        }

    }

    fun loadURLOnline(){
        songList.add(SongInfo("https://is4-ssl.mzstatic.com/image/thumb/Music118/v4/c8/9a/6f/c89a6ff4-5e67-ddd3-db24-2fce7868ba59/source/100x100bb.jpg",
            "https://is4-ssl.mzstatic.com/image/thumb/Music118/v4/c8/9a/6f/c89a6ff4-5e67-ddd3-db24-2fce7868ba59/source/60x60bb.jpg",
            "",
            "",
            "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview128/v4/3e/d1/6d/3ed16dee-133e-1eb7-e192-93ad438b80c4/mzaf_6012786526265473433.plus.aac.p.m4a",
            "Pop",
            "",
            "Star",
            "$1.29"))

        songList.add(SongInfo("https://is3-ssl.mzstatic.com/image/thumb/Video127/v4/d8/1e/03/d81e03d9-3d61-ce3d-dd05-ad511824a5b2/source/100x100bb.jpg",
            "https://is3-ssl.mzstatic.com/image/thumb/Video127/v4/d8/1e/03/d81e03d9-3d61-ce3d-dd05-ad511824a5b2/source/60x60bb.jpg",
            "When the crew of the Enterprise is called back home, they find an unstoppable force of terror from...",
            "J.J. Abrams STAR TREK INTO DARKNESS is the best-reviewed blockbuster of the year. When a ruthless mastermind known as Khan (Benedict Cumberbatch) declares a one-man war on the Federation, Captain Kirk (Chris Pine), Spock (Zachary Quinto), and the daring crew of the U.S.S. Enterprise will embark on the greatest manhunt in history. It will take all of their skills and teamwork to defend Earth and eliminate Khan’s threat in this “sleek, thrilling epic.” (Owen Gleiberman, Entertainment Weekly)",
            "https://video-ssl.itunes.apple.com/itunes-assets/Video127/v4/d1/d4/7a/d1d47a23-7ed3-f51d-ded1-12ab6ea3d5c6/mzvf_6689004818574290975.640x354.h264lc.U.p.m4v",
            "Sci-Fi & Fantasy",
            "When the crew of the Enterprise is called back home, they find an unstoppable force of terror from",
            "Star Trek Into Darkness",
            "$12.99"))

        songList.add(SongInfo("https://is1-ssl.mzstatic.com/image/thumb/Music/v4/90/02/da/9002daa3-7fa1-8a57-c27f-23b0d5666b6d/source/100x100bb.jpg",
            "https://is1-ssl.mzstatic.com/image/thumb/Music/v4/90/02/da/9002daa3-7fa1-8a57-c27f-23b0d5666b6d/source/60x60bb.jpg",
            "",
            "",
            "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview19/v4/ac/7a/c9/ac7ac977-0e4f-ad72-5e96-2bdb4e2da5a4/mzaf_2329193150417054536.plus.aac.p.m4a",
            "K-Pop",
            "",
            "Alone",
            "$0.99"))

        songList.add(SongInfo("https://is3-ssl.mzstatic.com/image/thumb/Music/v4/56/4d/74/564d74d2-3564-886f-1af3-ea754413391c/source/100x100bb.jpg",
            "https://is3-ssl.mzstatic.com/image/thumb/Music/v4/56/4d/74/564d74d2-3564-886f-1af3-ea754413391c/source/60x60bb.jpg",
            "",
            "",
            "https://audio-ssl.itunes.apple.com/itunes-assets/Music/v4/28/39/fc/2839fce4-d1d2-6f05-1454-14d6a8d20097/mzaf_541644405070642904.plus.aac.p.m4a",
            "Alternative",
            "",
            "A Sky Full of Stars",
            "$1.29"))

    }

    inner class myTrackAdapter:BaseAdapter{
        var songList = ArrayList<SongInfo>()
        constructor(songList: ArrayList<SongInfo>):super(){
            this.songList=songList
        }



      override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val myView = layoutInflater.inflate(R.layout.item_ticket,null)
            val track = this.songList[position]


            myView.tvTitle.text=track.trackName
            myView.tvGenre.text=track.primaryGenreName
            myView.tvPrice.text=track.trackPrice
            Picasso.get().load(track.artworkUrl60).into(myView.ivThumbnail)



            myView.btnPlay.setOnClickListener(View.OnClickListener {
                mp= MediaPlayer()

                Picasso.get().load(track.artworkUrl100).into(ivCurrentImage)
                tvCurrentTrack.text=track.trackName
                tvCurrentGenre.text=track.primaryGenreName


                mp!!.setDataSource(track.previewUrl)



            /*    mp!!.stop()
                myView.btnPlay.text = "Play"



                if(myView.btnPlay.text=="Play"){
                    try {

                        mp!!.prepare()
                        mp!!.start()
                        myView.btnPlay.text = "Stop"

                        trackProgress.max = mp!!.duration
                    }catch (ex:Exception){}


                }  */

                when (myView.btnPlay.text) {
                    "Play" -> {
                        mp!!.prepare()
                        mp!!.start()
                        myView.btnPlay.text = "Stop"
                        trackProgress.max = mp!!.duration
                    }
                    "Stop" -> {

                            mp!!.pause()
                            mp!!.reset()
                            myView.btnPlay.text = "Play"

                    }
                }


            })

            return  myView
        }

        override fun getItem(position: Int): Any {
            return songList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return songList.size
        }


    }

    inner class mySongTrack():Thread(){


        override fun run() {
            while (true){

                try {
                    Thread.sleep(1000)
                }catch (ex:Exception){}
                runOnUiThread {
                    if (mp!=null) {
                        trackProgress.progress = mp!!.currentPosition
                    }

                }
            }
        }

    }


}
