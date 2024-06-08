package dev.septian.vaulthiveserver.services;

import java.util.Map;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.RawgPagedResponse;

public interface GameService {
    Game getGameDetails(long id);
    RawgPagedResponse<Game> searchGame(Map<String, String> name);
    RawgPagedResponse<Game> searchGameByName(String name);
}
