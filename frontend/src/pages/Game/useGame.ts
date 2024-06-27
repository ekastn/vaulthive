import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/useAuth";
import { getGameDetailsApi, getLikeGameApi, likeGameApi, unlikeGameApi } from "../../services/gameService";
import { addGameToWishlistApi, getWishlistApi, removeGameFromWishlistApi } from "../../services/wishlistService";

const useGame = () => {
    const { isLoggedIn } = useAuth();

    const [gameId, setGameId] = useState<number | null>(null);
    const [images, setImages] = useState<string[]>([]);

    const { state } = useLocation();

    const queryClient = useQueryClient();
    const navigate = useNavigate();

    const getGameId = () => (gameId ? { gameId } : { gameId: state?.game.id });

    const {
        data: game,
        isLoading: isLoadingGame,
        error: errorGame,
    } = useQuery({
        enabled: state?.game !== undefined || state?.game !== null,
        queryKey: ["game", getGameId()],
        queryFn: async () => {
            const gameDetails: GameDetails = await getGameDetailsApi(getGameId().gameId);
            const gameScreenshots: string[] = gameDetails.screenshots.map((screenshot) => screenshot.image);

            setImages([gameDetails.imageUrl, ...gameScreenshots]);

            return gameDetails;
        },
    });

    const { isLoading: isLoadingWishlist, data: isInWishlist } = useQuery({
        enabled: isLoggedIn(),
        queryKey: ["wishlist", getGameId()],
        queryFn: async () => {
            const { isInWishlist } = await getWishlistApi(getGameId().gameId);
            return isInWishlist;
        },
    });

    const { isLoading: isLoadingLike, data: isLiked } = useQuery({
        enabled: isLoggedIn(),
        queryKey: ["like", getGameId()],
        queryFn: async () => {
            const { isLiked } = await getLikeGameApi(getGameId().gameId);
            return isLiked;
        },
    });

    const { mutate: mutateWishlist, isPending: isPendingWishlist } = useMutation({
        mutationFn: async () => {
            if (!isLoggedIn()) {
                navigate("/login");
                return;
            }

            if (isInWishlist) {
                await removeGameFromWishlistApi(getGameId().gameId);
            } else {
                await addGameToWishlistApi(getGameId().gameId);
            }
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["wishlist", getGameId()] });
        },
    });

    const { mutate: mutateLike, isPending: isPendingLike } = useMutation({
        mutationFn: async () => {
            if (!isLoggedIn()) {
                navigate("/login");
                return;
            }

            if (isLiked) {
                await unlikeGameApi(getGameId().gameId);
            } else {
                await likeGameApi(getGameId().gameId);
            }
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["like", getGameId()] });
        },
    });

    return {
        game,
        isLoadingGame,
        images,
        errorGame,
        mutateWishlist,
        mutateLike,
        isInWishlist,
        isLiked,
        isLoadingWishlist,
        isLoadingLike,
        isPendingWishlist,
        isPendingLike,
        setGameId,
    };
};

export default useGame;
