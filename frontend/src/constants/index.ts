export const NAVIGATION = [
    {
        id: crypto.randomUUID(),
        title: "Home",
        path: "/",
    },
    {
        id: crypto.randomUUID(),
        title: "Games",
        path: "/games",
    },
    {
        id: crypto.randomUUID(),
        title: "Lists",
        path: "/lists",
    },
];
export const FEATURES = [
    {
        title: "Discover New Games",
        description: "Explore a vast collection of games and discover new favorites from various genres and platforms.",
    },
    {
        title: "Create Personal Lists",
        description: "Organize your favorite games into custom lists and keep track of the ones you want to play next.",
    },
    {
        title: "Rate and Review",
        description:
            "Share your thoughts and experiences by rating and reviewing games, and see what others have to say.",
    },
];

export const PLATFORMS = [
    {
        id: 1,
        name: "PC",
        slug: "pc",
    },
    {
        id: 2,
        name: "PlayStation",
        slug: "playstation",
    },
    {
        id: 3,
        name: "Xbox",
        slug: "xbox",
    },
    {
        id: 4,
        name: "IOS",
        slug: "ios",
    },
    {
        id: 8,
        name: "Android",
        slug: "android",
    },
    {
        id: 7,
        name: "Nintendo",
        slug: "nintendo",
    },
];

export const GENRES = [
    {
        id: crypto.randomUUID(),
        name: "Action",
        slug: "action",
    },
    {
        id: crypto.randomUUID(),
        name: "Adventure",
        slug: "adventure",
    },
    {
        id: crypto.randomUUID(),
        name: "RPG",
        slug: "roe-playing-game",
    },
    {
        id: crypto.randomUUID(),
        name: "Simulation",
        slug: "simulation",
    },
    {
        id: crypto.randomUUID(),
        name: "Shooter",
        slug: "shooter",
    },
    {
        id: crypto.randomUUID(),
        name: "Sports",
        slug: "sports",
    },
    {
        id: crypto.randomUUID(),
        name: "Puzzle",
        slug: "puzzle",
    },
    {
        id: crypto.randomUUID(),
        name: "Racing",
        slug: "racing",
    },
    {
        id: crypto.randomUUID(),
        name: "Fighting",
        slug: "fighting",
    },
];

export const FILTER_TYPES = {
    PLATFORM: "platform",
    GENRE: "genre",
    NAME: "search",
};