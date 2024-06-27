package dev.septian.vaulthiveserver.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT g FROM GameEntity g LEFT JOIN g.likes l GROUP BY g.id ORDER BY COUNT(l) DESC LIMIT 10")
    List<GameEntity> findPopularGames();

    @Query("SELECT g FROM GameEntity g LEFT JOIN g.likes l WHERE l.createdAt >= :startDate GROUP BY g.id ORDER BY COUNT(l) DESC LIMIT 10")
    List<GameEntity> findPopularGamesByDateRange(LocalDate startDate);

    @Query("SELECT g FROM GameEntity g LEFT JOIN g.likes l GROUP BY g.id ORDER BY MAX(l.createdAt) DESC LIMIT 10")
    List<GameEntity> findRecentlyLikedGames();

}
