import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const getGameDetailsApi = async (id: number) => {
    const response = await axios.get<GameDetails>(`${API_URL}/games/${id}`);
    return response.data;
};

export const searchGamesApi = async (param: { type: string; value: string }) => {
    const response = await axios.get<GameSearch[]>(`${API_URL}/games?filter=${param.type}&value=${param.value}`);
    return response.data;
};
