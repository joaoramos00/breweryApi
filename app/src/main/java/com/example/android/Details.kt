package com.example.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment


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
        val btnShowMap = view.findViewById<ImageButton>(R.id.btnShowMap)
        btnShowMap.setOnClickListener(View.OnClickListener { view ->
        val maps: Maps = Maps()
        val context: Context? = getContext()
            if (context != null) {
                maps.openMap(2.7F,2.7F, context)
            }
        })
    }
}