import { Link } from "react-router-dom";
import { NAVIGATION } from "../constants";
import { useAuth } from "../context/useAuth";

const Navbar = () => {
    const { isLoggedIn, logout } = useAuth();

    return (
        <div className="px-24 py-2 navbar">
            <div className="navbar-start">
                <Link to="/" className="text-3xl font-semibold">
                    Vaulthive
                </Link>
            </div>
            <div className="navbar-center">
                {NAVIGATION.map((item) => (
                    <Link key={item.id} to={item.path} className="text-lg font-light uppercase btn hover:text-white">
                        {item.title}
                    </Link>
                ))}
            </div>
            <div className="navbar-end">
                {!isLoggedIn() ? (
                    <div className="flex items-center">
                        <Link to="/login" className="text-lg font-light uppercase btn hover:text-white">
                            sign in
                        </Link>
                        <Link to="/register" className="text-lg font-light uppercase btn hover:text-white">
                            create account
                        </Link>
                    </div>
                ) : (
                    <button onClick={logout} className="text-lg font-light uppercase btn hover:text-white">
                        logout
                    </button>
                )}
            </div>
        </div>
    );
};

export default Navbar;
