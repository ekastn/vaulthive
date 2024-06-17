import { useState } from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../../context/useAuth";

const Register = () => {
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const { registerUser } = useAuth();

    const handleFormSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        registerUser(email, username, password);        
    }

    return (
        <div className="fixed left-0 top-0 flex h-full min-h-screen w-full items-center justify-center bg-black/75 px-4 py-5">
            <div className="w-full max-w-[400px] rounded-sm bg-gray-900 space-y-8 p-8">
                <form className="flex flex-col gap-4" onSubmit={handleFormSubmit}>
                    <div className="space-y-2">
                        <label className="text-sm font-medium">Email Address</label>
                        <input
                            type="email"
                            placeholder="Email"
                            className="w-full text-sm rounded-sm px-2 py-2 outline-none"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className="space-y-2">
                        <label className="text-sm font-medium">Username</label>
                        <input
                            type="text"
                            placeholder="Username"
                            className="w-full text-sm rounded-sm px-2 py-2 outline-none"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    </div>
                    <div className="space-y-2">
                        <label className="text-sm font-medium">Password</label>
                        <input
                            type="password"
                            placeholder="password"
                            className="w-full text-sm rounded-sm px-2 py-2 outline-none"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <button className="p-3 text-sm font-medium text-center text-white border rounded-md bg-primary">
                        Sign Up
                    </button>
                </form>
                <div>
                    <p>
                        Already have an account?
                        <Link to="/login" className="text-primary">
                            Sign in
                        </Link>
                    </p>
                    <Link to="/" className="text-primary">
                        Back to home
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Register;
