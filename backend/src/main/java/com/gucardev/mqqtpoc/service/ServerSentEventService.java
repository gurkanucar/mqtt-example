package com.gucardev.mqqtpoc.service;

import com.gucardev.mqqtpoc.model.StateData;
import com.gucardev.mqqtpoc.model.StateDataCache;
import java.util.List;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface ServerSentEventService {

  Flux<ServerSentEvent<List<StateData>>> getStateDataByDeviceId(String id);

  Flux<ServerSentEvent<List<StateDataCache>>> getStateCacheDataByDeviceId(String id);
}
