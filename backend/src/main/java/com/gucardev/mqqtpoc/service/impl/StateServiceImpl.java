package com.gucardev.mqqtpoc.service.impl;

import com.gucardev.mqqtpoc.model.StateData;
import com.gucardev.mqqtpoc.repository.StateDataRepository;
import com.gucardev.mqqtpoc.service.StateService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

  private final StateDataRepository stateDataRepository;

  public StateData save(StateData data) {
    return stateDataRepository.save(data);
  }

  public List<StateData> getListByDeviceId(String deviceId) {
    return stateDataRepository.findAll().stream()
        .filter(x -> x.getDeviceId().equals(deviceId))
        .collect(Collectors.toList());
  }
}
