import Filters from "./Filters";
import useGames from "./useGames";
import DisplayGames from "./DisplayGames";

const Games = () => {
    const {
        setFilter,
        games,
        isLoading,
        error,
        popularGames,
        recentlyLikedGames,
        isLoadingPopularGames,
        isLoadingRecentllyLikedGames,
        errorPopularGames,
        errorRecentlyLikedGames,
    } = useGames();

    if (error || errorPopularGames || errorRecentlyLikedGames) {
        console.log(error, errorPopularGames, errorRecentlyLikedGames);
    }

    return (
        <div className="min-h-screen px-24 mt-12 mb-24 overflow-hidden text-sm">
            <Filters setFilter={setFilter} />
            {games ? (
                <DisplayGames games={games!} isLoading={isLoading} />
            ) : (
                <div className="space-y-4">
                    <div>
                        <h2 className="text-xl font-light uppercase">popular games</h2>
                        <div className="divider mt-1"></div>
                        <DisplayGames games={popularGames!} isLoading={isLoadingPopularGames} />
                    </div>
                    <div>
                        <h2 className="text-xl font-light uppercase">recently liked</h2>
                        <div className="divider mt-1"></div>
                        <DisplayGames games={recentlyLikedGames!} isLoading={isLoadingRecentllyLikedGames} />
                    </div>
                </div>
            )}
        </div>
    );
};

export default Games;
