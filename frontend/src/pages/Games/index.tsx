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
        <div className="flex flex-col items-center min-h-screen mt-8 mb-24 gap-8 overflow-hidden">
            <Filters setFilter={setFilter} />
            {games ? (
                <DisplayGames games={games!} isLoading={isLoading} />
            ) : (
                <div className="space-y-4 w-full">
                    <div>
                        <h2 className="text-xl font-light uppercase">popular games</h2>
                        <div className="mt-1 divider"></div>
                        <DisplayGames games={popularGames!} isLoading={isLoadingPopularGames} />
                    </div>
                    <div>
                        <h2 className="text-xl font-light uppercase">recently liked</h2>
                        <div className="mt-1 divider"></div>
                        <DisplayGames games={recentlyLikedGames!} isLoading={isLoadingRecentllyLikedGames} />
                    </div>
                </div>
            )}
        </div>
    );
};

export default Games;
