import { useNavigate } from "react-router-dom";
import SearchGameInput from "../../components/SearchGameInput";

const Games = () => {
    const navigate = useNavigate();

    const handeClick = (game: GameSearch) => {
        navigate(`/games/${game.slug}`, { state: game });
    };

    return (
        <main className="min-h-screen px-24 mt-12 overflow-hidden text-sm">
            <div className="flex justify-between">
                <div className="flex gap-4">
                    <div className="uppercase">Browse BY</div>
                    <div className="uppercase">Platform</div>
                    <div className="uppercase">Genre</div>
                    <div className="uppercase">Top</div>
                </div>
                <div className="flex items-center gap-4">
                    <div>find game</div>
                    <SearchGameInput handleClick={handeClick} />
                </div>
            </div>
        </main>
    );
};

export default Games;
