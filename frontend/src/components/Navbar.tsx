import { Link } from "react-router-dom";
import { NAVIGATION } from "../constants";
import { useAuth } from "../context/useAuth";
import ProfileButton from "./ProfileButton";
import TopBarSearch from "./TopBarSearch";

const Navbar = () => {
    const { isLoggedIn } = useAuth();

    return (
        <div className="flex items-center justify-between gap-8 py-4">
            <div className="absolute w-full h-[15vh] top-0 left-0 -z-20 opacity-50 bg-gradient-to-b from-fuchsia-950 to-fuchsia-700/0"></div>
            <div className="">
                <Link to="/" className="text-3xl font-semibold text-white">
                    Vaulthive
                </Link>
            </div>
            <TopBarSearch />
            <div className="flex items-center gap-8">
                {NAVIGATION.map((item) => (
                    <Link key={item.id} to={item.path} className="text-lg uppercase hover:text-white">
                        {item.title}
                    </Link>
                ))}
                <button className="text-lg uppercase hover:text-white">Members</button>
                {!isLoggedIn() ? (
                    <div className="flex items-center gap-8">
                        <Link to="/login" className="text-lg uppercase text-nowrap hover:text-white">
                            sign in
                        </Link>
                        <Link to="/register" className="text-lg uppercase text-nowrap hover:text-white">
                            sign up
                        </Link>
                    </div>
                ) : (
                    <ProfileButton />
                )}
            </div>
        </div>
    );
};

export default Navbar;
