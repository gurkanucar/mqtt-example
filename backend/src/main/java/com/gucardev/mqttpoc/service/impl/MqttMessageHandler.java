package com.gucardev.mqttpoc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gucardev.mqttpoc.model.StateData;
import com.gucardev.mqttpoc.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqttMessageHandler implements MessageHandler {

  private final StateService stateService;
  private final ObjectMapper mapper;

  public MqttMessageHandler(StateService stateService, ObjectMapper mapper) {
    this.stateService = stateService;
    this.mapper = mapper;
  }

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    try {
      String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
      var rawData = message.getPayload().toString();
      StateData myMessage = mapper.readValue(rawData, StateData.class);
      stateService.save(myMessage);
      log.info(myMessage.toString());
    } catch (Exception e) {
      log.error("something went wrong!");
    }
  }
}
