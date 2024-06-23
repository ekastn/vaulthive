type Props = {
    game: GameDetails;
};
const GameInfo = ({ game }: Props) => {
    return (
        <div className="flex flex-col overflow-y-hidden">
            <div className="h-full p-2 overflow-y-scroll space-y-1 text-[10pt] font-light">
                <h2 className="mb-1 text-xl font-bold">Description</h2>
                <p className="text-sm font-extralight">{game?.description}</p>
            </div>
            <div className="w-full px-4 py-4 space-y-1 text-sm font-light bg-base-300">
                <div>
                    Genre:{" "}
                    {game?.genres.map((genre, index, self) => `${genre.name}${index < self.length - 1 ? ", " : ""}`)}
                </div>
                <div>
                    Platform:{" "}
                    {game?.platforms.map(
                        (platform, index, self) => `${platform.name}${index < self.length - 1 ? ", " : ""}`,
                    )}
                </div>
                <div>
                    Developer:{" "}
                    {game?.developers.map(
                        (developer, index, self) => `${developer.name}${index < self.length - 1 ? ", " : ""}`,
                    )}
                </div>
                <div>
                    Publisher:{" "}
                    {game?.publishers.map(
                        (publisher, index, self) => `${publisher.name}${index < self.length - 1 ? ", " : ""}`,
                    )}
                </div>
            </div>
        </div>
    );
};

export default GameInfo;
