import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const getWishlistApi = async (gameId: number) => {
    const response = await axios.get<{ isInWishlist: boolean }>(`${API_URL}/wishlist/${gameId}`);
    return response.data;
}

export const addGameToWishlistApi = async (gameId: number) => {
    const response = await axios.post(`${API_URL}/wishlist/${gameId}`);
    if (response.status === 201) {
        return true;
    }
    return false;
}

export const removeGameFromWishlistApi = async (gameId: number) => {
    const response = await axios.delete(`${API_URL}/wishlist/${gameId}`);
    if (response.status === 200) {
        return true;
    }
    return false;
}