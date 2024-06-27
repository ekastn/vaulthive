package dev.septian.vaulthiveserver.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;

public interface GameService {

    Optional<GameEntity> findOne(int id);

    List<GameEntity> findGames(Map<String, String> params);

    boolean isLiked(int gameId, UserEntity userEntity);

    void likeGame(int gameId, UserEntity userEntity);

    void unlikeGame(int gameId, UserEntity userEntity);

    List<GameEntity> findPopularGames();

    List<GameEntity> findPopularGamesByDateRange(LocalDate startDate);

    List<GameEntity> findRecentlyLikedGames();

}
