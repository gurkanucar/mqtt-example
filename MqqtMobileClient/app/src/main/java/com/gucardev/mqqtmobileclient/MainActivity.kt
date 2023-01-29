package com.gucardev.mqqtmobileclient

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MainActivity : AppCompatActivity() {

    lateinit var btnConnect: Button
    lateinit var btnSend: Button
    private lateinit var mqttClient: MqttAndroidClient
    private lateinit var locationText: TextView

    private val locationPermissionManager = LocationPermissionManager(this)
    private lateinit var locationUpdater: LocationUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationPermissionManager.requestLocationPermission()


        locationText = findViewById(R.id.locationText)


        btnConnect = findViewById(R.id.button)
        btnSend = findViewById(R.id.buttonSend)

        btnConnect.setOnClickListener {
            connect(this)
        }

        btnSend.setOnClickListener {
            sendMessage("device1", "myTopic", "hi from app!", "0.0", "0.0")
        }

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )

        val locationUpdater = LocationUpdater(this) { location ->
            handleLocationChange(location)
        }
        locationUpdater.start()


    }

    private fun handleLocationChange(location: Location) {
        // Do something with the new location
        println(location)
        locationText.text = "lat: ${location.latitude}, lon: ${location.longitude}"
        if (mqttClient.isConnected) {
            sendMessage(
                "device1",
                "myTopic",
                "hi from app!",
                location.latitude.toString(),
                location.longitude.toString()
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun connect(context: Context) {
        val serverURI = "tcp://192.168.0.27:1883"
        mqttClient = MqttAndroidClient(context, serverURI, "kotlin_client")



        mqttClient.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.i("Info", "Receive message: ${message.toString()} from topic: $topic")
            }

            override fun connectionLost(cause: Throwable?) {
                Log.i("Info", "Connection lost ${cause.toString()}")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {

            }
        })
        val options = MqttConnectOptions()
        options.userName = "admin"
        options.password = "12345678".toCharArray()
        try {
            mqttClient.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.i("Info", "Connection success")

                    subscribeToTopic()
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.i("Info", "Connection failure")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }

    }

    private fun sendMessage(
        deviceName: String,
        topic: String,
        message: String,
        lat: String,
        lon: String
    ) {
        val gson = Gson()
        //val topic = "myTopic"
        //val message = "{\"message\":\"Hello,MQTTfromclient!\",\"topic\":\"myTopic\"}"
        val message = gson.toJson(StateData(deviceName, topic, message, lat, lon))
        val qos = 1
        val retained = false
        mqttClient.publish(topic, message.toByteArray(), qos, retained)
    }


    private fun subscribeToTopic() {
        val subscriptionTopic = "myTopic"

        try {
            mqttClient?.subscribe(subscriptionTopic, 0, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    Log.w("Mqtt", "Subscribed!")
                    //  setMQTTStatusMessage("MQTT connected")
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    Log.w("Mqtt", "Subscribed fail!")
                    // setMQTTStatusMessage("MQTT subscribe failed - check your keys")

                    //   selfStop = true
                    //  stopLocationTracking()
                    //  AppAggregate.stopService()
                }
            })

        } catch (ex: MqttException) {
            ex.printStackTrace()
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
    }


}
