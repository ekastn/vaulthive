type Common = {
    id: number;
    name: string;
    slug: string;
};

type Screenshot = {
    id: number;
    image: string;
    width: number;
    height: number;
};

declare type GameSearch = {
    id: number;
    name: string;
    slug: string;
    released: Date;
    imageUrl: string;
    rating: number;
    genres: Common[];
    platforms: Common[];
};

declare type Game = {
    id: number;
    name: string;
    slug: string;
    description: string;
    released: string;
    imageUrl: string;
    rating: number;
};

declare type GameDetails = {
    id: number;
    name: string;
    slug: string;
    description: string;
    released: string;
    rating: number;
    imageUrl: string;
    developers: Common[];
    publishers: Common[];
    genres: Common[];
    platforms: Common[];
    screenshots: Screenshot[];
};
