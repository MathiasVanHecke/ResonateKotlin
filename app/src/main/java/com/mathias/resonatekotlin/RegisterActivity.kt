package com.mathias.resonatekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.GsonBuilder
import com.mathias.resonatekotlin.Adapters.GenreAdapter
import com.mathias.resonatekotlin.Adapters.RegisterArtistsAdapter
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import java.io.IOException
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    //var user : SpotifyUser = SpotifyUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        rvRegister_artist.layoutManager = LinearLayoutManager(this)

        fetchDataFromSpotify()
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
                val user = gson.fromJson(body, SpotifyUser::class.java)

                //Vullen text field
                etRegister_name.setText(user.display_name)
                etRegister_email.setText(user.email)
                etRegister_dob.setText(user.birthdate)

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

                Log.d("response", body)
                val gson = GsonBuilder().create()

                val data = gson.fromJson(body, SpotifyData::class.java)

                val genres: MutableList<String> = mutableListOf()

                for(item in data.items){
                    for(genre in item.genres){
                        genres.add(genre)
                    }
                }

                //val adapterGenres = ArrayAdapter(this@RegisterActivity, android.R.layout.simple_list_item_1, genres)

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
}
