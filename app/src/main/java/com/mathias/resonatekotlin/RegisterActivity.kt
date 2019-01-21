package com.mathias.resonatekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import com.google.gson.GsonBuilder
import com.mathias.resonatekotlin.Adapters.GenreAdapter
import com.mathias.resonatekotlin.Adapters.RegisterArtistsAdapter
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import java.io.IOException
import kotlin.math.log
import com.google.gson.Gson



class RegisterActivity : AppCompatActivity() {
    var Genres = mutableListOf<Genre>()
    var Artists = mutableListOf<Artist>()
    var Images = mutableListOf<SpotifyData.Image>()

    var user : SpotifyUser = SpotifyUser(artists = Artists, genres = Genres, images = Images)

    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        rvRegister_artist.layoutManager = LinearLayoutManager(this)

        fetchDataFromSpotify()

        btnRegister_register.setOnClickListener{
            //TODO Uncommnt dit in production
            //RegisterUser()
            //Navigeer naar Discover Page
            var intent = Intent(this,DiscoverActivity::class.java)
            this@RegisterActivity.startActivity(intent)

            //Afsluiten thread
            finish()
        }
    }

    private fun fetchDataFromSpotify() {
        val url = "https://api.spotify.com/v1/me"
        val bearer = "Bearer " + intent.getStringExtra("BEARER")
        val client = OkHttpClient()
        val request =  Request.Builder()
            .addHeader("Authorization", bearer)
            .url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                user = gson.fromJson(body, SpotifyUser::class.java)

                //Vullen text field
                etRegister_name.setText(user.display_name)
                etRegister_email.setText(user.email)
                etRegister_dob.setText(user.birthdate)

                user.urlPf = user.images[0].url

                LoadData()
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get Spotify Data")
            }
        })
    }

    private fun LoadData() {
        val url = "https://api.spotify.com/v1/me/top/artists?time_range=medium_term&limit=3"
        val bearer = "Bearer " + intent.getStringExtra("BEARER")
        val client = OkHttpClient()
        val request =  Request.Builder()
            .addHeader("Authorization", bearer)
            .url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val gson = GsonBuilder().create()

                val data = gson.fromJson(body, SpotifyData::class.java)

                val genres: MutableList<String> = mutableListOf()

                user.genres = mutableListOf<Genre>()
                user.artists = mutableListOf<Artist>()

                for(item in data.items){
                    for(genre in item.genres){
                        genres.add(genre)
                        var genreUser = Genre(user.id, genre)
                        user.genres.add(genreUser)
                    }
                    var artistUser = Artist(user.id, item.name, item.href ,item.images[0].url )
                    user.artists.add(artistUser)
                }

                runOnUiThread{
                    rvRegister_artist.adapter = RegisterArtistsAdapter(data)
                    gvRegister_genres.adapter = GenreAdapter(this@RegisterActivity, genres)
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get spotify artists & genres")
            }
        })
    }

    private fun RegisterUser(){
        var url = "https://resonateapi.azurewebsites.net/api/user"
        val gson = Gson()
        val json = gson.toJson(user)

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to register user!")
            }
        })

    }
}
