declare type GameSearch = {
    id: number;
    name: string;
    slug: string;
    released: Date;
    imageUrl: string;
}

declare type Game = {
    id: number;
    name: string;
    slug: string;
    description: string;
    released: string;
    imageUrl: string;
    rating: number;
}