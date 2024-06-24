import { CgProfile } from "react-icons/cg";
import { FaHeart } from "react-icons/fa";

type Props = {
    lists: ListGame[];
};

const LongListDisplay = ({ lists }: Props) => {
    return (
        <div className="flex flex-col gap-8">
            {lists?.map((list: ListGame) => (
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
    );
};

export default LongListDisplay;
