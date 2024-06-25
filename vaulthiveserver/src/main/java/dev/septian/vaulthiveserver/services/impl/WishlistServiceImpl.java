package dev.septian.vaulthiveserver.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.entities.WishlistEntity;
import dev.septian.vaulthiveserver.repositories.GameRepository;
import dev.septian.vaulthiveserver.repositories.WishlistRepository;
import dev.septian.vaulthiveserver.services.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final GameRepository gameRepository;

    private Logger logger = LoggerFactory.getLogger(WishlistServiceImpl.class);

    public WishlistServiceImpl(WishlistRepository wishlistRepository, GameRepository gameRepository) {
        this.wishlistRepository = wishlistRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<WishlistEntity> getWishlist(int gameId, UserEntity userEntity) {
        Optional<GameEntity> game = gameRepository.findById(gameId);

        if (game.isEmpty()) {
            return Optional.empty();
        }

        return wishlistRepository.findByGame_IdAndUser_Id(gameId, userEntity.getId());
    }

    @Override
    public void addGameToWishlist(int gameId, UserEntity userEntity) {
        Optional<GameEntity> game = gameRepository.findById(gameId);

        if (game.isEmpty()) {
            return;
        }

        WishlistEntity wishlistEntity = WishlistEntity.builder()
                .game(game.get())
                .user(userEntity)
                .build();

        userEntity.addWishlist(wishlistEntity);

        wishlistRepository.save(wishlistEntity);
    }

    @Override
    public void removeGameFromWishlist(int gameId, UserEntity userEntity) throws Exception {
        Optional<WishlistEntity> wishlist = wishlistRepository.findByGame_IdAndUser_Id(gameId, userEntity.getId());

        if (!wishlist.isPresent()) {
            throw new Exception("Wishlist not found");   
        }

        wishlist.get().getUser().getWishlists().remove(wishlist.get());
        wishlistRepository.delete(wishlist.get());
    }
    
}
