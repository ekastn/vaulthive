declare type ListPostRequest = {
    title: string;
    description: string;
    gameIds: number[];
};

declare type ListGame = {
    id: number;
    title: string;
    description: string;
    user: { id: number; username: string;}
    games: Game[];
};