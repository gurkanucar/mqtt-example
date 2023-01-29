package com.gucardev.mqqtpoc.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("StateData")
public class StateDataCache implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String deviceId;
  private String topic;
  private String message;
  private String lat;
  private String lon;

  private boolean sent;

  public StateDataCache(StateData stateData) {
    this.deviceId = stateData.getDeviceId();
    this.topic = stateData.getTopic();
    this.message = stateData.getMessage();
    this.lat = stateData.getLat();
    this.lon = stateData.getLon();
    this.sent = false;
  }
}
