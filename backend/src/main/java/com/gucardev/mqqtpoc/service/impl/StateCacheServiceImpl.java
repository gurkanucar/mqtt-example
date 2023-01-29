package com.gucardev.mqqtpoc.service.impl;

import com.gucardev.mqqtpoc.model.StateDataCache;
import com.gucardev.mqqtpoc.repository.StateDataCacheRepository;
import com.gucardev.mqqtpoc.service.StateCacheService;
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
    repository.deleteAll(stateData);
    return stateData;
  }
}
