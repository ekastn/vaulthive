import { useLocation } from "react-router-dom";

const Game = () => {
    const { state } = useLocation();
    const game = state as GameSearch;

    return (
        <main className="px-24 py-4 flex flex-col gap-4 h-[calc(100vh-150px)]">
            <div className="flex justify-between">
                <h1 className="text-4xl font-bold">{game.name}</h1>
                <div className="flex gap-6">
                    <h3 className="text-sm font-light uppercase">Rating</h3>
                    <h3 className="text-sm font-light uppercase">Wishlist</h3>
                </div>
            </div>
            <div className="grid gap-7 grid-cols-[auto_max(25vw,300px)] h-full">
                <img src={game.imageUrl} alt={game.name} className="object-cover h-full rounded-lg" />
                <div className="flex flex-col overflow-y-hidden">
                    <div className="h-full overflow-y-scroll space-y-1 text-[10pt] font-light">
                        <h2 className="mb-1 text-xl font-bold">Description</h2>
                        <p>
                            Horizon Zero Dawn is an action role-playing game developed by Guerrilla Games and published
                            by Sony Interactive Entertainment. The game is set in a post-apocalyptic world where humans
                            live in primitive tribes and coexist with robotic creatures known as "Machines".
                        </p>
                        <p>
                            The player takes on the role of Aloy, a skilled hunter and archer, who embarks on a journey
                            to uncover the mysteries of her past and the ancient civilization that once ruled the world.
                            Along the way, she must navigate the open-world environment, engage in combat with the
                            Machines, and unravel the secrets of the past to shape the future.
                        </p>
                        <p>
                            Horizon Zero Dawn features a vast and visually stunning open world, with a diverse range of
                            environments, from lush forests to arid deserts. The game's combat system combines melee,
                            ranged, and stealth mechanics, allowing players to approach encounters in a variety of ways.
                            The story is engaging and well-written, with complex characters and thought-provoking
                            themes.
                        </p>
                    </div>
                    <ul className="w-full text-[10pt] py-4 space-y-1 bg-gray-800">
                        <li>
                            <div>Genre: pc, plyastation 2, xbox, gameboy</div>
                        </li>
                        <li>
                            <div>Genre: pc, plyastation 2, xbox, gameboy</div>
                        </li>
                        <li>
                            <div>Genre: pc, plyastation 2, xbox, gameboy</div>
                        </li>
                        <li>
                            <div>Genre: pc, plyastation 2, xbox, gameboy</div>
                        </li>
                    </ul>
                </div>
            </div>
        </main>
    );
};

export default Game;
