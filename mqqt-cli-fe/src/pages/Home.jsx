import React, { useEffect, useState } from "react";
import { MapComponent } from "../components/MapComponent";
import { exampleData } from "../res/exampleData";

export const Home = (props) => {
  const { initialData } = props;

  const [status, setStatus] = useState("idle");
  const [incomingData, setIncomingData] = useState(initialData);

  const url = "http://192.168.0.27:8081";

  useEffect(() => {
    console.log(incomingData.length);
  }, [incomingData]);

  useEffect(() => {
    const sse = new EventSource(`${url}/data/cached/device1`);
    sse.addEventListener("states-list-event", (event) => {
      const data = JSON.parse(event.data);
      if (data.length > 0) {
        setIncomingData((prevData) => [...prevData, ...data]);
      }
    });

    sse.onerror = () => {
      sse.close();
    };
    return () => {
      sse.close();
    };
  }, [url]);

  return (
    <div>
      <h1>Track Location</h1>
      <MapComponent markers={incomingData} />
    </div>
  );
};
