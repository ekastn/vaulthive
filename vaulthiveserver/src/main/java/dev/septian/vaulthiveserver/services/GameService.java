package dev.septian.vaulthiveserver.services;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.RawgPagedResponse;

public interface GameService {
    Game findGameDetails(long id);
    RawgPagedResponse<Game> findGameByName(String name);
}
