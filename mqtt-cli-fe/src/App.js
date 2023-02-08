import logo from "./logo.svg";
import "./App.css";
import { Home } from "./pages/Home";
import { useEffect, useState } from "react";
import { BASE_URL } from "./res/Constants";
import DeviceSelectComponent from "./components/DeviceSelectComponent";
import { ButtonComponent } from "./components/ButtonComponent";

function App() {
  const [initialData, setInitialData] = useState();
  const [deviceIds, setDeviceIds] = useState([]);
  const [selectedDevice, setSelectedDevice] = useState();
  const [deviceSelected, setdeviceSelected] = useState(false);

  const fetchDeviceIds = () => {
    console.log("Base Url:", BASE_URL);
    fetch(`${BASE_URL}/data/devices`).then((response) => {
      return response
        .json()
        .then((data) => {
          setDeviceIds(data);
        })
        .catch((err) => {
          console.log(err);
        });
    });
  };

  const fetchInitialData = () => {
    console.log("Base Url:", BASE_URL);
    fetch(`${BASE_URL}/data/stored-data/${selectedDevice}`).then((response) => {
      return response
        .json()
        .then((data) => {
          setInitialData(data);
        })
        .catch((err) => {
          console.log(err);
        });
    });
  };

  useEffect(() => {
    if (deviceSelected == true) {
      fetchInitialData();
    } else {
      setSelectedDevice("");
    }
  }, [deviceSelected]);

  useEffect(() => {
    fetchDeviceIds();
  }, []);

  return (
    <div className="App">
      {!deviceSelected && (
        <DeviceSelectComponent
          deviceIds={deviceIds}
          selectDevice={(e) => setSelectedDevice(e.target.value)}
        />
      )}
      <ButtonComponent
        onClick={() => {
          if (selectedDevice != "" && selectedDevice != undefined) {
            setdeviceSelected(!deviceSelected);
          }
        }}
        value={deviceSelected ? "Select another" : "View Location"}
      />
      {deviceSelected == true && initialData != undefined && (
        <Home selectedDevice={selectedDevice} initialData={initialData} />
      )}
    </div>
  );
}

export default App;
