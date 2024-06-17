import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const loginApi = async (username: string, password: string) => {
    const data = await axios.post<UserTokenType>(`${API_URL}/auth/login`, {
        username,
        password,
    });
    return data;
};

export const registerApi = async (email: string, username: string, password: string) => {
    const data = await axios.post<UserTokenType>(`${API_URL}/auth/register`, {
        email,
        username,
        password,
    });
    return data;
};
