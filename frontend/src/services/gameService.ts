import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const getGameDetailsApi = async (id: number) => {
    const response = await axios.get<GameDetails>(`${API_URL}/games/${id}`);
    return response.data;
}