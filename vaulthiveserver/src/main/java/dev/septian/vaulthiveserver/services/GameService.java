package dev.septian.vaulthiveserver.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.septian.vaulthiveserver.domain.entities.GameEntity;

public interface GameService {

    Optional<GameEntity> findOne(int id);

    List<GameEntity> findGames(Map<String, String> params);

}
