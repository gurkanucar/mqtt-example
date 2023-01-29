package com.gucardev.mqqtpoc.service;

import com.gucardev.mqqtpoc.model.ResponseData;
import com.gucardev.mqqtpoc.model.StateData;
import com.gucardev.mqqtpoc.repository.StateDataRepository;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class StateService {

  private final StateDataRepository stateDataRepository;

  public StateData save(StateData data) {
    return stateDataRepository.save(data);
  }

  public List<StateData> getListByDeviceId(String deviceId) {
    return stateDataRepository.findAll().stream().filter(x -> x.getDeviceId().equals(deviceId))
        .collect(
            Collectors.toList());
  }

  public Flux<ServerSentEvent<List<StateData>>> getById(String id) {
    return Flux.interval(Duration.ofSeconds(1))
        .publishOn(Schedulers.boundedElastic())
        .map(sequence -> ServerSentEvent.<List<StateData>>builder().id(String.valueOf(sequence))
            .event("states-list-event").data(getListByDeviceId(id))
            .build());
  }

}
