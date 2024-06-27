import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const getGameDetailsApi = async (id: number) => {
    const response = await axios.get<GameDetails>(`${API_URL}/games/${id}`);
    return response.data;
};

export const getPopularGamesApi = async () => {
    const response = await axios.get<GameSearch[]>(`${API_URL}/games/popular`);
    return response.data;
}

export const getRecentlyLikedGamesApi = async () => {
    const response = await axios.get<GameSearch[]>(`${API_URL}/games/recently-liked`);
    return response.data;
}

export const searchGamesApi = async (param: { type: string; value: string }) => {
    const response = await axios.get<GameSearch[]>(`${API_URL}/games?filter=${param.type}&value=${param.value}`);
    return response.data;
};

export const getLikeGameApi = async (id: number) => {
    const response = await axios.get<{ isLiked: boolean }>(`${API_URL}/games/${id}/like`, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
    });
    return response.data;
};

export const likeGameApi = async (id: number) => {
    const response = await axios.post(`${API_URL}/games/${id}/like`, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
    });
    if (response.status === 201) {
        return true;
    }
    return false;
};

export const unlikeGameApi = async (id: number) => {
    const response = await axios.delete(`${API_URL}/games/${id}/like`, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
    });
    if (response.status === 200) {
        return true;
    }
    return false;
};
