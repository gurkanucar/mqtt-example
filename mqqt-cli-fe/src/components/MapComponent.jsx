import React, { useState, useRef } from "react";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import marker from "../res/marker.png";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";

export const MapComponent = (props) => {
  const { markers } = props;

  const myIcon = new L.Icon({
    iconUrl: marker,
    iconRetinaUrl: marker,
    popupAnchor: [-0, -0],
    iconSize: [32, 32],
  });

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
        {markers.map((item, idx) => {
          return (
            <Marker
              icon={myIcon}
              key={`marker-${idx}`}
              position={[parseFloat(item.lat), parseFloat(item.lon)]}
            >
              {/* <Popup>
              <span>
                A pretty CSS3 popup. <br /> Easily customizable.
              </span>
            </Popup> */}
            </Marker>
          );
        })}
      </MapContainer>
    </div>
  );
};

{
  /* <TileLayer
url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
/> */
}
