import React, { useEffect, useState } from "react";
import { MapComponent } from "../components/MapComponent";
import { exampleData } from "../res/exampleData";

export const Home = () => {
  const [status, setStatus] = useState("idle");
  const [incomingData, setIncomingData] = useState([]);

  const url = "http://192.168.0.27:8080";

  const updateIncomingData = (data) => {
    console.log("message income");
    const parsedData = JSON.parse(data);
    setIncomingData(parsedData);
  };

  useEffect(() => {
    const sse = new EventSource(`${url}/data/device1`);

    sse.addEventListener("states-list-event", (event) => {
      const data = JSON.parse(event.data);
      console.log(data);
      setIncomingData(data);
    });

    sse.onerror = () => {
      sse.close();
    };
    return () => {
      sse.close();
    };
  }, []);

  return (
    <div>
      <h1>Home</h1>
      {/* [[51.505, -0.09]] */}
      <MapComponent markers={exampleData} />
      <h3>{JSON.stringify(incomingData)}</h3>
    </div>
  );
};
