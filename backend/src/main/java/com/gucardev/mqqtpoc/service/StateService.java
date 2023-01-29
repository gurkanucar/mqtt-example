package com.gucardev.mqqtpoc.service;

import com.gucardev.mqqtpoc.model.StateData;
import java.util.List;

public interface StateService {

  StateData save(StateData stateData);

  List<StateData> getListByDeviceId(String id);

  List<String> getDevices();
}
