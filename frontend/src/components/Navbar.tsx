import { Link } from "react-router-dom";
import { NAVIGATION } from "../constants";
import { useAuth } from "../context/useAuth";
import ProfileButton from "./ProfileButton";

const Navbar = () => {
    const { isLoggedIn } = useAuth();

    return (
        <div className="flex items-center justify-between px-24 py-3">
            <div className="">
                <Link to="/" className="text-3xl font-semibold text-white">
                    Vaulthive
                </Link>
            </div>
            <div className="flex items-center">
                {NAVIGATION.map((item) => (
                    <Link
                        key={item.id}
                        to={item.path}
                        className="text-lg font-light uppercase border-0 btn bg-transparent hover:text-white"
                    >
                        {item.title}
                    </Link>
                ))}
                {!isLoggedIn() ? (
                    <div className="flex items-center">
                        <Link to="/login" className="text-lg font-light uppercase border-0 btn bg-transparent hover:text-white">
                            sign in
                        </Link>
                        <Link to="/register" className="text-lg font-light uppercase border-0 btn bg-transparent hover:text-white">
                            create account
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
