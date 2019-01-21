package com.mathias.resonatekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mathias.resonatekotlin.Adapters.ArtistAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.activity_swipe_profile_page.*
import okhttp3.*
import java.io.IOException

class SwipeProfilePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_profile_page)

        LoadProfile()
    }

    private fun LoadProfile(){
        val url = "https://resonateapi.azurewebsites.net/api/user/${intent.getStringExtra("ID")}"
        val client = OkHttpClient()
        val request =  Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val gson = GsonBuilder().create()

                val data = gson.fromJson<SpotifyUser>(body, object : TypeToken<SpotifyUser>() {}.type)

                runOnUiThread{
                    gvSwipePage_artists.adapter = ArtistAdapter(this@SwipeProfilePage, data.artists)
                    Picasso.get().load(data.urlPf).into(imageView2)
                    tvProfilePage_name.text = data.display_name
                    tvProfilePage_beschrijving.text = data.beschrijving
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get Profile From Resonate Api")
            }
        })
    }
}
