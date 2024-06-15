import { useQuery } from "@tanstack/react-query";
import { useState } from "react";
import getData from "../api";

const SearchGameInput = ({ handleClick }: { handleClick: (game: GameSearch) => void }) => {
    const [search, setsearch] = useState<string>("");

    const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
        setsearch(e.target.value);
    };

    const { data, isLoading } = useQuery({
        enabled: search.length > 3,
        queryKey: ["games", { search }],
        queryFn: async () => getData<GameSearch[]>("games", { search }),
    });

    return (
        <div className="relative flex flex-col ">
            <input onChange={handleSearch} type="text" className="p-2 rounded outline-none" value={search} />
            <div className="w-full">
                <ul className="absolute w-full max-h-[400px] overflow-y-scroll mt-2 bg-gray-800">
                    {isLoading && <li className="w-full p-2">Loading...</li>}
                    {data?.map((game: GameSearch) => (
                        <li className="flex gap-2 p-2" key={game.id}>
                            <img className="w-[50px] object-cover" src={game.imageUrl} alt={game.name} />
                            <div onClick={() => handleClick(game)}>
                                <div className="text-sm font-medium cursor-pointer">{game.name}</div>
                                <div className="text-xs">{new Date(game.released).getFullYear()}</div>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default SearchGameInput;
