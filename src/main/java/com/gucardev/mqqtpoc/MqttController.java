package com.gucardev.mqqtpoc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

  @Autowired
  MqttGateway mqtGateway;

  @PostMapping("/sendMessage")
  public ResponseEntity<?> publish(@RequestBody String mqttMessage) {
    try {
      mqtGateway.senToMqtt("message!", "myTopic");
      return ResponseEntity.ok("Success");
    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.ok("fail");
    }
  }

}
