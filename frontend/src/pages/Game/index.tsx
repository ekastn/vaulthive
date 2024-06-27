import { CgPlayListAdd, CgPlayListRemove } from "react-icons/cg";
import { FaStar } from "react-icons/fa";
import { IoMdHeart, IoMdHeartEmpty } from "react-icons/io";
import Carousel from "./Carousel";
import GameInfo from "./GameInfo";
import Skeleton from "./Skeleton";
import useGame from "./useGame";

const Game = () => {
    const {
        game,
        isLoadingGame,
        errorGame,
        images,
        mutateWishlist,
        isInWishlist,
        isPendingWishlist,
        mutateLike,
        isLiked,
        isPendingLike,
    } = useGame();

    if (isLoadingGame) {
        return <Skeleton />;
    }

    if (errorGame) {
        console.log(errorGame);
    }

    const handleWishlistClick = () => {
        if (isPendingWishlist) return;
        mutateWishlist();
    };

    const handleLikeClick = () => {
        if (isPendingLike) return;
        mutateLike();
    }
    

    return (
        <div className="flex flex-col gap-4 h-[calc(100vh-100px)]">
            <div className="flex justify-between items-center py-2">
                <h1 className="text-4xl font-bold text-white">{game?.name}</h1>
                <div className="flex gap-2">
                    <div className="flex flex-col items-center px-2 py-1 text-white">
                        <h3 className="text-sm tracking-widest uppercase font-extralight">Rating</h3>
                        <div className="flex items-center gap-1">
                            <FaStar className="text-2xl" />
                            <p>
                                {game?.rating}
                                <span className="text-sm font-extralight">/5</span>
                            </p>
                        </div>
                    </div>
                    <div className="flex flex-col items-center px-2 py-1 text-white">
                        <h3 className="text-sm tracking-widest uppercase font-extralight">Whishlist</h3>
                        {isInWishlist ? (
                            <CgPlayListRemove onClick={handleWishlistClick} className="text-2xl cursor-pointer" />
                        ) : (
                            <CgPlayListAdd onClick={handleWishlistClick} className="text-2xl cursor-pointer" />
                        )}
                    </div>
                    <div className="flex flex-col items-center px-2 py-1 text-white">
                        <h3 className="text-sm tracking-widest uppercase font-extralight">Like</h3>
                        {isLiked ? (
                            <IoMdHeart onClick={handleLikeClick} className="text-xl cursor-pointer" />
                        ) : (
                            <IoMdHeartEmpty onClick={handleLikeClick} className="text-xl cursor-pointer" />
                        )}
                    </div>
                </div>
            </div>
            <div className="grid gap-6 grid-cols-[auto_max(30vw,300px)] h-full overflow-y-hidden">
                <Carousel images={images} />
                <GameInfo game={game!} />
            </div>
        </div>
    );
};

export default Game;
