package com.gucardev.mqttpoc.service;

import com.gucardev.mqttpoc.model.StateData;
import java.util.List;

public interface StateService {

  StateData save(StateData stateData);

  List<StateData> getListByDeviceId(String id);

  List<String> getDevices();
}
