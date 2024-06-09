package dev.septian.vaulthiveserver.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.GameSearchDto;

public interface GameService {

    Optional<GameDto> findOne(int id);

    List<GameSearchDto> findGames(Map<String, String> params);

}
