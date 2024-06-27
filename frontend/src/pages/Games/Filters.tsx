import { FILTER_TYPES, GENRES, PLATFORMS } from "../../constants";

type Props = {
    setFilter: React.Dispatch<React.SetStateAction<{ type: string; value: string } | null>>;
};

const Filters = ({ setFilter }: Props) => {
    return (
        <div className="flex gap-8">
            <div className="text-lg uppercase">Browse BY</div>
            <div className="dropdown dropdown-hover">
                <div tabIndex={0} role="button" className="text-lg uppercase">
                    Platform
                </div>
                <ul
                    tabIndex={0}
                    className="absolute top-[10%] dropdown-content rounded-xs z-[1] menu p-1 shadow bg-gray-700"
                >
                    <li className="uppercase font-bold px-4 py-2">Platform</li>
                    {PLATFORMS.map((platform) => (
                        <li key={platform.id}>
                            <div
                                onClick={() => setFilter({ type: FILTER_TYPES.PLATFORM, value: platform.slug })}
                                className="rounded-xs font-light"
                            >
                                {platform.name}
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="dropdown dropdown-hover">
                <div tabIndex={0} role="button" className="text-lg uppercase">
                    Genre
                </div>
                <ul
                    tabIndex={0}
                    className="absolute top-[10%] dropdown-content rounded-xs z-[1] menu p-1 shadow bg-gray-700"
                >
                    <li className="uppercase font-bold px-4 py-2">Genre</li>
                    {GENRES.map((genre) => (
                        <li key={genre.id}>
                            <div
                                onClick={() => setFilter({ type: FILTER_TYPES.GENRE, value: genre.slug })}
                                className="rounded-xs font-light"
                            >
                                {genre.name}
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="text-lg uppercase">Top</div>
        </div>
    );
};

export default Filters;
