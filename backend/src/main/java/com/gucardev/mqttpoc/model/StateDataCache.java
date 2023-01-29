package com.gucardev.mqttpoc.model;

import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("StateData")
public class StateDataCache implements Serializable {
  @Indexed
  @Id String id;

  @Indexed private String deviceId;
  private String topic;
  private String message;
  private String lat;
  private String lon;

  public StateDataCache(StateData stateData) {
    this.deviceId = stateData.getDeviceId();
    this.topic = stateData.getTopic();
    this.message = stateData.getMessage();
    this.lat = stateData.getLat();
    this.lon = stateData.getLon();
  }
}
