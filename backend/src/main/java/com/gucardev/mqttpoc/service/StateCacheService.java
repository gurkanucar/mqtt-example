package com.gucardev.mqttpoc.service;

import com.gucardev.mqttpoc.model.StateDataCache;
import java.util.List;

public interface StateCacheService {

  void addToCache(StateDataCache stateDataCache);

  void deleteAll(String deviceId);

  List<StateDataCache> retrieveDataByDeviceId(String deviceId);
}
