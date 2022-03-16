package com.example.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

        private var brewery: Breweries? = null
        private val breweryName: TextView = itemView.findViewById(R.id.breweryName)
        private val breweryRating: TextView = itemView.findViewById(R.id.breweryRating)
        private val breweryType: TextView = itemView.findViewById(R.id.breweryType)
        private val starOne: ImageView = itemView.findViewById(R.id.star_one)
        private var starTwo: ImageView = itemView.findViewById(R.id.star_two)
        private val starThree: ImageView = itemView.findViewById(R.id.star_three)
        private val starFour: ImageView = itemView.findViewById(R.id.star_four)
        private val starFive: ImageView = itemView.findViewById(R.id.star_five)

        var letter: TextView = itemView.findViewById(R.id.letter)
        var card: CardView = itemView.findViewById(R.id.card_item)

        fun bindData(breweries: Breweries) {

            var rating: Double? = 1.8
            if (rating === null) rating = 0.0
            breweryName.text = breweries.name
            breweryType.text = breweries.brewery_type
            letter.text = breweries.name?.first().toString()
            breweryRating.text = rating.toString()
            brewery = breweries
            mountStart(rating.toInt())
        }

        private fun mountStart(rating: Int) {
            if (rating > 0) starOne.setBackgroundResource(R.drawable.star)
            if (rating >= 2) starTwo.setBackgroundResource(R.drawable.star)
            if (rating >= 3) starThree.setBackgroundResource(R.drawable.star)
            if (rating >= 4) starFour.setBackgroundResource(R.drawable.star)
            if (rating == 5) starFive.setBackgroundResource(R.drawable.star)
        }

        init {
            card.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("breweryId", brewery?.id)
                }
                context.startActivity(intent)
            }
        }
    }
}
