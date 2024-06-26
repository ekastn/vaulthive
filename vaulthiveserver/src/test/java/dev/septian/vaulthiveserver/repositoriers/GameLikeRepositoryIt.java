package dev.septian.vaulthiveserver.repositoriers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.GameLikeEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.repositories.GameLikeRepository;
import dev.septian.vaulthiveserver.repositories.GameRepository;
import dev.septian.vaulthiveserver.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameLikeRepositoryIt {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameLikeRepository underTest;

    @Autowired
    public GameLikeRepositoryIt(UserRepository userRepository, GameLikeRepository underTest, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.underTest = underTest;
    }

    @Test
    public void testThatLikeCanBeCreatedAndRecalled() {
        UserEntity userEntity = TestData.createUserEntity();
        GameEntity gameEntity = TestData.createGameEntity();

        userRepository.save(userEntity);
        gameRepository.save(gameEntity);

        GameLikeEntity gameLikeEntity = GameLikeEntity.builder()
                .user(userEntity)
                .game(gameEntity)
                .build();
        
        userEntity.addGameLike(gameLikeEntity);

        GameLikeEntity saved = underTest.save(gameLikeEntity);

        Optional<GameLikeEntity> response = underTest.findById(saved.getId());

        assertThat(response).isPresent();
        assertThat(response.get().getId()).isEqualTo(saved.getId());
        assertThat(response.get().getCreatedAt().toString()).isEqualTo("2024-06-26");
        assertThat(response.get().getUser().getId()).isEqualTo(userEntity.getId());
        assertThat(response.get().getGame().getId()).isEqualTo(gameEntity.getId());
    }

    @Test
    public void testThatLikeCanBeDeleted() {
        UserEntity userEntity = TestData.createUserEntity();
        GameEntity gameEntity = TestData.createGameEntity();

        userRepository.save(userEntity);
        gameRepository.save(gameEntity);

        GameLikeEntity gameLikeEntity = GameLikeEntity.builder()
                .user(userEntity)
                .game(gameEntity)
                .build();
        
        userEntity.addGameLike(gameLikeEntity);
        gameEntity.addLike(gameLikeEntity);

        underTest.save(gameLikeEntity);

        Optional<GameLikeEntity> found = underTest.findByGame_IdAndUser_Id(gameEntity.getId(), userEntity.getId());
        assertThat(found).isPresent();

        found.get().getUser().getGameLikes().remove(found.get());
        found.get().getGame().getLikes().remove(found.get());

        underTest.delete(found.get());

        Optional<GameLikeEntity> response = underTest.findById(found.get().getId());

        assertThat(response.isPresent()).isFalse();
    }
    
}
