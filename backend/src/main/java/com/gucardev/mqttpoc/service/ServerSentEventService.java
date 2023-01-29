package com.gucardev.mqttpoc.service;

import com.gucardev.mqttpoc.model.StateData;
import com.gucardev.mqttpoc.model.StateDataCache;
import java.util.List;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface ServerSentEventService {

  Flux<ServerSentEvent<List<StateData>>> getStateDataByDeviceId(String id);

  Flux<ServerSentEvent<List<StateDataCache>>> getStateCacheDataByDeviceId(String id);
}
