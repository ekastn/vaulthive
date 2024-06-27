import { useQuery } from "@tanstack/react-query";
import { useState } from "react";
import Spinner from "./Spinner";
import { searchGamesApi } from "../services/gameService";

const SearchGameInput = ({ handleClick }: { handleClick: (game: GameSearch) => void }) => {
    const [search, setsearch] = useState<string>("");

    const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
        setsearch(e.target.value);
    };

    const handleSearchClick = (game: GameSearch) => {
        handleClick(game);
        setsearch("");
    };

    const { data, isLoading, error } = useQuery({
        enabled: search.length > 3,
        queryKey: ["games", { search }],
        queryFn: async () => searchGamesApi({ type: "name", value: search })
    });

    if (error) {
        console.log(error);
    }

    console.log(data);

    return (
        <div className="relative flex flex-col">
            <input
                type="text"
                value={search}
                onChange={handleSearch}
                placeholder="Type here"
                className="w-full max-w-xs border-0 rounded-sm outline-none ring-0 input bg-gray-900 focus:outline-0 focus:border-0 focus:ring-0"
            />

            <ul className={`${(data || isLoading) ? "block" : "hidden" } absolute z-50 top-full w-full max-h-[300px] overflow-scroll overflow-x-hidden p-2 rounded-md shadow bg-gray-900`}>
                {isLoading && <Spinner className="" />}
                {data?.map((game: GameSearch) => (
                    <li className="flex gap-2 p-2" key={game.id}>
                        <img className="w-[50px] object-cover" src={game.imageUrl} alt={game.name} />
                        <div onClick={() => handleSearchClick(game)} className="text-base-content">
                            <div className="text-sm font-medium cursor-pointer">{game.name}</div>
                            <div className="text-xs">{new Date(game.released).getFullYear()}</div>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default SearchGameInput;
