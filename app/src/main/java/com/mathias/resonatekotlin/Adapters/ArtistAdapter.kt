package com.mathias.resonatekotlin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mathias.resonatekotlin.Artist
import com.mathias.resonatekotlin.R
import com.mathias.resonatekotlin.SwipeActivity
import com.squareup.picasso.Picasso

class ArtistAdapter : BaseAdapter{
    var con: Context
    var artists: MutableList<Artist>
    private  var inflater: LayoutInflater

    constructor(con:Context, artists:MutableList<Artist>): super(){
        this.con = con
        this.artists = artists
        inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: Holder = Holder()
        var rv = View(con)
        rv = inflater.inflate(R.layout.artist_grid_row, null)

        holder.tv = rv.findViewById(R.id.tvArtistGrid_name)
        holder.tv.setText(artists[position].artistName)

        holder.iv = rv.findViewById(R.id.ivAristGrid_image)
        Picasso.get().load(artists[position].urlPf).into(holder.iv)

        rv.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(v?.context, SwipeActivity::class.java)
                intent.putExtra("SWIPE_VALUE", artists[position].artistName)
                intent.putExtra("SWIPE_METHODE", 1)
                v?.context?.startActivity(intent)
            }
        })
        return rv
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return artists.size
    }

    public class Holder{
        lateinit var tv : TextView
        lateinit var iv : ImageView
    }
}