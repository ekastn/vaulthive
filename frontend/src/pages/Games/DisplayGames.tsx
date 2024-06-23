import { useNavigate } from "react-router-dom";
import Skeleton from "./Skeleton";
import { FaStar } from "react-icons/fa";

type Props = {
    games: GameSearch[];
    isLoading: boolean;
};

const DisplayGames = ({ games, isLoading }: Props) => {
    const navigate = useNavigate();

    const handleClick = (game: GameSearch) => {
        navigate(`/games/${game.slug}`, { state: { game } });
    };

    if (isLoading) {
        return <Skeleton />;
    }

    return (
        <div className="grid w-full mt-4 grid-cols-3 gap-8">
            {games?.map((game: GameSearch) => (
                <div key={game.id} className="card w-full h-full rounded-md shadow-2xl  bg-base-100">
                    <figure>
                        <img
                            src={game.imageUrl}
                            className="object-cover w-full cursor-pointer aspect-video"
                            alt={game.name}
                        />
                    </figure>
                    <div onClick={() => handleClick(game)} className="card-body p-4 gap-1 rounded-b-md bg-neutral">
                        <div className="flex items-center gap-1">
                            <FaStar className="text-base" />
                            <p>
                                {game?.rating}
                                <span className="text-sm font-extralight">/5</span>
                            </p>
                        </div>
                        <h2 className="cursor-pointer card-title">{game.name}</h2>
                        <p className="text-base font-light">{new Date(game.released).getFullYear()}</p>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default DisplayGames;
