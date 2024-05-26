package dev.septian.vaulthiveserver.services;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.PagedResponse;

public interface GameService {
    Game findGameDetails(long id);
    PagedResponse<Game> findGameByName(String name);
}
