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
public class GameEntityRepositoryIt {

    private GameRepository gameRepository;

    @Autowired
    GameEntityRepositoryIt(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // @Test
    // public void testThatGameCanBeCreatedAndRecalled() {
    //     GameEntity gameEntity = TestData.createGameEntity(14);

    //     gameRepository.save(gameEntity);

    //     Optional<GameEntity> result = gameRepository.findById(gameEntity.getId());
    //     assertThat(result).isPresent();
    //     assertThat(result.get()).isEqualTo(gameEntity);
    // }

    // @Test
    // public void testCreateGame() {
    // Game game = new Game(); // add necessary data to the game
    // Game savedGame = gameRepository.save(game);
    // assertThat(savedGame).isNotNull();
    // assertThat(savedGame.getId()).isNotNull();
    // }

    // @Test
    // public void testReadGame() {
    // Game game = new Game(); // add necessary data to the game
    // Game savedGame = gameRepository.save(game);
    // Game retrievedGame = gameRepository.findById(savedGame.getId()).orElse(null);
    // assertThat(retrievedGame).isNotNull();
    // assertThat(retrievedGame.getId()).isEqualTo(savedGame.getId());
    // }

    // @Test
    // public void testUpdateGame() {
    // Game game = new Game(); // add necessary data to the game
    // Game savedGame = gameRepository.save(game);
    // // update some fields of savedGame
    // Game updatedGame = gameRepository.save(savedGame);
    // Game retrievedGame =
    // gameRepository.findById(updatedGame.getId()).orElse(null);
    // // assert that the retrieved game's updated fields match the new values
    // }

    // @Test
    // public void testDeleteGame() {
    // Game game = new Game(); // add necessary data to the game
    // Game savedGame = gameRepository.save(game);
    // gameRepository.delete(savedGame);
    // Game retrievedGame = gameRepository.findById(savedGame.getId()).orElse(null);
    // assertThat(retrievedGame).isNull();
    // }
}
