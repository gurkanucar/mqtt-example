export const BASE_URL =
  process.env.REACT_APP_ENV === "local"
    ? "http://localhost:8080"
    : "http://localhost:8081";
