import { CgPlayListAdd, CgPlayListRemove } from "react-icons/cg";
import { FaStar } from "react-icons/fa";
import Carousel from "./Carousel";
import GameInfo from "./GameInfo";
import Skeleton from "./Skeleton";
import useGame from "./useGame";

const Game = () => {
    const { game, isLoadingGame, errorGame, images, mutateWishlist, isInWishlist, isPendingWishlist } = useGame();

    if (isLoadingGame) {
        return <Skeleton />;
    }

    if (errorGame) {
        console.log(errorGame);
    }

    const handleWishlistClick = () => {
        if (isPendingWishlist) return;
        mutateWishlist();
        console.log(isInWishlist)
    };

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
                        {isInWishlist ? (
                            <CgPlayListRemove onClick={handleWishlistClick} className="text-2xl cursor-pointer" />
                        ) : (
                            <CgPlayListAdd onClick={handleWishlistClick} className="text-2xl cursor-pointer" />
                        )}
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
