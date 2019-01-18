package com.mathias.resonatekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mathias.resonatekotlin.Adapters.ArtistAdapter
import com.mathias.resonatekotlin.Adapters.GenreAdapter
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import java.io.IOException

class DiscoverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        LoadArtists()
    }

    private fun LoadArtists(){
        val url = "https://resonateapi.azurewebsites.net/api/artist"
        val client = OkHttpClient()
        val request =  Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val gson = GsonBuilder().create()

                val data = gson.fromJson<MutableList<Artist>>(body, object :TypeToken<MutableList<Artist>>() {}.type)

                runOnUiThread{
                    gvDiscover_artists.adapter = ArtistAdapter(this@DiscoverActivity, data)
                }

                LoadGenres()
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get Arists From Resonate Api")
            }
        })
    }

    private fun LoadGenres(){
        val url = "https://resonateapi.azurewebsites.net/api/genre"
        val client = OkHttpClient()
        val request =  Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val gson = GsonBuilder().create()

                val data = gson.fromJson<MutableList<String>>(body, object :TypeToken<MutableList<String>>() {}.type)

                runOnUiThread{
                    gvDiscover_genres.adapter = GenreAdapter(this@DiscoverActivity, data)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get Arists From Resonate Api")
            }
        })
    }
}
