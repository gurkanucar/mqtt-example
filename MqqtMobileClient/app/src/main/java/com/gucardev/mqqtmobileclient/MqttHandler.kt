package com.gucardev.mqqtmobileclient

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MqttHandler(context: Context, clientId: String) {
    var isConnected: Boolean = false

    private var mqttClient: MqttAndroidClient? = null

    init {
        val serverURI = "tcp://192.168.0.27:1883"
        mqttClient = MqttAndroidClient(context, serverURI, clientId)
    }

    fun connect(callback: (Boolean) -> Unit) {
        mqttClient?.setCallback(object : MqttCallback {
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
            mqttClient?.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.i("Info", "Connection success")
                    subscribeToTopic()
                    isConnected = true
                    callback(true)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.i("Info", "Connection failure")
                    callback(false)
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun sendMessage(
        deviceName: String,
        topic: String,
        message: String,
        lat: String,
        lon: String
    ) {
        val gson = Gson()
        val message = gson.toJson(StateData(deviceName, topic, message, lat, lon))
        val qos = 1
        val retained = false
        Log.i("Info", "message sent ${message}")
        mqttClient?.publish(topic, message.toByteArray(), qos, retained)
    }

    private fun subscribeToTopic() {
        val subscriptionTopic = "myTopic"
        try {
            mqttClient?.subscribe(subscriptionTopic, 0, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    Log.w("Mqtt", "Subscribed!")
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    Log.w("Mqtt", "Subscribed fail!")
                }
            })
        } catch (ex: MqttException) {
            ex.printStackTrace()
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
    }
}
