package com.example.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.android.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.regex.Matcher
import java.util.regex.Pattern

class RateBrewery : BottomSheetDialogFragment() {

    private val EMAIL_PATTERN = Pattern.compile("^(.+)@(\\S+)$")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.rate_brewery_layout, container, false)
        view?.findViewById<TextView>(R.id.rateBreweryModal)?.text = "Avaliar a Cervejaria"
        val email: String = view.findViewById<TextView>(R.id.emailRate).toString()
        if (isValidEmail(email)) {
            saveRate(email)
        }
        val btnCancel = view.findViewById<ImageButton>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            getActivity()?.getFragmentManager()?.popBackStack();
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun saveRate(email: String) {

    }

    private fun isValidEmail(email: String): Boolean {
        return if (email == null || email.isEmpty() || email.endsWith(".")) {
            false
        } else {
            val emailMatcher: Matcher = EMAIL_PATTERN.matcher(email)
            emailMatcher.matches()
        }
    }


}