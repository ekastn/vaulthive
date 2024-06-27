import { CgProfile } from "react-icons/cg";
import Spinner from "../../components/Spinner";
import LongListDisplay from "./LongListDisplay";
import useLists from "./useLists";
import { useNavigate } from "react-router-dom";

const Lists = () => {
    const { lists, isLoading, error } = useLists();

    const navigate = useNavigate();

    const handleCreateNewList = () => {
        navigate("/lists/new");
    }

    if (error) {
        console.log(error);
    }

    return (
        <main className="mt-14">
            <div className="flex flex-col items-center justify-center gap-4">
                <h1 className="text-2xl uppercase font-light">
                    Collect, curate, and share. Lists are the perfect way to group games.
                </h1>
                <button onClick={handleCreateNewList} className="px-4 py-2 text-sm rounded-sm font-medium text-base-100 bg-cyan-500">
                    Start your own list
                </button>
            </div>
            <div className="grid grid-cols-[auto_max(20vw,300px)] gap-12 mt-12">
                <div className="rounded-lg">
                    <h2 className="font-light uppercase">recently added</h2>
                    <hr className="w-full h-[1px] mt-3 mb-8 bg-gray-700 border-0" />
                    {isLoading && !lists ? (
                        <Spinner className="" />
                    ) : (
                        <LongListDisplay lists={lists!} />
                    )}
                </div>
                <div className="rounded-lg">
                    <h2 className="font-light uppercase">Popular this week</h2>
                    <hr className="w-full h-[1px] mt-3 mb-8 bg-gray-700 border-0" />
                    <div className="flex flex-col gap-4">
                        <div className="w-full h-[150px] bg-gray-500"></div>
                        <div className="flex flex-col gap-2">
                            <h3 className="text-xl font-bold">Some thing to do with game</h3>
                            <div className="flex items-center gap-2 text-xs text-gray-400">
                                <div className="flex items-center gap-1 text-gray-200">
                                    <CgProfile className="size-4" />
                                    <p>Deeznuts</p>
                                </div>
                                <p>200 games</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    );
};

export default Lists;
