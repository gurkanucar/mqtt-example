package com.gucardev.mqttpoc.service.impl;

import com.gucardev.mqttpoc.model.StateDataCache;
import com.gucardev.mqttpoc.repository.StateDataCacheRepository;
import com.gucardev.mqttpoc.service.StateCacheService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StateCacheServiceImpl implements StateCacheService {

  private final StateDataCacheRepository repository;

  public StateCacheServiceImpl(StateDataCacheRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addToCache(StateDataCache stateDataCache) {
    repository.save(stateDataCache);
  }

  @Override
  public List<StateDataCache> retrieveDataByDeviceId(String deviceId) {
    var stateData = repository.findAllByDeviceId(deviceId);
    deleteAll(deviceId);
    return stateData;
  }

  @Override
  public void deleteAll(String deviceId) {
    repository.deleteAll(repository.findAllByDeviceId(deviceId));
  }
}
