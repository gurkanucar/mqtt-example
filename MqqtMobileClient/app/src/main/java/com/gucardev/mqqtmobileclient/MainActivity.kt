package com.gucardev.mqqtmobileclient

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gucardev.mqqtmobileclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mqttHandler: MqttHandler

    private lateinit var deviceId: String
    private var isConnected: Boolean = false

    private val locationPermissionManager = LocationPermissionManager(this)
    private lateinit var locationUpdater: LocationUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationPermissionManager.requestLocationPermission()

        deviceId = "device1"


        binding.buttonSetDeviceId.setOnClickListener {
            deviceId = binding.deviceNameText.text.toString()
            Log.i("Info", "setDeviceId clicked, deviceId: $deviceId")
        }

        binding.buttonConnect.setOnClickListener {

            mqttHandler = MqttHandler(this, deviceId)
            mqttHandler.connect { isConnected ->
                this.isConnected = isConnected
                Log.i(
                    "Info",
                    "buttonConnect clicked, isConnected: $isConnected , deviceId: $deviceId"
                )
            }
            isConnected = mqttHandler.isConnected
        }

        binding.buttonSend.setOnClickListener {
            Log.i("Info", "btnSend clicked, isConnected: $isConnected")
            if (isConnected) {
                mqttHandler.sendMessage(deviceId, "myTopic", "hi from app!", "0.0", "0.0")
            }
        }

        locationUpdater = LocationUpdater(this) { location ->
            handleLocationChange(location)
        }
        locationUpdater.start()


    }

    private fun handleLocationChange(location: Location) {
        println(location)
        binding.locationText.text = "lat: ${location.latitude}, lon: ${location.longitude}"
        if (isConnected) {
            mqttHandler.sendMessage(
                deviceId,
                deviceId,
                "hi from app!",
                location.latitude.toString(),
                location.longitude.toString()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
