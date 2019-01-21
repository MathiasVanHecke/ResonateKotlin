package com.mathias.resonatekotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kidach1.tinderswipe.model.CardModel
import com.kidach1.tinderswipe.view.CardStackAdapter
import com.mathias.resonatekotlin.R
import com.squareup.picasso.Picasso

class CardStackAdapter(val mContext: Context) : CardStackAdapter(mContext) {
    override fun getCardView(position: Int, model: CardModel, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.std_card_inner, parent, false)
        }

        (convertView?.findViewById(R.id.titleView) as TextView).text = model.name
        (convertView?.findViewById(R.id.descriptionView) as TextView).text = model.description
        var imageView = convertView!!.findViewById(R.id.imageView) as ImageView
        Picasso.get().load(model.cardImageUrl).into(imageView)

        return convertView!!
    }
}