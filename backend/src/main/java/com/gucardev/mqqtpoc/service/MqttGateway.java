package com.gucardev.mqqtpoc.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

  void senToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}
