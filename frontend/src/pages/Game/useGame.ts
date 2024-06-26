import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useLocation, useNavigate } from "react-router-dom";
import { getGameDetailsApi } from "../../services/gameService";
import { useState } from "react";
import { useAuth } from "../../context/useAuth";
import { addGameToWishlistApi, getWishlistApi, removeGameFromWishlistApi } from "../../services/wishlistService";

const useGame = () => {
    const { isLoggedIn } = useAuth();

    const [images, setImages] = useState<string[]>([]);
    const [isInWishlist, setIsInWishlist] = useState(false);

    const { state } = useLocation();

    const queryClient = useQueryClient();
    const navigate = useNavigate();

    const {
        data: game,
        isLoading: isLoadingGame,
        error: errorGame,
    } = useQuery({
        queryKey: ["game", { id: state.game.id }],
        queryFn: async () => {
            const gameDetails: GameDetails = await getGameDetailsApi(state.game.id);
            const gameScreenshots: string[] = gameDetails.screenshots.map((screenshot) => screenshot.image);

            setImages([gameDetails.imageUrl, ...gameScreenshots]);

            return gameDetails;
        },
    });

    const { isLoading: isLoadingWishlist } = useQuery({
        enabled: isLoggedIn(),
        queryKey: ["wishlist"],
        queryFn: async () => {
            const { isInWishlist } = await getWishlistApi(state.game.id);
            setIsInWishlist(isInWishlist);
        },
    });

    const { mutate: mutateWishlist, isPending: isPendingWishlist } = useMutation({
        mutationFn: async () => {
            if (!isLoggedIn()) {
                navigate("/login");
                return;
            }

            if (isInWishlist) {
                await removeGameFromWishlistApi(state.game.id);
            } else {
                await addGameToWishlistApi(state.game.id);
            }
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["wishlist"] });
        },
    });

    return {
        game,
        isLoadingGame,
        images,
        errorGame,
        mutateWishlist,
        isInWishlist,
        isLoadingWishlist,
        isPendingWishlist,
    };
};

export default useGame;
