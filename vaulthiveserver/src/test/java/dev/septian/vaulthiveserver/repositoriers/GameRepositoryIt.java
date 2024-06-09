package dev.septian.vaulthiveserver.repositoriers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.repositories.GameRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameRepositoryIt {

    private GameRepository underTest;

    @Autowired
    GameRepositoryIt(GameRepository gameRepository) {
        this.underTest = gameRepository;
    }

    @Test
    public void testThatGameCanBeCreatedAndRecalled() {
        GameEntity gameEntity = TestData.createGameEntity(1);

        GameEntity savedGame = underTest.save(gameEntity);

        assertThat(savedGame).isNotNull();
        assertThat(savedGame.getId()).isNotNull();
    }

    @Test
    public void testThatGameCanBeRetrieved() {
        GameEntity gameEntity = TestData.createGameEntity(1);

        GameEntity savedGame = underTest.save(gameEntity);

        Optional<GameEntity> foundGame = underTest.findById(savedGame.getId());

        assertThat(foundGame).isPresent();
        assertThat(foundGame.get().getName()).isEqualTo("Game 1");
    }

    @Test
    public void testThatGameCanBeUpdated() {
        GameEntity gameEntity = TestData.createGameEntity(1);

        GameEntity savedGame = underTest.save(gameEntity);

        GameEntity updatedGame = GameEntity.builder()
                .id(savedGame.getId())
                .name("Game 1 Updated")
                .description("Description of Game 1 Updated")
                .released("2021-01-01")
                .rating(5.0f)
                .build();

        GameEntity savedUpdatedGame = underTest.save(updatedGame);

        Optional<GameEntity> foundGame = underTest.findById(savedUpdatedGame.getId());

        assertThat(foundGame).isPresent();
        assertThat(foundGame.get().getName()).isEqualTo("Game 1 Updated");
    }

    @Test
    public void testThatGameCanBeDeleted() {
        GameEntity gameEntity = TestData.createGameEntity(1);

        GameEntity savedGame = underTest.save(gameEntity);

        underTest.deleteById(savedGame.getId());

        Optional<GameEntity> foundGame = underTest.findById(savedGame.getId());

        assertThat(foundGame).isEmpty();
    }

}
