package dev.septian.vaulthiveserver.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.GameSearchDto;
import dev.septian.vaulthiveserver.domain.entities.DeveloperEntity;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.GameSearchEntity;
import dev.septian.vaulthiveserver.domain.entities.GenreEntity;
import dev.septian.vaulthiveserver.domain.entities.PlatformEntity;
import dev.septian.vaulthiveserver.domain.entities.PublisherEntity;
import dev.septian.vaulthiveserver.domain.entities.ScreenshotEntity;
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

    Logger logger = LoggerFactory.getLogger(GameClient.class);

    public GameServiceImpl(
            GameClient gameClient,
            Mapper<GameEntity, GameDto> gameMapper,
            Mapper<GameSearchEntity, GameSearchDto> gameSearchMapper,
            GameRepository gameRepository,
            GameSearchRepository gameSearchRepository) {
        this.gameClient = gameClient;
        this.gameMapper = gameMapper;
        this.gameSearchMapper = gameSearchMapper;
        this.gameRepository = gameRepository;
        this.gameSearchRepository = gameSearchRepository;
    }

    @Override
    public Optional<GameEntity> findOne(int id) {
        Optional<GameEntity> foundGame = gameRepository.findById(id);
        if (foundGame.isEmpty()) {
            GameDto game = gameClient.getDetails(id);

            if (game == null) {
                return Optional.empty();
            }
            
            logger.info("Game: {}", game.toString());

            GameEntity gameEntity = gameMapper.mapFrom(game);
            Set<DeveloperEntity> developers = gameEntity.getDevelopers();
            Set<PublisherEntity> publishers = gameEntity.getPublishers();
            Set<GenreEntity> genres = gameEntity.getGenres();
            Set<PlatformEntity> platforms = gameEntity.getPlatforms();
            Set<ScreenshotEntity> screenshots = gameEntity.getScreenshots();

            developers.forEach(developer -> developer.addGame(gameEntity));
            publishers.forEach(publisher -> publisher.addGame(gameEntity));
            genres.forEach(genre -> genre.addGame(gameEntity));
            platforms.forEach(platform -> platform.addGame(gameEntity));
            screenshots.forEach(screenshot -> screenshot.setGame(gameEntity));

            gameEntity.setDevelopers(developers);
            gameEntity.setPublishers(publishers);
            gameEntity.setGenres(genres);
            gameEntity.setPlatforms(platforms);
            gameEntity.setScreenshots(screenshots);

            GameEntity saved = gameRepository.save(gameEntity);

            return Optional.of(saved);
        }
        return foundGame;
    }

    @Override
    public List<GameSearchDto> findGames(Map<String, String> params) {
        List<GameSearchEntity> foundGames = gameSearchRepository.findByNameContainingIgnoreCase(params.get("search"));

        if (foundGames.isEmpty()) {
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
