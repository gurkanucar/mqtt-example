package com.gucardev.mqttmobileclient


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager

class LocationUpdater(context: Context, private val locationChangeHandler: (Location) -> Unit) {

    private val mGpsLocationClient: LocationManager =
        context.getSystemService(LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    fun start() {
        mGpsLocationClient.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            2000L,
            1f,
            locationListener

        )
    }

    private val locationListener = android.location.LocationListener { location ->
        locationChangeHandler(location)
    }
}
