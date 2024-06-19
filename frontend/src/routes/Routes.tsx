import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Games from "../pages/Games";
import Lists from "../pages/Lists";
import Game from "../pages/Game";
import NewList from "../pages/NewList";
import ProtectedRoute from "./ProtectedRoute";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        children: [
            { path: "", element: <Home /> },
            { path: "login", element: <Login /> },
            { path: "register", element: <Register /> },
            { path: "games", element: <Games /> },
            { path: "games/:slug", element: <Game /> },
            { path: "lists", element: <Lists /> },
            {
                path: "lists/new",
                element: (
                    <ProtectedRoute>
                        <NewList />
                    </ProtectedRoute>
                ),
            },
        ],
    },
]);
