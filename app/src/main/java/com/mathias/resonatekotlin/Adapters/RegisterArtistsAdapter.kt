package com.mathias.resonatekotlin.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mathias.resonatekotlin.R
import com.mathias.resonatekotlin.SpotifyData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.register_artist_row.view.*

class RegisterArtistsAdapter(val data: SpotifyData) : RecyclerView.Adapter<CustomViewHolder>(){
    override fun getItemCount(): Int {
        return data.items.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.register_artist_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val artist = data.items.get(p1)

        p0.view.tvArtistRow_artist.text = artist.name
        val artistRowImage = p0.view.ivArtistRow

        Picasso.get().load(artist.images[0].url).into(artistRowImage)
    }
}

class CustomViewHolder(val view: View, var data: SpotifyData? = null ) : RecyclerView.ViewHolder(view)
