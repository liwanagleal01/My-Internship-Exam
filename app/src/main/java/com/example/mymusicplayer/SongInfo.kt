package com.example.mymusicplayer

class SongInfo {


    var artworkUrl100: String?=null
    var artworkUrl60: String?=null
    var description: String?=null
    var longDescription: String?=null
    var previewUrl: String?=null
    var primaryGenreName: String?=null
    var shortDescription: String?=null
    var trackName: String?=null
    var trackPrice: String?=null



    constructor(artworkUrl100:String,
                artworkUrl60:String,
                description:String,
                longDescription:String,
                previewUrl:String,
                primaryGenreName:String,
                shortDescription:String,
                trackName:String,
                trackPrice:String){

            this.artworkUrl100=artworkUrl100
            this.artworkUrl60=artworkUrl60
            this.description=description
            this.longDescription=longDescription
            this.previewUrl=previewUrl
            this.primaryGenreName=primaryGenreName
            this.shortDescription=shortDescription
            this.trackName=trackName
            this.trackPrice=trackPrice


    }

}