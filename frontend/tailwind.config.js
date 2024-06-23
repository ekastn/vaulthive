import defaultTheme from "tailwindcss/defaultTheme";
import daisyui from "daisyui";

/** @type {import('tailwindcss').Config} */
export default {
    content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        fontFamily: {
            sans: ["Inter", "sans-serif", ...defaultTheme.fontFamily.sans],
            serif: [...defaultTheme.fontFamily.serif],
            mono: [...defaultTheme.fontFamily.mono],
        },
        extend: {},
    },
    plugins: [daisyui],
    daisyui: {
        themes: [
            {
                mytheme: {
                    primary: "#7400ff",
                    secondary: "#009cff",
                    accent: "#00d3ff",
                    neutral: "#002425",
                    "base-100": "#141719",
                    info: "#00d2ff",
                    success: "#84d84e",
                    warning: "#a26800",
                    error: "#ff6e75",
                },
            },
        ],
    },
};
