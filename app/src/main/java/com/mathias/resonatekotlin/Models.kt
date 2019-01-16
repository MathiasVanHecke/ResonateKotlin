package com.mathias.resonatekotlin

import android.media.Image

class SpotifyUser(val country: String = "", val display_name: String= "", val email: String = "", val href: String = "",
            val id: String = "", val birthdate: String = "", val beschrijving: String="", val urlPf: String="", val Artists : List<Artist>)


class SpotifyData(val items: List<Item>){
    class Item(val genres: List<String>, val href: String, val images: List<Image>, val name:String)
    class Image(val height:Int,val url:String)
}

class Genre(val UserId: String, val GenreName:String)


class Artist(val ArtistId: Int, val UserId: String, val ArtistName: String, val HrefSpotify: String, val UrlPf: String)



