
import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const createListApi = async (userId: number, title: string, description: string, gameIds: number[]) => {
    const data = await axios.post(`${API_URL}/lists/`, {
        userId,
        title,
        description,
        gameIds,
    });
    return data;
}

export const getListsApi = async () => {
    const response = await axios.get<ListGame[]>(`${API_URL}/lists/`);
    return response.data;
}