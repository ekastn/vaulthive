import { useQuery } from "@tanstack/react-query";
import { useLocation } from "react-router-dom";
import { getGameDetailsApi } from "../../services/gameService";
import Spinner from "../../components/Spinner";
import { FcRating } from "react-icons/fc";
import { FaStar } from "react-icons/fa";
import { MdAddCard } from "react-icons/md";

const Game = () => {
    const { state } = useLocation();

    const {
        data: game,
        isLoading,
        error,
    } = useQuery({
        queryKey: ["game", { id: state.game.id }],
        queryFn: async () => getGameDetailsApi(state.game.id),
    });

    if (isLoading) {
        return <Spinner className="mt-24" />;
    }

    if (error) {
        console.log(error);
    }

    console.log(game);

    return (
        <main className="px-24 py-4 flex flex-col gap-4 h-[calc(100vh-150px)]">
            <div className="flex justify-between">
                <h1 className="text-4xl font-bold">{game?.name}</h1>
                <div className="flex gap-6">
                    <div className="flex flex-col items-center px-2 py-1">
                        <h3 className="text-sm uppercase font-extralight">Rating</h3>
                        <div className="flex items-center gap-1">
                            <FaStar className="text-2xl" />
                            <p>{game?.rating}</p>
                        </div>
                    </div>
                    <div className="flex flex-col items-center px-2 py-1">
                        <h3 className="text-sm uppercase font-extralight">Whishlist</h3>
                        <MdAddCard className="text-xl cursor-pointer" />
                    </div>
                </div>
            </div>
            <div className="grid gap-7 grid-cols-[auto_max(30vw,300px)] h-full">
                <img src={game?.imageUrl} alt={game?.name} className="object-cover h-full rounded-lg" />
                <div className="flex flex-col overflow-y-hidden">
                    <div className="h-full p-2 overflow-y-scroll space-y-1 text-[10pt] font-light">
                        <h2 className="mb-1 text-xl font-bold">Description</h2>
                        <p className="text-sm font-extralight">{game?.description}</p>
                    </div>
                    <div className="w-full px-4 py-4 space-y-1 text-sm font-light text-gray-300 bg-gray-900">
                        <div>
                            Genre: {game?.genres.map( (genre, index, self) => `${genre.name}${index < self.length - 1 ? ", " : ""}`)}
                        </div>
                        <div>
                            Platform: {game?.platforms.map( (platform, index, self) => `${platform.name}${index < self.length - 1 ? ", " : ""}`)}
                        </div>
                        <div>
                            Developer: {game?.developers.map( (developer, index, self) => `${developer.name}${index < self.length - 1 ? ", " : ""}`)}
                        </div>
                        <div>
                            Publisher: {game?.publishers.map((publisher, index, self) => `${publisher.name}${index < self.length - 1 ? ", " : ""}`)}
                        </div>
                    </div>
                </div>
            </div>
            <div className="grid grid-cols-3 gap-4">
                {game?.screenshots.map((screenshot) => (
                    <img
                        key={screenshot.id}
                        src={screenshot.image}
                        alt={game.name}
                        className="object-cover h-[200px] rounded-lg"
                    />
                ))}
            </div>
        </main>
    );
};

export default Game;
