import Filters from "./Filters";
import useGames from "./useGames";
import DisplayGames from "./DisplayGames";

const Games = () => {
    const { setFilter, games, isLoading, error } = useGames();

    if (error) {
        console.log(error);
    }

    return (
        <div className="min-h-screen px-24 mt-12 overflow-hidden text-sm">
            <Filters setFilter={setFilter} />
            <DisplayGames games={games!} isLoading={isLoading} />
        </div>
    );
};

export default Games;
