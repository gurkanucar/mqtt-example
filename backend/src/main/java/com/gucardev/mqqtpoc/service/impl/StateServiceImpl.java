package com.gucardev.mqqtpoc.service.impl;

import com.gucardev.mqqtpoc.model.StateData;
import com.gucardev.mqqtpoc.model.StateDataCache;
import com.gucardev.mqqtpoc.repository.StateDataRepository;
import com.gucardev.mqqtpoc.service.StateCacheService;
import com.gucardev.mqqtpoc.service.StateService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {

  private final StateDataRepository stateDataRepository;
  private final StateCacheService stateCacheService;

  public StateServiceImpl(
      StateDataRepository stateDataRepository, StateCacheService stateCacheService) {
    this.stateDataRepository = stateDataRepository;
    this.stateCacheService = stateCacheService;
  }

  public StateData save(StateData data) {
    var saved = stateDataRepository.save(data);
    stateCacheService.addToCache(new StateDataCache(saved));
    return saved;
  }

  public List<StateData> getListByDeviceId(String deviceId) {
    stateCacheService.deleteAll(deviceId);
    return stateDataRepository.findAll().stream()
        .filter(x -> x.getDeviceId().equals(deviceId))
        .collect(Collectors.toList());
  }
}
