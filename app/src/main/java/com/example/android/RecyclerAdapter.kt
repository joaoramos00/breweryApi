package com.example.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.model.Breweries
import com.example.android.view.DetailsActivity

class RecyclerAdapter(var listBreweries: List<Breweries>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bindData(listBreweries[i])
    }

    override fun getItemCount() = listBreweries.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var brewery: Breweries? = null
        var breweryName: TextView = itemView.findViewById(R.id.breweryName)
        var breweryRating: TextView = itemView.findViewById(R.id.breweryRating)
        var breweryType: TextView = itemView.findViewById(R.id.breweryType)

        var letter: TextView = itemView.findViewById(R.id.letter)
        var card: CardView = itemView.findViewById(R.id.card_item)

        fun bindData(breweries: Breweries) {
            breweryName.text = breweries.name
            breweryType.text = breweries.brewery_type
            letter.text = breweries.name?.first().toString()
            breweryRating.text = "0.0"
            brewery = breweries
        }

        init {

            card.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("breweryId", brewery?.id)
                }
                context.startActivity(intent)
            }

//            itemView.setOnClickListener {
//
////                val position: Int = absoluteAdapterPosition
//                val context = itemView.context
//

//            }
        }
    }
}
