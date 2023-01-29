package com.gucardev.mqqtpoc.service;

import com.gucardev.mqqtpoc.model.StateDataCache;
import java.util.List;

public interface StateCacheService {

  void addToCache(StateDataCache stateDataCache);

  void deleteAll(String deviceId);

  List<StateDataCache> retrieveDataByDeviceId(String deviceId);
}
