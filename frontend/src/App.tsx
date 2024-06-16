import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Games from "./pages/Games";
import Lists from "./pages/Lists";
import Navbar from "./components/Navbar";
import Game from "./pages/Game";

const App = () => {
    return (
        <BrowserRouter>
            <Navbar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/games" element={<Games />} />
                <Route path="/games/:slug" element={<Game />} />
                <Route path="/lists" element={<Lists />} />
            </Routes>
        </BrowserRouter>
    );
};

export default App;
