import { Link } from "react-router-dom";
import { NAVIGATION } from "../constants";

const Navbar = () => {
    return (
        <nav>
            {NAVIGATION.map((item) => (
                <Link key={item.id} to={item.path}>
                    {item.title}
                </Link>
            ))}
        </nav>
    );
};

export default Navbar;
