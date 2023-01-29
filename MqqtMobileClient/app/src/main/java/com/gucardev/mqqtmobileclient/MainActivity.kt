package com.gucardev.mqqtmobileclient

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var btnConnect: Button
    lateinit var btnSend: Button
    lateinit var btnSetDeviceId: Button
    private lateinit var mqttHandler: MqttHandler
    private lateinit var locationText: TextView
    private lateinit var incomingDataText: TextView
    private lateinit var deviceId: String
    private lateinit var deviceIdText: EditText
    private var isConnected: Boolean = false

    private val locationPermissionManager = LocationPermissionManager(this)
    private lateinit var locationUpdater: LocationUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationPermissionManager.requestLocationPermission()

        deviceId = "device1"

        locationText = findViewById(R.id.locationText)
        btnConnect = findViewById(R.id.button)
        btnSend = findViewById(R.id.buttonSend)
        btnSetDeviceId = findViewById(R.id.buttonSetDeviceId)
        deviceIdText = findViewById(R.id.deviceNameText)
        incomingDataText = findViewById(R.id.incomingData)

        btnSetDeviceId.setOnClickListener {
            deviceId = deviceIdText.text.toString()
        }

        btnConnect.setOnClickListener {
            mqttHandler = MqttHandler(this, deviceId)
            mqttHandler.connect { isConnected ->
                this.isConnected = isConnected
            }
            isConnected = mqttHandler.isConnected
        }

        btnSend.setOnClickListener {
            Log.i("Info", "btnSend clicked, isConnected: $isConnected")
            if (isConnected) {
                mqttHandler.sendMessage(deviceId, "myTopic", "hi from app!", "0.0", "0.0")
            }
        }

//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//            ),
//            0
//        )

        val locationUpdater = LocationUpdater(this) { location ->
            handleLocationChange(location)
        }
        locationUpdater.start()


    }

    private fun handleLocationChange(location: Location) {
        println(location)
        locationText.text = "lat: ${location.latitude}, lon: ${location.longitude}"
        if (isConnected) {
            mqttHandler.sendMessage(
                deviceId,
                "myTopic",
                "hi from app!",
                location.latitude.toString(),
                location.longitude.toString()
            )
        }
    }


}
