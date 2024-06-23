import { FaStar } from "react-icons/fa";
import { MdAddCard } from "react-icons/md";
import useGame from "./useGame";
import Carousel from "./Carousel";
import GameInfo from "./GameInfo";
import Skeleton from "./Skeleton";

const Game = () => {
    const { game, isLoading, error, images } = useGame();

    if (isLoading) {
        return <Skeleton />;
    }

    if (error) {
        console.log(error);
    }

    return (
        <div className="px-24 py-4 flex flex-col gap-4 h-[calc(100vh-150px)]">
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
                <Carousel images={images} />
                <GameInfo game={game!} />
            </div>
        </div>
    );
};

export default Game;
