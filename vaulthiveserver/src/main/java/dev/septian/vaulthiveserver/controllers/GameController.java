package dev.septian.vaulthiveserver.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.responses.GameSearchResponse;
import dev.septian.vaulthiveserver.mappers.Mapper;
import dev.septian.vaulthiveserver.services.GameService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;
    private final Mapper<GameEntity, GameDto> gameMapper;

    public GameController(GameService gameService, Mapper<GameEntity, GameDto> gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping
    public ResponseEntity<List<GameSearchResponse>> searchGame(@RequestParam Map<String, String> params) {
        List<GameEntity> foundGames = gameService.findGames(params);
        List<GameDto> gameDtos = foundGames.stream()
                .map(gameMapper::mapTo)
                .collect(Collectors.toList());

        List<GameSearchResponse> gameResponses = gameDtos.stream()
                .map(game -> GameSearchResponse.builder()
                        .id(game.getId())
                        .name(game.getName())
                        .slug(game.getSlug())
                        .released(game.getReleased())
                        .imageUrl(game.getImageUrl())
                        .rating(game.getRating())
                        .genres(game.getGenres())
                        .platforms(game.getPlatforms())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(gameResponses, HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<GameSearchResponse>> getPopularGames() {
        List<GameEntity> popularGames = gameService.findPopularGames();
        List<GameDto> gameDtos = popularGames.stream()
                .map(gameMapper::mapTo)
                .collect(Collectors.toList());

        List<GameSearchResponse> gameResponses = gameDtos.stream()
                .map(game -> GameSearchResponse.builder()
                        .id(game.getId())
                        .name(game.getName())
                        .slug(game.getSlug())
                        .released(game.getReleased())
                        .imageUrl(game.getImageUrl())
                        .rating(game.getRating())
                        .genres(game.getGenres())
                        .platforms(game.getPlatforms())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(gameResponses, HttpStatus.OK);
    }

    @GetMapping("/recently-liked")
    public ResponseEntity<List<GameSearchResponse>> getRecentlyLikedGames() {
        List<GameEntity> recentlyLikedGames = gameService.findRecentlyLikedGames();
        List<GameDto> gameDtos = recentlyLikedGames.stream()
                .map(gameMapper::mapTo)
                .collect(Collectors.toList());

        List<GameSearchResponse> gameResponses = gameDtos.stream()
                .map(game -> GameSearchResponse.builder()
                        .id(game.getId())
                        .name(game.getName())
                        .slug(game.getSlug())
                        .released(game.getReleased())
                        .imageUrl(game.getImageUrl())
                        .rating(game.getRating())
                        .genres(game.getGenres())
                        .platforms(game.getPlatforms())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(gameResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameDetails(@PathVariable int id) {
        Optional<GameEntity> foundGame = gameService.findOne(id);
        return foundGame.map(game -> new ResponseEntity<>(gameMapper.mapTo(game), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/like")
    public ResponseEntity<Map<String, Boolean>> isLiked(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return new ResponseEntity<>(Map.of("isLiked", gameService.isLiked(id, user)), HttpStatus.OK);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Map<String, String>> likeGame(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        if (user == null) return new ResponseEntity<>(Map.of("error", "User not found"), HttpStatus.UNAUTHORIZED);
        try {
            gameService.likeGame(id, user);
            return new ResponseEntity<>(Map.of("message", "Game liked"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Error liking game: " + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<Map<String, String>> unlikeGame(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        try {
            gameService.unlikeGame(id, user);
            return new ResponseEntity<>(Map.of("message", "Game unliked"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Error unliking game: " + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}