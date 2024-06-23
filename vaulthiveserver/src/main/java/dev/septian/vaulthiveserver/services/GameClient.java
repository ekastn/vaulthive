package dev.septian.vaulthiveserver.services;

import java.util.List;
import java.util.Map;

import dev.septian.vaulthiveserver.domain.entities.GameEntity;

public interface GameClient {

    List<GameEntity> getData(Map<String, String> params);

    GameEntity getDetails(int id);

}
