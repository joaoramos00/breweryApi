package com.example.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * A simple [Fragment] subclass.
 * Use the [Details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Details : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context? = getContext()
        val btnShowMap = view.findViewById<ImageButton>(R.id.btnShowMap)
        btnShowMap.setOnClickListener(View.OnClickListener { view ->
            if (context != null) {
                Maps().openMap(2.7F,2.7F, context)
            }
        })
        view.findViewById<TextView>(R.id.breweryName).setText(Data().breweryName)
        view.findViewById<TextView>(R.id.rateNumber).setText(Data().breweryRate)
        view.findViewById<TextView>(R.id.rateText).setText(Data().breweryRateString)
        view.findViewById<TextView>(R.id.breweryType).setText(Data().breweryType)
        view.findViewById<TextView>(R.id.breweryWeb).setText(Data().breweryWeb)
        view.findViewById<TextView>(R.id.breweryAddress).setText(Data().breweryAddress)
        val ratingBar: RatingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.setOnTouchListener { view, event -> true }
        ratingBar.rating = 3F

        val btnRateBrewery = view.findViewById<Button>(R.id.rateBrewery)
        btnRateBrewery.setOnClickListener {
            val dialog = context?.let { it -> BottomSheetDialog(it) }
            val view = layoutInflater.inflate(R.layout.rate_brewery_layout, null)

        }

    }
}