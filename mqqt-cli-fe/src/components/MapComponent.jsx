import React, { useState, useRef } from "react";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import marker from "../res/marker2.png";
import {
  CircleMarker,
  MapContainer,
  Marker,
  Polyline,
  Popup,
  TileLayer,
} from "react-leaflet";

export const MapComponent = (props) => {
  const { markers } = props;

  const myIcon = new L.Icon({
    iconUrl: marker,
    iconRetinaUrl: marker,
    popupAnchor: [-0, -0],
    iconSize: [48, 48],
  });

  const renderPositions = (positions) => {
    return (
      <>
        <Polyline color="#0083FF" positions={positions} />
        {positions.map((position, index) => (
          <CircleMarker
            key={index}
            center={position}
            fill={true}
            color="#003363"
            radius={3}
          >
            <Popup>
              <b>device:</b> {position.deviceId} <br />
              <b>lat:</b> {position.lat} <br />
              <b>lon:</b> {position.lon} <br />
            </Popup>
          </CircleMarker>
        ))}
      </>
    );
  };

  const putLastLocation = (markers) => {
    if (markers != undefined && markers.length != 0) {
      const location = markers[markers.length - 1];
      return (
        <>
          <Marker
            icon={myIcon}
            key={`marker-${Math.random()}`}
            position={[parseFloat(location.lat), parseFloat(location.lon)]}
          >
            <Popup>
              <span>Current Location</span>
            </Popup>
          </Marker>
        </>
      );
    }
  };

  return (
    <div style={{ justifyContent: "center", display: "flex" }}>
      <MapContainer
        style={{ width: "80%", height: "40rem" }}
        center={[38.9637, 35.2433]}
        zoom={6}
        scrollWheelZoom={true}
      >
        <TileLayer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        />
        {renderPositions(markers)}
        {putLastLocation(markers)}
      </MapContainer>
    </div>
  );
};
