import defaultTheme from "tailwindcss/defaultTheme";
import daisyui from "daisyui";

/** @type {import('tailwindcss').Config} */
export default {
    content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        fontFamily: {
            sans: ["Inter", "sans-serif", ...defaultTheme.fontFamily.sans],
        },
        extend: {
            colors: {
                txMain: "#e9e8f1",
                bgMain: "#0a0814",
                primary: "#9e94e5",
                secondary: "#20108e",
                accent: "#4228fd",
            },
        },
    },
    plugins: [daisyui],
    daisyui: {
        themes: ["forest"],
    },
};
