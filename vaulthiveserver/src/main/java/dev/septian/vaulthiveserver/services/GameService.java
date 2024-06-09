package dev.septian.vaulthiveserver.services;

import java.util.Map;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.domain.dtos.GameDto;

public interface GameService {
    GameDto getGameDetails(long id);
    RawgPagedResponse<GameDto> searchGame(Map<String, String> name);
    RawgPagedResponse<GameDto> searchGameByName(String name);
}
