import React from "react";

export const ButtonComponent = (props) => {
  const { value, onClick } = props;

  return (
    <button
      type="button"
      style={{
        width: 200,
        height: 40,
        fontSize: 20,
        margin: 20,
        padding: 5,
      }}
      onClick={onClick}
    >
      {value}
    </button>
  );
};
