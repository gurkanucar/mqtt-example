package com.gucardev.mqqtmobileclient

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MainActivity : AppCompatActivity() {

    lateinit var btnConnect: Button
    lateinit var btnSend: Button
    private lateinit var mqttClient: MqttAndroidClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConnect = findViewById(R.id.button)
        btnSend = findViewById(R.id.buttonSend)

        btnConnect.setOnClickListener {
            connect(this)
        }

        btnSend.setOnClickListener {
            sendMessage()
        }

/*
        val client = MqttAndroidClient(this, "tcp://192.168.0.27:1883", "clientId")

        client.connect(options, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken) {
                val topic = "myTopic"
                val qos = 1
                client.subscribe(topic, qos)

                client.setCallback(object : MqttCallback {
                    override fun connectionLost(cause: Throwable?) {
                        TODO("Not yet implemented")
                    }

                    override fun messageArrived(topic: String, message: MqttMessage) {

                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken?) {
                        TODO("Not yet implemented")
                    }

                    // other callbacks
                })
            }

            override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                // failed to connect
            }
        })*/

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

    private fun sendMessage() {
        val topic = "myTopic"
        val message = "Hello, MQTT from client!"
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