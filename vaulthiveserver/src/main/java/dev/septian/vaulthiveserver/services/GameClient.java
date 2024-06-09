package dev.septian.vaulthiveserver.services;

import java.util.Map;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.GameSearchDto;

public interface GameClient {
    RawgPagedResponse<GameDto> getData();
    RawgPagedResponse<GameSearchDto> getData(Map<String, String> params);
    GameDto getDetails(int id);
}
