package dev.septian.vaulthiveserver.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.responses.GameSearchResponse;
import dev.septian.vaulthiveserver.mappers.Mapper;
import dev.septian.vaulthiveserver.services.GameService;

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

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameDetails(@PathVariable int id) {
        Optional<GameEntity> foundGame = gameService.findOne(id);
        return foundGame.map(game -> new ResponseEntity<>(gameMapper.mapTo(game), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}