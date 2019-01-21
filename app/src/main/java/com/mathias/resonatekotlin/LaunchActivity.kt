package com.mathias.resonatekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.android.synthetic.main.activity_login_to_spotify.*

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        btnLauch_register.setOnClickListener{
            val intent = Intent(btnLauch_register.context, LoginToSpotifyActivity::class.java)
            btnLauch_register.context.startActivity(intent)
            finish()
        }
    }
}
