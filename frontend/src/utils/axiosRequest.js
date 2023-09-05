import axios from "axios";

const baseURL = process.env.NEXT_PUBLIC_BASE_API_URL;

const axiosRequest = axios.create({
  baseURL,
});

const token = localStorage.getItem("keepit_access_token");
if (token) {
  axiosRequest.defaults.headers.common["Authorization"] = `Bearer ${token}`;
}

export default axiosRequest;