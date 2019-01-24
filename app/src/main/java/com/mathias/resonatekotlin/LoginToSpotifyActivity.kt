package com.mathias.resonatekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_to_spotify.*
import android.webkit.WebView
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_launch.*


class LoginToSpotifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Login to Spotify")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_to_spotify)

        wvSpotifyLogin.settings.javaScriptEnabled = true
        wvSpotifyLogin.settings.loadWithOverviewMode = true
        wvSpotifyLogin.settings.useWideViewPort = true

        wvSpotifyLogin.loadUrl("https://accounts.spotify.com/authorize?client_id=7a788b5554324e06afd076e05e69eaee&redirect_uri=https%3A%2F%2Fexample.com%2Fcallback&scope=user-read-private%20user-read-email%20user-top-read%20user-read-birthdate&response_type=token&show_dialog=true")

        wvSpotifyLogin!!.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if(url!!.contains("access_token")){
                    val helper = url.split('=')[1]
                    val token = helper.split('&')[0]
                    //Verwijs door naar register pagina
                    val intent = Intent(view?.context, RegisterActivity::class.java)
                    intent.putExtra("BEARER", token)
                    view?.context?.startActivity(intent)
                    finish()
                }
            }
        }
    }
}
