import { Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";
import "./index.css";
import { AuthProvider } from "./context/useAuth";

const App = () => {
    return (
        <>
            <AuthProvider>
                <Navbar />
                <Outlet />
            </AuthProvider>
        </>
    );
};

export default App;
