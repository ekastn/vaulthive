package dev.septian.vaulthiveserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.services.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public RawgPagedResponse<Game> searchGameByName(@RequestParam String name) {
        return gameService.findGameByName(name);
    }

    @GetMapping("/{id}")
    public Game getGameDetails(@PathVariable long id) {
        return gameService.findGameDetails(id);
    }

}