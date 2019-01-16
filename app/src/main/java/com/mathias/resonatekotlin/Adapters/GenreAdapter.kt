package com.mathias.resonatekotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mathias.resonatekotlin.R

class GenreAdapter : BaseAdapter{

    var con: Context
    var name: MutableList<String>
    private  var inflater: LayoutInflater

    constructor(con:Context,name: MutableList<String>): super(){
        this.name = name
        this.con = con
        inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: Holder = Holder()
        var rv = View(con)
        rv = inflater.inflate(R.layout.genre_grid_row, null)

        holder.tv = rv.findViewById(R.id.tvGenreGrid_name)
        holder.tv.setText(name[position])
        //Voor onclick listener :: https://www.youtube.com/watch?v=uT5STJuEj5Y
        return rv
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return name.size
    }

    public class Holder{
        lateinit var tv :TextView
    }
}