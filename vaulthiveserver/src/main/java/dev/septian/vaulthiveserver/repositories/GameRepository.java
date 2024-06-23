package dev.septian.vaulthiveserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.GenreEntity;
import dev.septian.vaulthiveserver.domain.entities.PlatformEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {

    List<GameEntity> findByName(String name);

    List<GameEntity> findByNameContainingIgnoreCase(String name);

    List<GameEntity> findByGenres_NameOrSlugContainingIgnoreCase(String name, String slug);

    List<GameEntity> findByPlatforms_NameOrSlugContainingIgnoreCase(String name, String slug);

    boolean existsByGenresAndId(GenreEntity genre, Integer id);

    boolean existsByPlatformsAndId(PlatformEntity platform, Integer id);

}
