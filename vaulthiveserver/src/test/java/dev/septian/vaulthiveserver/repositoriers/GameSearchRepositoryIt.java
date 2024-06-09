package dev.septian.vaulthiveserver.repositoriers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.domain.entities.GameSearchEntity;
import dev.septian.vaulthiveserver.repositories.GameSearchRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameSearchRepositoryIt {

    private GameSearchRepository underTest;

    @Autowired
    GameSearchRepositoryIt(GameSearchRepository gameSearchRepository) {
        this.underTest = gameSearchRepository;
    }

    @Test
    public void testThatGameSearchCanBeCreatedAndRecalled() {
        GameSearchEntity gameSearchEntity = GameSearchEntity.builder()
                .id(1)
                .name("Game 1")
                .slug("game-1")
                .released("2021-01-01")
                .imageUrl("https://example.com/image.jpg")
                .build();

        GameSearchEntity savedGameSearch = underTest.save(gameSearchEntity);

        assertThat(savedGameSearch).isNotNull();
        assertThat(savedGameSearch.getId()).isNotNull();
    }

    @Test
    public void testThatGameSearchCanBeRetrieved() {
        GameSearchEntity gameSearchEntity = GameSearchEntity.builder()
                .id(1)
                .name("Game 1")
                .slug("game-1")
                .released("2021-01-01")
                .imageUrl("https://example.com/image.jpg")
                .build();

        GameSearchEntity savedGameSearch = underTest.save(gameSearchEntity);

        Optional<GameSearchEntity> foundGameSearch = underTest.findById(savedGameSearch.getId());

        assertThat(foundGameSearch).isPresent();
        assertThat(foundGameSearch.get().getId()).isEqualTo(1);
        assertThat(foundGameSearch.get().getName()).isEqualTo("Game 1");
        assertThat(foundGameSearch.get().getSlug()).isEqualTo("game-1");
    }

    @Test
    public void testThatGameSearchCanBeUpdated() {
        GameSearchEntity gameSearchEntity = GameSearchEntity.builder()
                .id(1)
                .name("Game 1")
                .slug("game-1")
                .released("2021-01-01")
                .imageUrl("https://example.com/image.jpg")
                .build();

        GameSearchEntity savedGameSearch = underTest.save(gameSearchEntity);

        GameSearchEntity updatedGameSearch = GameSearchEntity.builder()
                .id(savedGameSearch.getId())
                .name("Game 1 Updated")
                .slug("game-1-updated")
                .released("2021-01-01")
                .imageUrl("https://example.com/image.jpg")
                .build();

        GameSearchEntity updatedGameSearchEntity = underTest.save(updatedGameSearch);

        Optional<GameSearchEntity> foundGameSearch = underTest.findById(updatedGameSearchEntity.getId());
        
        assertThat(foundGameSearch).isPresent();
        assertThat(foundGameSearch.get().getName()).isEqualTo("Game 1 Updated");
        assertThat(foundGameSearch.get().getSlug()).isEqualTo("game-1-updated");
    }

    @Test
    public void testThatGameSearchCanBeDeleted() {
        GameSearchEntity gameSearchEntity = GameSearchEntity.builder()
                .id(1)
                .name("Game 1")
                .slug("game-1")
                .released("2021-01-01")
                .imageUrl("https://example.com/image.jpg")
                .build();

        GameSearchEntity savedGameSearch = underTest.save(gameSearchEntity);

        underTest.deleteById(savedGameSearch.getId());

        Optional<GameSearchEntity> foundGameSearch = underTest.findById(savedGameSearch.getId());

        assertThat(foundGameSearch).isEmpty();
    }

    @Test
    public void testThatGameSearchCanBeFoundByName() {
        GameSearchEntity gameSearchEntity = GameSearchEntity.builder()
                .id(1)
                .name("Game 1")
                .slug("game-1")
                .released("2021-01-01")
                .imageUrl("https://example.com/image.jpg")
                .build();

        underTest.save(gameSearchEntity);

        Optional<GameSearchEntity> foundGameSearch = underTest.findByNameContainingIgnoreCase("Game 1").stream().findFirst();

        assertThat(foundGameSearch).isPresent();
        assertThat(foundGameSearch.get().getName()).isEqualTo("Game 1");
    }

}
