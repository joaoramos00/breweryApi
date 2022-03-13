package com.example.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val kode = arrayOf("Cervejaria A",
        "Cervejaria B", "Cerbejaria C", "5b87628",
        "db8d14e", "9913dc4", "e120f96",
        "466251b")

    private val kategori = arrayOf("Kekayaan", "Teknologi",
        "Keluarga", "Bisnis",
        "Keluarga", "Hutang",
        "Teknologi", "Pidana")

    private val isi = arrayOf("3,6",
        "3,9", "5,0", "4,9",
        "pertanyaan 12", "pertanyaan 18", "pertanyaan 20",
        "pertanyaan 21")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemKode: TextView = itemView.findViewById(R.id.breweryName)
//        var itemKategori: TextView = itemView.findViewById(R.id.ratingText)
        var itemIsi: TextView = itemView.findViewById(R.id.breweryType)
        var card: CardView = itemView.findViewById(R.id.card_item)



        init {

            card.setOnClickListener {
                Navigation.findNavController(itemView).navigate(R.id.details)
            }

//            itemView.setOnClickListener {
//
////                val position: Int = absoluteAdapterPosition
//                val context = itemView.context
//
////                val intent = Intent(context, Details::class.java).apply {
//////                    putExtra("NUMBER", position)
//////                    putExtra("CODE", itemKode.text)
//////                    putExtra("CATEGORY", itemKategori.text)
//////                    putExtra("CONTENT", itemIsi.text)
////                }
////                context.startActivity(intent)
//            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemKode.text = kode[i]
//        viewHolder.itemKategori.text = kategori[i]
        viewHolder.itemIsi.text = isi[i]

    }

    override fun getItemCount(): Int {
        return kode.size
    }
}
