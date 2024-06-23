import { useNavigate } from "react-router-dom";
import SearchGameInput from "../../components/SearchGameInput";
import { FILTER_TYPES, GENRES, PLATFORMS } from "../../constants";
import { useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { searchGamesApi } from "../../services/gameService";
import Spinner from "../../components/Spinner";

const Games = () => {
    const [filter, setFilter] = useState<{ type: string; value: string } | null>(null);

    const navigate = useNavigate();

    const handeClick = (game: GameSearch) => {
        navigate(`/games/${game.slug}`, { state: { game } });
    };

    const { data: games, error, isLoading } = useQuery({
        queryKey: ["games", { filter }],
        queryFn: async () => searchGamesApi(filter!),
        enabled: filter !== null || filter !== undefined,
    });

    if (error) {
        console.log(error);
    }

    return (
        <main className="min-h-screen px-24 mt-12 overflow-hidden text-sm">
            <div className="flex items-center justify-between py-2">
                <div className="flex gap-4">
                    <div className="text-base uppercase">Browse BY</div>
                    <div className="dropdown dropdown-hover">
                        <div tabIndex={0} role="button" className="text-base uppercase">
                            Platform
                        </div>
                        <ul
                            tabIndex={0}
                            className="absolute top-[10%] dropdown-content rounded-xs z-[1] menu p-1 shadow bg-gray-700"
                        >
                            <li className="uppercase font-bold px-4 py-2">Platform</li>
                            {PLATFORMS.map((platform) => (
                                <li key={platform.id}>
                                    <div
                                        onClick={() => setFilter({ type: FILTER_TYPES.PLATFORM, value: platform.slug })}
                                        className="rounded-xs font-light"
                                    >
                                        {platform.name}
                                    </div>
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div className="dropdown dropdown-hover">
                        <div tabIndex={0} role="button" className="text-base uppercase">
                            Genre
                        </div>
                        <ul
                            tabIndex={0}
                            className="absolute top-[10%] dropdown-content rounded-xs z-[1] menu p-1 shadow bg-gray-700"
                        >
                            <li className="uppercase font-bold px-4 py-2">Genre</li>
                            {GENRES.map((genre) => (
                                <li key={genre.id}>
                                    <div
                                        onClick={() => setFilter({ type: FILTER_TYPES.GENRE, value: genre.slug })}
                                        className="rounded-xs font-light"
                                    >
                                        {genre.name}
                                    </div>
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div className="text-base uppercase">Top</div>
                </div>
                <div className="flex items-center gap-4">
                    <div className="text-base uppercase">find game</div>
                    <SearchGameInput handleClick={handeClick} />
                </div>
            </div>
            {isLoading && <Spinner className="mt-24" />}
            <div className="grid grid-cols-3 w-full gap-8">
                {games?.map((game: GameSearch) => (
                    <div key={game.id} className="card w-full bg-base-100 shadow-2xl">
                        <figure>
                            <img src={game.imageUrl} className="w-full aspect-video object-cover" alt={game.name} />
                        </figure>
                        <div onClick={() => handeClick(game)} className="card-bod cursor-pointer">
                            <h2 className="card-title">
                                {game.name}
                            </h2>
                            <div className="card-actions justify-end">
                                { game?.genres.map((genre) => (
                                    <div key={genre.id} className="badge badge-outline">{genre.name}</div>
                                ))}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </main>
    );
};

export default Games;
