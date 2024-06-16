import ImagePlaceholder from "../../assets/ImgPlaceholder.svg";

const Home = () => {
    return (
        <main className="w-scheen flex min-h-screen flex-col overflow-x-hidden">
            <div>
                <img
                    className="fixed -z-10 h-[100vh] w-full object-cover"
                    src="https://images7.alphacoders.com/327/327601.jpg"
                    alt=""
                />
                <div className="flex h-screen flex-col justify-center gap-4 p-8">
                    <h1 className="w-[50vw] text-5xl font-semibold">
                        Connect with fellow gamers and discover new games
                    </h1>
                    <button className="bg-accent max-w-max px-8 py-2">Get started</button>
                </div>
            </div>
        </main>
    );
};

export default Home;
