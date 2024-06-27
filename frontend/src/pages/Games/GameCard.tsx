import { IoMdAdd, IoMdHeart, IoMdHeartEmpty } from "react-icons/io";
import useGame from "../Game/useGame";
import { IoCheckmark } from "react-icons/io5";
import { FaStar } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

type Props = {
    game: GameSearch;
};

const GameCard = ({ game }: Props) => {
    const { mutateLike, mutateWishlist, isPendingLike, isPendingWishlist, isLiked, isInWishlist, setGameId } =
        useGame();

    const navigate = useNavigate();

    const navigateToGameDetails = (game: GameSearch) => {
        navigate(`/games/${game.slug}`, { state: { game } });
    };

    const handleWishlistClick = (gameId: number) => {
        if (isPendingWishlist) return;
        setGameId(gameId);
        mutateWishlist();
    };

    const handleLikeClick = (gameId: number) => {
        if (isPendingLike) return;
        setGameId(gameId);
        mutateLike();
    };

    useEffect(() => {
        setGameId(game.id);
    }, [setGameId, game.id]);

    return (
        <div className="card w-full h-full rounded-md shadow-2xl  bg-base-100">
            <figure>
                <img
                    src={game.imageUrl}
                    alt={game.name}
                    onClick={() => navigateToGameDetails(game)}
                    className="object-cover w-full cursor-pointer aspect-video"
                />
            </figure>
            <div className="card-body p-4 gap-1 rounded-b-md bg-neutral">
                <div className="flex items-center gap-1">
                    <FaStar className="text-base" />
                    <p className="text-sm">
                        {game?.rating}
                        <span className="font-extralight">/5</span>
                    </p>
                    <div className="flex gap-2 justify-end">
                        <div onClick={() => handleLikeClick(game.id)} className="btn text-center">
                            {isLiked ? <IoMdHeart className="text-base" /> : <IoMdHeartEmpty className="text-base" />}
                            <p className="text-sm font-light">Like</p>
                        </div>
                        <div onClick={() => handleWishlistClick(game.id)} className="btn text-center">
                            {isInWishlist ? <IoCheckmark className="text-base" /> : <IoMdAdd className="text-base" />}
                            <p className="text-sm font-light">Wishlist</p>
                        </div>
                    </div>
                </div>
                <h2 onClick={() => navigateToGameDetails(game)} className="cursor-pointer card-title">
                    {game.name}
                </h2>
                <p className="text-base font-light">{new Date(game.released).getFullYear()}</p>
            </div>
        </div>
    );
};

export default GameCard;
