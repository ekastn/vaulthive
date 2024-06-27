import { useLocation, useNavigate } from "react-router-dom";

const List = () => {
    const navigate = useNavigate();

    const { state } = useLocation();
    const list = state.list as ListGame;
    
    const handleImageClick = (game: Game) => {
        navigate(`/games/${game.slug}`, { state: { game } });
    };

    return (
        <div className="px-24">
            <div>
                <p className="text-base font-light">List by <span className="font-semibold">{list.username}</span></p>
            </div>
            <div className="divider mt-1"></div>
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
    );
};

export default List;
