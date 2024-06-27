import { useState } from "react";
import { CgProfile } from "react-icons/cg";
import { useParams } from "react-router-dom";
import { PROFILE_TABS } from "../../constants";

const Profile = () => {
    const [selectedTab, setSelectedTab] = useState(PROFILE_TABS[0].title);

    const { username } = useParams();

    const selectedTabClass = "text-white font-medium underline decoration-0 underline-offset-[1em]";

    return (
        <div className="flex flex-col items-center gap-4 px-24 py-4">
            <CgProfile className="size-24 font-extrabold" />
            <h1 className="text-2xl font-bold text-white">{username}</h1>
            <ul className="flex items-center">
                {PROFILE_TABS.map((tab) => (
                    <li key={tab.id}>
                        <button
                            onClick={() => setSelectedTab(tab.title)}
                            className={`btn border-0 text-lg bg-transparent ${tab.title === selectedTab ? selectedTabClass : "font-light"}`}
                        >
                            {tab.title}
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Profile;
