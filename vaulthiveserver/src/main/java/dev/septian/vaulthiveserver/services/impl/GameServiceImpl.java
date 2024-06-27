package dev.septian.vaulthiveserver.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.entities.DeveloperEntity;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.GameLikeEntity;
import dev.septian.vaulthiveserver.domain.entities.GenreEntity;
import dev.septian.vaulthiveserver.domain.entities.PlatformEntity;
import dev.septian.vaulthiveserver.domain.entities.PublisherEntity;
import dev.septian.vaulthiveserver.domain.entities.ScreenshotEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.repositories.GameLikeRepository;
import dev.septian.vaulthiveserver.repositories.GameRepository;
import dev.septian.vaulthiveserver.services.GameService;
import dev.septian.vaulthiveserver.services.enums.FilterType;
import dev.septian.vaulthiveserver.services.GameClient;

@Service
public class GameServiceImpl implements GameService {

    private final GameClient gameClient;
    private final GameRepository gameRepository;
    private final GameLikeRepository gameLikeRepository;

    Logger logger = LoggerFactory.getLogger(GameClient.class);

    private boolean isFetchingToRawgApi = false;

    public GameServiceImpl(GameClient gameClient, GameRepository gameRepository, GameLikeRepository gameLikeRepository) {
        this.gameClient = gameClient;
        this.gameRepository = gameRepository;
        this.gameLikeRepository = gameLikeRepository;
    }

    @Override
    public Optional<GameEntity> findOne(int id) {
        Optional<GameEntity> foundGame = gameRepository.findById(id);

        if (foundGame.isEmpty() || !checkIfIsNull(foundGame)) {
            logger.info("Game not found in database, fetching from API");
            GameEntity gameEntity = gameClient.getDetails(id);

            if (gameEntity == null) {
                return Optional.empty();
            }

            logger.info("Mapping game to entity");
            Set<DeveloperEntity> developers = gameEntity.getDevelopers();
            Set<PublisherEntity> publishers = gameEntity.getPublishers();
            Set<GenreEntity> genres = gameEntity.getGenres();
            Set<PlatformEntity> platforms = gameEntity.getPlatforms();
            Set<ScreenshotEntity> screenshots = gameEntity.getScreenshots();

            logger.info("Adding game to developers, publishers, genres, platforms");
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
    public List<GameEntity> findGames(Map<String, String> params) {
        List<GameEntity> foundGames = getGameByFilter(params);

        if (isFetchingToRawgApi) {
            if (foundGames.isEmpty()) {
                return List.of();
            }

            for (GameEntity newGame : foundGames) {
                logger.debug("checking existing game");
                if (gameRepository.findById(newGame.getId()).isPresent()) {
                    continue;
                }

                logger.debug("adding game to genres, platforms, screenshots");
                Set<GenreEntity> genres = newGame.getGenres();
                Set<PlatformEntity> platforms = newGame.getPlatforms();
                Set<ScreenshotEntity> screenshots = newGame.getScreenshots();

                genres.forEach(genre -> genre.addGame(newGame));
                platforms.forEach(platform -> platform.addGame(newGame));
                screenshots.forEach(screenshot -> screenshot.setGame(newGame));

                try {
                    logger.debug("saving game");
                    gameRepository.save(newGame);
                } catch (Exception e) {
                    logger.debug("Error saving game: {}", e.getMessage());
                }
            }
        }

        return foundGames;
    }

    @Override
    public boolean isLiked(int gameId, UserEntity userEntity) {
        Optional<GameLikeEntity> gameLikeEntity = gameLikeRepository.findByGame_IdAndUser_Id(gameId, userEntity.getId());
        return gameLikeEntity.isPresent();
    }

    @Override
    public void likeGame(int gameId, UserEntity userEntity) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);

        if (gameEntity.isEmpty()) {
            throw new RuntimeException("Game not found");
        }

        GameLikeEntity gameLikeEntity = GameLikeEntity.builder()
                .game(gameEntity.get())
                .user(userEntity)
                .build();
        
        gameLikeRepository.save(gameLikeEntity);
    }

    @Override
    public void unlikeGame(int gameId, UserEntity userEntity) {
        Optional<GameLikeEntity> gameLikeEntity = gameLikeRepository.findByGame_IdAndUser_Id(gameId, userEntity.getId());

        if (gameLikeEntity.isEmpty()) {
            throw new RuntimeException("Game like not found");
        }

        gameLikeEntity.get().getUser().getGameLikes().remove(gameLikeEntity.get());
        gameLikeEntity.get().getGame().getLikes().remove(gameLikeEntity.get());

        gameLikeRepository.delete(gameLikeEntity.get());
    }

    @Override
    public List<GameEntity> findPopularGames() {
        return gameRepository.findPopularGames();
    }

    @Override
    public List<GameEntity> findPopularGamesByDateRange(LocalDate startDate) {
        return gameRepository.findPopularGamesByDateRange(startDate);
    }

    @Override
    public List<GameEntity> findRecentlyLikedGames() {
        return gameRepository.findRecentlyLikedGames();
    }

    private List<GameEntity> getGameByFilter(Map<String, String> params) {
        switch (FilterType.valueOf(params.get("filter").toUpperCase())) {
            case FilterType.NAME:
                logger.info("searching games by name");
                List<GameEntity> foundGamesByName = gameRepository.findByNameContainingIgnoreCase(params.get("value"));

                if (foundGamesByName.isEmpty()) {
                    Map<String, String> searchParam = Map.of("search", params.get("value"));
                    isFetchingToRawgApi = true;
                    return gameClient.getData(searchParam);
                }

                isFetchingToRawgApi = false;
                return foundGamesByName;

            case FilterType.PLATFORM:
                logger.info("searching games by platform");
                List<GameEntity> foundGamesByPlatform = gameRepository
                        .findByPlatforms_NameOrSlugContainingIgnoreCase(params.get("value"), params.get("value"));

                if (foundGamesByPlatform.isEmpty()) {
                    Map<String, String> platformParam = Map.of("parent_platforms", params.get("value"));
                    isFetchingToRawgApi = true;
                    return gameClient.getData(platformParam);
                }

                isFetchingToRawgApi = false;
                return foundGamesByPlatform;

            case FilterType.GENRE:
                logger.info("searching games by genre");
                List<GameEntity> foundGamesByGenre = gameRepository
                        .findByGenres_NameOrSlugContainingIgnoreCase(params.get("value"), params.get("value"));

                if (foundGamesByGenre.isEmpty()) {
                    Map<String, String> genreParam = Map.of("genres", params.get("value"));
                    isFetchingToRawgApi = true;
                    return gameClient.getData(genreParam);
                }

                isFetchingToRawgApi = false;
                return foundGamesByGenre;

            default:
                logger.info("searching all games");
                isFetchingToRawgApi = false;
                return gameRepository.findAll();
        }
    }

    public boolean checkIfIsNull(Optional<GameEntity> optionalGameEntity) {
        GameEntity gameEntity = optionalGameEntity.get();

        return gameEntity.getId() != null &&
                gameEntity.getDescription() != null &&
                gameEntity.getDevelopers() != null && !gameEntity.getDevelopers().isEmpty() &&
                gameEntity.getPublishers() != null && !gameEntity.getPublishers().isEmpty() &&
                gameEntity.getGenres() != null && !gameEntity.getGenres().isEmpty() &&
                gameEntity.getPlatforms() != null && !gameEntity.getPlatforms().isEmpty();
    }

}
