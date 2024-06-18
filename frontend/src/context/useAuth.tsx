import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { loginApi, registerApi } from "../services/authService";

type UserContextType = {
    user: UserType | null;
    token: string | null;
    registerUser: (email: string, username: string, password: string) => void;
    loginUser: (username: string, password: string) => void;
    logout: () => void;
    isLoggedIn: () => boolean;
};

type Props = { children: React.ReactNode };

const UserContext = createContext<UserContextType>({} as UserContextType);

export const AuthProvider = ({ children }: Props) => {
    const navigate = useNavigate();
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<UserType | null>(null);
    const [isReady, setIsReady] = useState(false);

    useEffect(() => {
        const user = localStorage.getItem("user");
        const token = localStorage.getItem("token");
        if (user && token) {
            setUser(JSON.parse(user));
            setToken(token);
            axios.defaults.headers.common["Authorization"] = "Bearer " + token;
        }
        setIsReady(true);
    }, []);

    const registerUser = async (email: string, username: string, password: string) => {
        await registerApi(email, username, password)
            .then((res) => {
                if (res) {
                    localStorage.setItem("token", res?.data.token);
                    const userObj = {
                        id: res?.data.id,
                        username: res?.data.username,
                        email: res?.data.email,
                    };
                    localStorage.setItem("user", JSON.stringify(userObj));
                    setToken(res?.data.token);
                    setUser(userObj!);
                    navigate("/");
                }
            })
            .catch((e) => console.log(e));
    };

    const loginUser = async (username: string, password: string) => {
        await loginApi(username, password)
            .then((res) => {
                if (res) {
                    localStorage.setItem("token", res?.data.token);
                    const userObj = {
                        id: res?.data.id,
                        username: res?.data.username,
                        email: res?.data.email,
                    };
                    localStorage.setItem("user", JSON.stringify(userObj));
                    setToken(res?.data.token);
                    setUser(userObj!);
                    navigate("/");
                }
            })
            .catch((e) => console.log(e));
    };

    const isLoggedIn = () => {
        return !!user;
    };

    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        setUser(null);
        setToken("");
        navigate("/");
    };

    return (
        <UserContext.Provider value={{ loginUser, user, token, logout, isLoggedIn, registerUser }}>
            {isReady ? children : null}
        </UserContext.Provider>
    );
};

export const useAuth = () => useContext(UserContext);
