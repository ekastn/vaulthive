package dev.septian.vaulthiveserver.services;

import java.util.Map;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.domain.dtos.GameDto;

public interface GameClient {
    RawgPagedResponse<GameDto> getData();
    RawgPagedResponse<GameDto> getData(Map<String, String> params);
    GameDto getDetails(int id);
}
