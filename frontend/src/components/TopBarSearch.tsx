import { useQuery } from "@tanstack/react-query";
import { searchGamesApi } from "../services/gameService";
import { useState } from "react";
import Spinner from "./Spinner";
import { IoSearch } from "react-icons/io5";
import { useNavigate } from "react-router-dom";

const TopBarSearch = () => {
    const [search, setsearch] = useState<string>("");

    const navigate = useNavigate();

    const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
        setsearch(e.target.value);
    };

    const handleSearchResultClick = (game: GameSearch) => {
        setsearch("");
        navigate(`/games/${game.slug}`, { state: { game } });
    };

    const {
        data: games,
        isLoading,
        error,
    } = useQuery({
        enabled: search.length > 3,
        queryKey: ["games", { search }],
        queryFn: async () => searchGamesApi({ type: "name", value: search }),
    });

    if (error) {
        console.log(error);
    }

    const resultClass = `${games || isLoading ? "block" : "hidden"} absolute z-20 top-full w-full max-h-[70vh] overflow-y-auto overflow-x-hidden p-2 rounded-b-md shadow-lg shadow-fuchsia-900 bg-neutral-800`;

    return (
        <div className="relative w-full h-full mx-auto">
            <div className="flex items-center w-full h-9 bg-white rounded-t-sm focus-within:shadow-lg focus-within:shadow-fuchsia-900">
                <div className="grid w-12 h-full text-gray-500 place-items-center">
                    <IoSearch />
                </div>
                <input
                    value={search}
                    onChange={handleSearch}
                    type="text"
                    placeholder="Search"
                    className="w-full pr-2 text-sm text-gray-800 outline-none placeholder:text-gray-500"
                />
                <ul className={resultClass}>
                    {isLoading && <Spinner className="" />}
                    {games?.map((game: GameSearch) => (
                        <li
                            key={game.id}
                            onClick={() => handleSearchResultClick(game)}
                            className="flex gap-3 p-2 cursor-pointer hover:bg-neutral-700"
                        >
                            <img className="w-12 aspect-3/4 object-cover" src={game.imageUrl} />
                            <div className="text-base-content">
                                <h4 className="text-base text-white">
                                    {game.name}
                                </h4>
                                <div className="text-xs">
                                    {new Date(game.released).getFullYear()}
                                </div>
                                <div className="text-sm">
                                    {game.genres.map(
                                        (genre, index, self) => `${genre.name}${index < self.length - 1 ? ", " : ""}`,
                                    )}
                                </div>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default TopBarSearch;
