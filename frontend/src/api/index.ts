import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

const getData = async <T>(endpoint: string, params?: Record<string, string>) => {
    const searchParams = new URLSearchParams(params).toString();
    const response = await axios.get<T>(`${API_URL}/${endpoint}?${searchParams}`);
    return response.data;
};

export default getData;
