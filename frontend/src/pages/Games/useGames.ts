import { useQuery } from "@tanstack/react-query";
import { getPopularGamesApi, getRecentlyLikedGamesApi, searchGamesApi } from "../../services/gameService";
import { useState } from "react";

const useGames = () => {
    const [filter, setFilter] = useState<{ type: string; value: string } | null>(null);

    const {
        data: games,
        error,
        isLoading,
    } = useQuery({
        enabled: filter !== null || filter !== undefined,
        queryKey: ["games", { filter }],
        queryFn: async () => searchGamesApi(filter!),
    });

    const {
        data: recentlyLikedGames,
        error: errorRecentlyLikedGames,
        isLoading: isLoadingRecentllyLikedGames,
    } = useQuery({
        enabled: filter == null || filter == undefined,
        queryKey: ["games", "recently-liked"],
        queryFn: async () => getRecentlyLikedGamesApi(),
    });

    const {
        data: popularGames,
        error: errorPopularGames,
        isLoading: isLoadingPopularGames,
    } = useQuery({
        enabled: filter !== null || filter !== undefined,
        queryKey: ["games", "popular"],
        queryFn: async () => getPopularGamesApi(),
    });

    return {
        games,
        error,
        isLoading,
        setFilter,
        recentlyLikedGames,
        errorRecentlyLikedGames,
        isLoadingRecentllyLikedGames,
        popularGames,
        errorPopularGames,
        isLoadingPopularGames,
    };
};

export default useGames;
