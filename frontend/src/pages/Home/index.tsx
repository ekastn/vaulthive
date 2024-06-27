import DeadCellImg from "../../assets/images/deadcell.jpg";

const Home = () => {
    return (
        <div className="absolute inset-0 -z-30 hero bg-base-100">
            <div className="absolute inset-0 backdrop-contrast-150 opacity-40  -z-10 bg-gradient-to-l from-slate-950 to-slate-200/10"></div>
            <div className="absolute inset-0 -z-20">
                <img src={DeadCellImg} className="object-cover w-full h-full" />
            </div>
            <div className="flex flex-col gap-1 w-full px-12 text-end">
                <h1 className="font-serif text-5xl font-bold text-white">Track games you’ve played</h1>
                <h1 className="font-serif text-5xl font-bold text-white">Save those you want to see</h1>
                <h1 className="font-serif text-5xl font-bold text-white">Tell your friends what’s good</h1>
                <button className="mt-4 ml-auto text-lg z-0 border-0 font-extrabold rounded-md text-white btn bg-red-400 hover:bg-fuchsia-700">
                    Get Started - it's free!
                </button>
            </div>
        </div>
    );
};

export default Home;
