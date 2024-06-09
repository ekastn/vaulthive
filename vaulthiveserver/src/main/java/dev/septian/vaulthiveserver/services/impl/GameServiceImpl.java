package dev.septian.vaulthiveserver.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.repositories.GameRepository;
import dev.septian.vaulthiveserver.services.GameService;
import dev.septian.vaulthiveserver.services.GameClient;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Service
public class GameServiceImpl implements GameService {

    private final GameClient rawgClient;
    private final Mapper<GameEntity, GameDto> gameMapper;
    private final GameRepository gameRepository;

    public GameServiceImpl(GameClient rawgClient, Mapper<GameEntity, GameDto> gameMapper, GameRepository gameRepository) {
        this.rawgClient = rawgClient;
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<GameDto> findOne(int id) {
        Optional<GameEntity> gameEntity = gameRepository.findById(id);
        if (gameEntity.isEmpty()) {
            GameDto game = rawgClient.getDetails(id);
            if (game == null) {
                return Optional.empty();
            }
            GameEntity newGame = gameMapper.mapFrom(game);
            gameRepository.save(newGame);
            return Optional.of(game);
        }
        return Optional.of(gameMapper.mapTo(gameEntity.get()));
    }
}
