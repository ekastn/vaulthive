const Home = () => {
    return (
        <div className="min-h-screen px-20 font-mono hero bg-base-100">
            <div className="grid w-full grid-cols-2 px-12 hero-content">
                <div className="justify-self-start">
                    <h1 className="text-5xl font-bold">Welcome!</h1>
                    <p className="py-6">
                        Connect with fellow gamers and discover new games
                    </p>
                    <button className="btn btn-primary">Get Started</button>
                </div>
                <img
                    src="https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg"
                    className="w-[300px] h-[400px] rounded-lg shadow-2xl object-cover justify-self-end"
                />
            </div>
        </div>
    );
};

export default Home;
