package com.gucardev.mqqtpoc.service.impl;

import com.gucardev.mqqtpoc.model.StateData;
import com.gucardev.mqqtpoc.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqqtMessageHandler implements MessageHandler {

  private final StateService stateService;
  private final ModelMapper modelMapper;

  public MqqtMessageHandler(StateService stateService, ModelMapper modelMapper) {
    this.stateService = stateService;
    this.modelMapper = modelMapper;
  }

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    try {
      String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
      var rawData = message.getPayload();
      StateData myMessage = modelMapper.map(rawData, StateData.class);
      stateService.save(myMessage);
      log.info(myMessage.toString());
    } catch (Exception e) {
      log.error("something went wrong!");
    }
  }
}
