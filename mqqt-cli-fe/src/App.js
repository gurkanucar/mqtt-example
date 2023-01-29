import logo from "./logo.svg";
import "./App.css";
import { Home } from "./pages/Home";
import { useEffect, useState } from "react";

function App() {
  const [initialData, setInitialData] = useState();

  const url = "http://192.168.0.27:8081";

  const fetchInitialData = () => {
    fetch(`${url}/data/stored-data/device1`).then((response) => {
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
    fetchInitialData();
  }, []);

  return (
    <div className="App">
      {initialData && <Home initialData={initialData} />}
    </div>
  );
}

export default App;
