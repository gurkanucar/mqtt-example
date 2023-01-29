package com.gucardev.mqttpoc.service.impl;

import com.gucardev.mqttpoc.model.StateData;
import com.gucardev.mqttpoc.model.StateDataCache;
import com.gucardev.mqttpoc.service.ServerSentEventService;
import com.gucardev.mqttpoc.service.StateCacheService;
import com.gucardev.mqttpoc.service.StateService;
import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class ServerSentEventServiceImp implements ServerSentEventService {

  private final StateService stateService;
  private final StateCacheService stateCacheService;

  public ServerSentEventServiceImp(StateService stateService, StateCacheService stateCacheService) {
    this.stateService = stateService;
    this.stateCacheService = stateCacheService;
  }

  public Flux<ServerSentEvent<List<StateDataCache>>> getStateCacheDataByDeviceId(String id) {
    return Flux.interval(Duration.ofSeconds(1))
        .publishOn(Schedulers.boundedElastic())
        .map(
            sequence ->
                ServerSentEvent.<List<StateDataCache>>builder()
                    .id(String.valueOf(sequence))
                    .event("states-list-event")
                    .data(stateCacheService.retrieveDataByDeviceId(id))
                    .build());
  }

  public Flux<ServerSentEvent<List<StateData>>> getStateDataByDeviceId(String id) {
    return Flux.interval(Duration.ofSeconds(1))
        .publishOn(Schedulers.boundedElastic())
        .map(
            sequence ->
                ServerSentEvent.<List<StateData>>builder()
                    .id(String.valueOf(sequence))
                    .event("states-list-event")
                    .data(stateService.getListByDeviceId(id))
                    .build());
  }
}
