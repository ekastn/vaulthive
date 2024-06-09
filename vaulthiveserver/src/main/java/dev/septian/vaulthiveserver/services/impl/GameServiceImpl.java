package dev.septian.vaulthiveserver.services.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.services.GameService;
import dev.septian.vaulthiveserver.services.GameClient;

@Service
public class GameServiceImpl implements GameService {

    private final GameClient rawgClient;

    public GameServiceImpl(GameClient rawgClient) {
        this.rawgClient = rawgClient;
    }

    @Override
    public GameDto getGameDetails(long id) {
        return rawgClient.getDetails("/games", id);
    }

    @Override
    public RawgPagedResponse<GameDto> searchGame(Map<String, String> params) {
        return rawgClient.getData("/games", params);
    }

    @Override
    public RawgPagedResponse<GameDto> searchGameByName(String name) {
        return rawgClient.getData("/games", Map.of("search", name));
    }
}
