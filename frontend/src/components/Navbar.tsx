import { Link } from "react-router-dom";
import { NAVIGATION } from "../constants";

const Navbar = () => {
    return (
        <nav className="w-full shadow-sm bg-transparent">
            <div className="flex items-center h-16 px-24">
                <Link to="/" className="flex items-center gap-2">
                    <span className="text-2xl font-semibold">Vaulthive</span>
                </Link>
                <div className="flex items-center gap-4 mx-auto">
                    {NAVIGATION.map((item) => (
                        <Link
                            key={item.id}
                            to={item.path}
                            className="text-base font-medium uppercase transition-colors duration-200 hover:text-white"
                        >
                            {item.title}
                        </Link>
                    ))}
                </div>
                <div className="flex items-center gap-4">
                    <Link
                        to="/login"
                        className="text-base font-medium uppercase transition-colors duration-200 hover:text-white"
                    >
                        sign in
                    </Link>
                    <Link
                        to="/register"
                        className="text-base font-medium uppercase transition-colors duration-200 hover:text-white"
                    >
                        create account
                    </Link>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
