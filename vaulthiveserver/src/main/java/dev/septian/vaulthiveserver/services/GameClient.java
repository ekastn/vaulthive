package dev.septian.vaulthiveserver.services;

import java.util.Map;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.RawgPagedResponse;

public interface GameClient {
    RawgPagedResponse<Game> getData(String endpoint);
    RawgPagedResponse<Game> getData(String endpoint, Map<String, String> params);
    Game getDetails(String endpoint, long id);
}
