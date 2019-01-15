package com.mathias.resonatekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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

                Log.d("response", body)
                val gson = GsonBuilder().create()

                //val courseLessons = gson.fromJson(body, Array<CourseLesson>::class.java)

                runOnUiThread{

                }

            }
            override fun onFailure(call: Call, e: IOException) {

            }
        })
    }
}
