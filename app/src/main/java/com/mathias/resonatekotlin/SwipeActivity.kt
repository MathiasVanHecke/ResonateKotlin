package com.mathias.resonatekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.kidach1.tinderswipe.model.CardModel
import com.kidach1.tinderswipe.model.Orientations
import com.kidach1.tinderswipe.view.CardContainer
import com.mathias.resonatekotlin.Adapters.GenreAdapter
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.activity_swipe.*
import okhttp3.*
import java.io.IOException

class SwipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)

        var cardStackAdapter = com.mathias.resonatekotlin.Adapters.CardStackAdapter(this)

        //Kaarten toevoegen
        var methode = intent.getIntExtra("SWIPE_METHODE", 1)
        var value = intent.getStringExtra("SWIPE_VALUE")

        //Populate cards
        val url = "https://resonateapi.azurewebsites.net/api/match/koen.vanhecke/$methode/${value.toString().toLowerCase()}"
        val client = OkHttpClient()
        val request =  Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val gson = GsonBuilder().create()

                val data = gson.fromJson<MutableList<SpotifyUser>>(body, object : TypeToken<MutableList<SpotifyUser>>() {}.type)

                if(data != null) {
                    for (user in data) {
                        var card = CardModel(user.display_name, user.id, user.urlPf)
                        addDissmissListener(card)
                        cardStackAdapter.add(card)
                    }

                    Log.d("Users", data.toString())
                    runOnUiThread{
                        cardContainer.orientation = Orientations.Orientation.Disordered
                        cardContainer.adapter = cardStackAdapter
                    }
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get Pot Matches From Resonate Api")
            }
        })
    }

    private fun addDissmissListener(cardModel: CardModel) {
        cardModel.onCardDismissedListener = object : CardModel.OnCardDismissedListener {
            override fun onLike(callback: CardContainer.OnLikeListener) {
                var url = "https://resonateapi.azurewebsites.net/api/match/koen.vanhecke/${cardModel.description}"
                val json = ""

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

            override fun onDislike() {
            }
        }

        cardModel.onClickListener = object : CardModel.OnClickListener{
           override fun OnClickListener() {
              val intent = Intent(this@SwipeActivity, SwipeProfilePage::class.java)
                intent.putExtra("ID", cardModel.description)
                this@SwipeActivity.startActivity(intent)
            }
        }
    }
}
