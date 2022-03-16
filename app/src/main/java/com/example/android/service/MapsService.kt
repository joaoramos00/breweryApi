package com.example.android.service

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import java.util.*


class MapsService {

    fun openMap(longitude: Float, latitude: Float, context: Context) {

        val uri: String = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()))
        intent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(context, intent, null)
        } catch (ex: ActivityNotFoundException) {
            try {
                val unrestrictedIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()))
                startActivity(context, unrestrictedIntent, null)
            } catch (innerEx: ActivityNotFoundException) {
                Toast.makeText(context, "Please install a maps application", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}