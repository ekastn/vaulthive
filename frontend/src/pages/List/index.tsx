import { IoMdHeartEmpty } from "react-icons/io";
import { IoShareSocial } from "react-icons/io5";
import { useLocation, useNavigate } from "react-router-dom";

const List = () => {
    const navigate = useNavigate();

    const { state } = useLocation();
    const list = state.list as ListGame;

    const handleImageClick = (game: Game) => {
        navigate(`/games/${game.slug}`, { state: { game } });
    };

    return (
        <main className="mt-4 grid gap-6 grid-cols-[auto_max(25vw,300px)] h-full overflow-y-hidden">
            <div>
                <p className="text-base font-light">
                    List by <span className="font-semibold">{list.username}</span>
                </p>
                <hr className="w-full h-[1px] mt-4 mb-6 bg-gray-700 border-0" />
                <div className="flex flex-col gap-4 mb-12">
                    <h1 className="text-2xl font-bold text-white">{list.title}</h1>
                    <p className="mb-4 text-base font-light">{list.description}</p>
                    <div className="grid grid-cols-5 gap-8">
                        {list.games.map((game) => (
                            <div key={game.id} className="aspect-3/4">
                                <img
                                    onClick={() => handleImageClick(game)}
                                    src={game.imageUrl}
                                    alt={game.name}
                                    className="object-cover h-full rounded-md cursor-pointer"
                                />
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            <div className="w-full px-4 py-8">
                <div className="h-auto flex flex-col px-8 py-4 rounded-md bg-neutral-900">
                    <div className="btn text-center bg-transparent border-0">
                        <IoMdHeartEmpty className="text-base" />
                        <p className="text-lg font-light">Like this list?</p>
                    </div>
                    <div className="btn text-center bg-transparent border-0">
                        <IoShareSocial className="text-base" />
                        <p className="text-lg font-light">Share</p>
                    </div>
                </div>
            </div>
        </main>
    );
};

export default List;
