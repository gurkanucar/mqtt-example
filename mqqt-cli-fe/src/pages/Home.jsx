import React, { useEffect, useState } from "react";
import { MapComponent } from "../components/MapComponent";
import { BASE_URL } from "../res/Constants";
import { exampleData } from "../res/exampleData";

export const Home = (props) => {
  const { initialData, selectedDevice } = props;
  const [incomingData, setIncomingData] = useState(initialData);

  useEffect(() => {
    console.log(incomingData.length);
  }, [incomingData]);

  useEffect(() => {
    const sse = new EventSource(`${BASE_URL}/data/cached/${selectedDevice}`);
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
  }, []);

  return (
    <div>
      <h1>Track Location</h1>
      <MapComponent markers={incomingData} />
    </div>
  );
};
