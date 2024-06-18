import { useState } from "react";
import SearchGameInput from "../../components/SearchGameInput";
import { IoMdClose } from "react-icons/io";
import { createListApi } from "../../services/ListService";
import { useAuth } from "../../context/useAuth";
import { useNavigate } from "react-router-dom";

const NewList = () => {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [listItems, setListItems] = useState<GameSearch[]>([]);

    const { user } = useAuth();

    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const response = await createListApi(user!.id, title, description, listItems.map((item) => item.id));
        setTitle("");
        setDescription("");
        setListItems([]);
        console.log(response);
    };

    const handleSearchGame = (game: GameSearch) => {
        setListItems([...listItems, game]);
    };

    const removeItem = (id: number) => {
        setListItems(listItems.filter((item) => item.id !== id));
    };

    return (
        <main className="px-24 py-10">
            <h2 className="text-2xl font-light">New List</h2>
            <hr className="w-full h-[1px] mt-2 mb-6 bg-gray-500 border-0" />
            <div className="grid grid-cols-2 gap-12">
                <div className="space-y-4">
                    <form onSubmit={handleSubmit} id="new-list" className="space-y-4">
                        <div className="space-y-2">
                            <label className="text-base font-bold" htmlFor="name">
                                Title
                            </label>
                            <input
                                value={title}
                                onChange={(e) => setTitle(e.target.value)}
                                className="w-full p-3 text-sm rounded-lg"
                                type="text"
                            />
                        </div>
                        <div className="space-y-2">
                            <label className="text-base font-bold" htmlFor="message">
                                Description
                            </label>

                            <textarea
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                                className="w-full p-3 text-sm rounded-lg"
                                rows={8}
                            ></textarea>
                        </div>
                    </form>
                    <div className="flex items-center">
                        <p className="px-4 py-2 uppercase bg-primary">add a film</p>
                        <SearchGameInput handleClick={handleSearchGame} />
                    </div>
                    <div className="">
                        <button
                            type="submit"
                            className="inline-block w-full px-5 py-3 font-medium text-white bg-black rounded-lg sm:w-auto"
                            form="new-list"
                        >
                            Send Enquiry
                        </button>
                    </div>
                </div>

                <ul className="h-full overflow-y-scroll border border-gray-500 rounded-md">
                    {listItems.length === 0 ? (
                        <li className="flex items-center justify-center w-full h-full">
                            <p>
                                <strong>Your list is empty</strong>
                            </p>
                        </li>
                    ) : (
                        listItems.map((item) => (
                            <li key={item.id} className="flex items-center justify-between px-2 py-4">
                                <div className="flex gap-2 p-2">
                                    <img className="w-[50px] object-cover" src={item.imageUrl} alt={item.name} />
                                    <div>
                                        <div className="text-sm font-medium">{item.name}</div>
                                        <div className="text-xs">{new Date(item.released).getFullYear()}</div>
                                    </div>
                                </div>
                                <div onClick={() => removeItem(item.id)} className="px-4">
                                    <IoMdClose className="cursor-pointer" />
                                </div>
                            </li>
                        ))
                    )}
                </ul>
            </div>
        </main>
    );
};

export default NewList;
