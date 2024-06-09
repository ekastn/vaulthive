package dev.septian.vaulthiveserver.services;

import java.util.Map;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.domain.dtos.GameDto;

public interface GameClient {
    RawgPagedResponse<GameDto> getData(String endpoint);
    RawgPagedResponse<GameDto> getData(String endpoint, Map<String, String> params);
    GameDto getDetails(String endpoint, long id);
}
