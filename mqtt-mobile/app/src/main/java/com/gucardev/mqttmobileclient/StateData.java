package com.gucardev.mqttmobileclient;


public class StateData {
  private Long id;
  private String deviceId;
  private String topic;
  private String message;
  private String lat;
  private String lon;

  public StateData( String deviceId, String topic, String message, String lat, String lon) {
    this.deviceId = deviceId;
    this.topic = topic;
    this.message = message;
    this.lat = lat;
    this.lon = lon;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }
}
