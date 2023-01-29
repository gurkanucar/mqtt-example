package com.gucardev.mqqtpoc.controller;

import com.gucardev.mqqtpoc.model.StateData;
import com.gucardev.mqqtpoc.service.ServerSentEventService;
import com.gucardev.mqqtpoc.service.StateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class StateDataController {

  private final StateService stateService;
  private final ServerSentEventService eventService;

  @GetMapping("/{clientId}")
  public Flux<ServerSentEvent<List<StateData>>> streamLastMessage(@PathVariable String clientId) {
    return eventService.getByDeviceId(clientId);
  }
}
