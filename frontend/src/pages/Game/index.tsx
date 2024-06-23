import { useQuery } from "@tanstack/react-query";
import { useLocation } from "react-router-dom";
import { getGameDetailsApi } from "../../services/gameService";
import Spinner from "../../components/Spinner";
import { FaStar } from "react-icons/fa";
import { MdAddCard } from "react-icons/md";
import { IoIosArrowBack, IoIosArrowForward } from "react-icons/io";
import { useState } from "react";

const Game = () => {
    const [imageIndex, setImageIndex] = useState(0);
    const { state } = useLocation();

    const {
        data: game,
        isLoading,
        error,
    } = useQuery({
        queryKey: ["game", { id: state.game.id }],
        queryFn: async () => getGameDetailsApi(state.game.id),
    });

    const nextImage = () => setImageIndex((prev) => (prev === (game?.screenshots.length ?? 0) ? 0 : prev + 1));
    const prevImage = () => setImageIndex((prev) => (prev === 0 ? (game?.screenshots.length ?? 0) : prev - 1));

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
                            <p>
                                {game?.rating}
                                <span className="text-sm font-extralight">/5</span>
                            </p>
                        </div>
                    </div>
                    <div className="flex flex-col items-center px-2 py-1">
                        <h3 className="text-sm uppercase font-extralight">Whishlist</h3>
                        <MdAddCard className="text-xl cursor-pointer" />
                    </div>
                </div>
            </div>
            <div className="grid gap-7 grid-cols-[auto_max(30vw,300px)] h-full">
                <div className="w-full carousel">
                    <div className="relative w-full carousel-item">
                        <img src={game?.imageUrl} className={`w-full rounded-md object-cover ${imageIndex === 0 ? "block" : "hidden"}`} />
                        {game?.screenshots.map((screenshot, index) => (
                            <img
                                key={index + 1}
                                src={screenshot.image}
                                className={`w-full rounded-md object-cover ${index + 1 === imageIndex ? "block" : "hidden"}`}
                            />
                        ))}
                        <div className="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                         <button onClick={prevImage} className="p-1 text-gray-600 rounded-full shadow bg-black/20 hover:text-gray-400 hover:bg-black/55" >
                            <IoIosArrowBack className="text-4xl cursor-pointer" />
                        </button>
                         <button onClick={nextImage} className="p-1 text-gray-600 rounded-full shadow bg-black/20 hover:text-gray-400 hover:bg-black/55" >
                            <IoIosArrowForward className="text-4xl cursor-pointer" />
                        </button>
                        </div>
                    </div>
                </div>
                <div className="flex flex-col overflow-y-hidden">
                    <div className="h-full p-2 overflow-y-scroll space-y-1 text-[10pt] font-light">
                        <h2 className="mb-1 text-xl font-bold">Description</h2>
                        <p className="text-sm font-extralight">{game?.description}</p>
                    </div>
                    <div className="w-full px-4 py-4 space-y-1 text-sm font-light bg-base-300">
                        <div>
                            Genre:{" "}
                            {game?.genres.map(
                                (genre, index, self) => `${genre.name}${index < self.length - 1 ? ", " : ""}`,
                            )}
                        </div>
                        <div>
                            Platform:{" "}
                            {game?.platforms.map(
                                (platform, index, self) => `${platform.name}${index < self.length - 1 ? ", " : ""}`,
                            )}
                        </div>
                        <div>
                            Developer:{" "}
                            {game?.developers.map(
                                (developer, index, self) => `${developer.name}${index < self.length - 1 ? ", " : ""}`,
                            )}
                        </div>
                        <div>
                            Publisher:{" "}
                            {game?.publishers.map(
                                (publisher, index, self) => `${publisher.name}${index < self.length - 1 ? ", " : ""}`,
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </main>
    );
};

export default Game;
