package dev.septian.vaulthiveserver.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.GameSearchDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.GameSearchEntity;
import dev.septian.vaulthiveserver.domain.responses.RawgPagedResponse;
import dev.septian.vaulthiveserver.repositories.GameRepository;
import dev.septian.vaulthiveserver.repositories.GameSearchRepository;
import dev.septian.vaulthiveserver.services.GameService;
import dev.septian.vaulthiveserver.services.GameClient;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Service
public class GameServiceImpl implements GameService {

    private final GameClient gameClient;
    private final Mapper<GameEntity, GameDto> gameMapper;
    private final Mapper<GameSearchEntity, GameSearchDto> gameSearchMapper;
    private final GameRepository gameRepository;
    private final GameSearchRepository gameSearchRepository;

    public GameServiceImpl(
            GameClient rawgClient,
            Mapper<GameEntity, GameDto> gameMapper,
            Mapper<GameSearchEntity, GameSearchDto> gameSearchMapper,
            GameRepository gameRepository,
            GameSearchRepository gameSearchRepository) {

        this.gameClient = rawgClient;
        this.gameMapper = gameMapper;
        this.gameSearchMapper = gameSearchMapper;
        this.gameRepository = gameRepository;
        this.gameSearchRepository = gameSearchRepository;
    }

    @Override
    public Optional<GameDto> findOne(int id) {
        Optional<GameEntity> foundGame = gameRepository.findById(id);
        if (foundGame.isEmpty()) {
            GameDto game = gameClient.getDetails(id);
            if (game == null) {
                return Optional.empty();
            }
            GameEntity newGame = gameMapper.mapFrom(game);
            gameRepository.save(newGame);
            return Optional.of(game);
        }
        return Optional.of(gameMapper.mapTo(foundGame.get()));
    }

    @Override
    public List<GameSearchDto> findGames(Map<String, String> params) {
        List<GameSearchEntity> foundGames = gameSearchRepository.findByNameContainingIgnoreCase(params.get("search"));

        if (foundGames.isEmpty()){
            RawgPagedResponse<GameSearchDto> response = gameClient.getData(params);

            if (response == null) {
                return List.of();
            }

            Set<Integer> existingGames = gameSearchRepository.findAll().stream()
                .map(GameSearchEntity::getId)
                .collect(Collectors.toSet());

            List<GameSearchDto> newGames = response.getResults();
            for (GameSearchDto newGame : newGames) {
                if (existingGames.contains(newGame.getId())) {
                    continue;
                }

                GameSearchEntity game = gameSearchMapper.mapFrom(newGame);

                gameSearchRepository.save(game);
            }

            return newGames;
        }

        return foundGames.stream()
                .map(gameSearchMapper::mapTo)
                .collect(Collectors.toList());
    }

}
