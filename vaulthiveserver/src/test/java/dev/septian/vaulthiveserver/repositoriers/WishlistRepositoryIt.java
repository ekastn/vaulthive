package dev.septian.vaulthiveserver.repositoriers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.entities.WishlistEntity;
import dev.septian.vaulthiveserver.domain.keys.WishlistKey;
import dev.septian.vaulthiveserver.repositories.GameRepository;
import dev.septian.vaulthiveserver.repositories.UserRepository;
import dev.septian.vaulthiveserver.repositories.WishlistRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WishlistRepositoryIt {

    private final WishlistRepository underTest;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public WishlistRepositoryIt(WishlistRepository wishlistRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.underTest = wishlistRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Test
    public void testThatWishlistCanBeCreatedAndRecalled() {
        // UserEntity userEntity = UserEntity.builder()
        //         .username("user1")
        //         .password("password")
        //         .email("user1@test.com")
        //         .build();
        // userRepository.save(userEntity);

        // GameEntity gameEntity = GameEntity.builder()
        //         .id(1)
        //         .name("Game 1")
        //         .description("Description of Game 1")
        //         .released("2021-01-01")
        //         .rating(5.0f)
        //         .build();
        // gameRepository.save(gameEntity);

        // WishlistKey wishlistKey = WishlistKey.builder()
        //         .game(gameEntity)
        //         .user(userEntity)
        //         .build();

        // WishlistEntity wishlistEntity = WishlistEntity.builder()
        //         .id(wishlistKey)
        //         .build();
        
        // userEntity.addWishlist(wishlistEntity);

        // WishlistEntity savedWishlist = underTest.save(wishlistEntity);

        // assertThat(savedWishlist).isNotNull();
        // assertThat(savedWishlist.getId()).isNotNull();
    }
    
}
