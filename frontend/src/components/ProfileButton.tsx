import { CgProfile } from "react-icons/cg";
import { HiDotsVertical } from "react-icons/hi";
import { useNavigate } from "react-router-dom";
import { PROFILE_TABS } from "../constants";
import { useAuth } from "../context/useAuth";

const ProfileButton = () => {
    const { logout, user } = useAuth();

    const navigate = useNavigate();

    const handleDropdownClick = () => {
        navigate(`/${user?.username}`, { state: { user } });
    };

    return (
        <div className="dropdown dropdown-end">
            <div tabIndex={0} role="button" className="flex">
                <div className="flex items-center gap-2">
                    <CgProfile className="size-8" />
                    <HiDotsVertical className="size-5" />
                </div>
            </div>
            <ul
                tabIndex={0}
                className="menu menu-sm dropdown-content rounded-sm z-[1] mt-3 w-40 p-2 shadow bg-gray-700"
            >
                <li>
                    <a onClick={handleDropdownClick} className="text-sm">
                        Profile
                    </a>
                </li>
                {PROFILE_TABS.map((tab) => (
                    <li key={tab.id}>
                        <a onClick={handleDropdownClick} className="text-sm">
                            {tab.title}
                        </a>
                    </li>
                ))}
                <li>
                    <div className="divider my-0"></div>
                </li>
                <li>
                    <a onClick={logout} className="text-sm">
                        Sign Out
                    </a>
                </li>
            </ul>
        </div>
    );
};

export default ProfileButton;
