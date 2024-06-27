import { CgProfile } from "react-icons/cg";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/useAuth";
import { RiArrowDropDownLine } from "react-icons/ri";

const ProfileButton = () => {
    const { logout, user } = useAuth();

    const navigate = useNavigate();

    const handleDropdownClick = () => {
        navigate(`/${user?.username}`, { state: { user } });
    };

    return (
        <div className="dropdown dropdown-end">
            <div tabIndex={0} role="button" className="flex">
                <h3 className="text-lg font-light uppercase border-0 btn bg-transparent hover:text-white">
                    {user?.username}
                </h3>
                <div className="flex items-center">
                    <RiArrowDropDownLine className="size-8" />
                    <CgProfile className="size-8" />
                </div>
            </div>
            <ul
                tabIndex={0}
                className="menu menu-sm dropdown-content rounded-sm z-[1] mt-3 w-40 p-2 shadow bg-gray-700"
            >
                <li>
                    {" "}
                    <a onClick={handleDropdownClick} className="text-sm">
                        Profile
                    </a>{" "}
                </li>
                <li>
                    {" "}
                    <a onClick={handleDropdownClick} className="text-sm">
                        Wishlist
                    </a>{" "}
                </li>
                <li>
                    {" "}
                    <a onClick={handleDropdownClick} className="text-sm">
                        Lists
                    </a>{" "}
                </li>
                <li>
                    {" "}
                    <a onClick={handleDropdownClick} className="text-sm">
                        Likes
                    </a>{" "}
                </li>
                <li>
                    {" "}
                    <div className="divider my-0"></div>{" "}
                </li>
                <li>
                    {" "}
                    <a onClick={logout} className="text-sm">
                        Sign Out
                    </a>{" "}
                </li>
            </ul>
        </div>
    );
};

export default ProfileButton;
