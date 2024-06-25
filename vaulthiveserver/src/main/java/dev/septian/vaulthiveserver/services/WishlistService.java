package dev.septian.vaulthiveserver.services;

import java.util.Optional;

import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.entities.WishlistEntity;

public interface WishlistService {

    Optional<WishlistEntity> getWishlist(int gameId, UserEntity id);

    void addGameToWishlist(int gameId, UserEntity userEntity);

    void removeGameFromWishlist(int gameId, UserEntity userEntity) throws Exception;
    
}
