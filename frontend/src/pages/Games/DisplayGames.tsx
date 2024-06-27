import GameCard from "./GameCard";
import Skeleton from "./Skeleton";

type Props = {
    games: GameSearch[];
    isLoading: boolean;
};

const DisplayGames = ({ games, isLoading }: Props) => {
    if (isLoading) {
        return <Skeleton />;
    }

    return (
        <div className="grid w-full mt-4 grid-cols-3 gap-8">
            {games?.map((game: GameSearch) => <GameCard key={game.id} game={game} />)}
        </div>
    );
};

export default DisplayGames;
