package dev.septian.vaulthiveserver.services;

import java.util.Optional;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;

public interface GameService {

    Optional<GameDto> findOne(int id);

}
