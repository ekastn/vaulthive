package dev.septian.vaulthiveserver.services;

import dev.septian.vaulthiveserver.domain.Game;

public interface GameService {
    Game getGameDetails(long id);
}
