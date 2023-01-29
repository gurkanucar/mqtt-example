import React from "react";

const DeviceSelectComponent = (props) => {
  const { selectDevice, deviceIds } = props;

  return (
    <div>
      <select
        style={{
          width: 200,
          height: 40,
          fontSize: 20,
          margin: 20,
          borderRadius: 10,
          padding: 5,
        }}
        onChange={(e) => selectDevice(e)}
        name="Devices"
      >
        <option value="">select device</option>
        {deviceIds.map((x) => (
          <option key={x} value={x}>
            {x}
          </option>
        ))}
      </select>
    </div>
  );
};

export default DeviceSelectComponent;
