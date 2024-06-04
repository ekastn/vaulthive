import { Link } from "react-router-dom";
import { NAVIGATION } from "../constants";

const Navbar = () => {
    return (
        <nav className="w-full shadow-sm text-txMain bg-gradient-to-b from-gray-950/90 to-gray-950/50 backdrop-blur-sm">
            <div className="flex items-center justify-between h-16 px-4 md:px-6">
                <Link to="/" className="flex items-center gap-2">
                    <span className="text-2xl font-semibold">Vaulthive</span>
                </Link>
                <div className="flex items-center gap-4 sm:gap-6">
                    {NAVIGATION.map((item) => (
                        <Link
                            key={item.id}
                            to={item.path}
                            className="text-base font-medium text-primary uppercase transition-colors duration-200 hover:text-txMain"
                        >
                            {item.title}
                        </Link>
                    ))}
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
