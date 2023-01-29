package com.gucardev.mqttpoc.controller;

import com.gucardev.mqttpoc.model.StateData;
import com.gucardev.mqttpoc.service.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

  @Autowired MqttGateway mqtGateway;

  @PostMapping("/sendMessage")
  public ResponseEntity<?> publish(@RequestBody StateData mqttMessage) {
    try {
      mqtGateway.senToMqtt(mqttMessage.toString(), mqttMessage.getTopic());
      return ResponseEntity.ok("Success");
    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.ok("fail");
    }
  }
}
