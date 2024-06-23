import { useQuery } from "@tanstack/react-query";
import { searchGamesApi } from "../../services/gameService";
import { useState } from "react";

const useGames = () => {
    const [filter, setFilter] = useState<{ type: string; value: string } | null>(null);

    const { data: games, error, isLoading } = useQuery({
        enabled: filter !== null || filter !== undefined,
        queryKey: ["games", { filter }],
        queryFn: async () => searchGamesApi(filter!),
    });

    return { games, error, isLoading, setFilter };
};

export default useGames;
