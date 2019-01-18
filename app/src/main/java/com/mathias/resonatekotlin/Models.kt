package com.mathias.resonatekotlin

import android.media.Image
import com.google.gson.annotations.SerializedName

class SpotifyUser(val country: String = "", val display_name: String= "", val email: String = "", val href: String = "",
            val id: String = "", val birthdate: String = "", val beschrijving: String="", var urlPf: String="",
                  var Artists : MutableList<Artist?> , var Genres: MutableList<Genre?> , var images: MutableList<SpotifyData.Image>)


class SpotifyData(val items: List<Item>){
    class Item(val genres: List<String>, val href: String, val images: List<Image>, val name:String)
    class Image(val height:Int,val url:String)
}

class Genre(val UserId: String, val GenreName:String)


class Artist( userId: String, val artistName: String, val hrefSpotify: String, val urlPf: String)



