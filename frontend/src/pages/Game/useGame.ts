import { useQuery } from "@tanstack/react-query";
import { useLocation } from "react-router-dom";
import { getGameDetailsApi } from "../../services/gameService";
import { useState } from "react";

const useGame = () => {
    const [images, setImages] = useState<string[]>([]);
    const { state } = useLocation();

    const {
        data: game,
        isLoading,
        error,
    } = useQuery({
        queryKey: ["game", { id: state.game.id }],
        queryFn: async () => {
            const gameDetails: GameDetails = await getGameDetailsApi(state.game.id);
            const gameScreenshots: string[] = gameDetails.screenshots.map((screenshot) => screenshot.image);

            setImages([gameDetails.imageUrl, ...gameScreenshots]);

            return gameDetails;
        },
    });

    return { game, isLoading, images, error };
};

export default useGame;
