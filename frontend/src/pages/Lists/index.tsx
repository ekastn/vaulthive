import { useQuery } from "@tanstack/react-query";
import { CgProfile } from "react-icons/cg";
import { FaHeart } from "react-icons/fa";
import { Link } from "react-router-dom";
import { getListsApi } from "../../services/ListService";
import Spinner from "../../components/Spinner";

const Lists = () => {
    const { data, isLoading, error } = useQuery({
        queryKey: ["lists"],
        queryFn: async () => getListsApi(),
    });

    if (error) {
        console.log(error);
    }

    return (
        <main className="px-24 mt-14">
            <div className="flex flex-col items-center justify-center gap-4">
                <h1 className="text-xl uppercase font-extralight">
                    Collect, curate, and share. Lists are the perfect way to group games.
                </h1>
                <Link to="/lists/new" className="px-4 py-2 text-sm font-light bg-secondary">
                    Start your own list
                </Link>
            </div>
            <div className="grid grid-cols-[auto_max(20vw,300px)] gap-32 mt-12">
                <div className="rounded-lg">
                    <h2 className="font-light uppercase">recently added</h2>
                    <hr className="w-full h-[1px] mt-2 mb-6 bg-gray-500 border-0" />
                    {isLoading && !data ? (
                        <Spinner className="" />
                    ) : (
                        <div className="flex flex-col gap-8">
                            {data?.map((list: ListGame) => (
                                <div key={list.id} className="flex gap-4 items-start">
                                    <div className="aspect-video h-[100px] bg-gray-500"></div>
                                    <div className="flex flex-col gap-2">
                                        <h3 className="text-xl font-bold">{list.title}</h3>
                                        <div className="flex items-center gap-2 text-xs">
                                            <div className="flex items-center gap-1">
                                                <CgProfile className="size-4" />
                                                <p>{list.user?.username}</p>
                                            </div>
                                            <p>{list.games.length} games</p>
                                            <div className="flex items-center gap-1">
                                                <FaHeart className="size-4" />
                                                <p>1K</p>
                                            </div>
                                        </div>
                                        <p className="text-sm text-gray-500 text-light">{list.description}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </div>
                <div className="rounded-lg">
                    <h2 className="font-light uppercase">Popular this week</h2>
                    <hr className="w-full h-[1px] mt-2 mb-6 bg-gray-500 border-0" />
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
