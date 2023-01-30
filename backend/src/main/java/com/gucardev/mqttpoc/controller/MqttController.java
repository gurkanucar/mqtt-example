package com.gucardev.mqttpoc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gucardev.mqttpoc.model.StateData;
import com.gucardev.mqttpoc.service.MqttGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

  private final MqttGateway mqtGateway;
  private final ObjectMapper objectMapper;

  public MqttController(MqttGateway mqtGateway, ObjectMapper objectMapper) {
    this.mqtGateway = mqtGateway;
    this.objectMapper = objectMapper;
  }

  @PostMapping("/sendMessage")
  public ResponseEntity<?> publish(@RequestBody StateData mqttMessage) {
    try {
      mqtGateway.senToMqtt(objectMapper.writeValueAsString(mqttMessage), mqttMessage.getTopic());
      return ResponseEntity.ok("Success");
    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.ok("fail");
    }
  }
}
