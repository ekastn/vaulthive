declare type UserType = {
    id: number;
    username: string;
    email: string;
}

declare type UserTokenType = {
    id: number;
    username: string;
    email: string;
    token: string;
    expiresIn: number;
}