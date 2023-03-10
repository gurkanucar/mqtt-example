package com.gucardev.mqttpoc.controller;

import com.gucardev.mqttpoc.model.StateData;
import com.gucardev.mqttpoc.model.StateDataCache;
import com.gucardev.mqttpoc.service.ServerSentEventService;
import com.gucardev.mqttpoc.service.StateService;
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

  @GetMapping("/devices")
  public List<String> getDevices() {
    return stateService.getDevices();
  }

  @GetMapping("/stored-data/{clientId}")
  public List<StateData> getStoredData(@PathVariable String clientId) {
    return stateService.getListByDeviceId(clientId);
  }

  @GetMapping("/{clientId}")
  public Flux<ServerSentEvent<List<StateData>>> streamLastMessage(@PathVariable String clientId) {
    return eventService.getStateDataByDeviceId(clientId);
  }

  @GetMapping("/cached/{clientId}")
  public Flux<ServerSentEvent<List<StateDataCache>>> streamCachedData(
      @PathVariable String clientId) {
    return eventService.getStateCacheDataByDeviceId(clientId);
  }
}
