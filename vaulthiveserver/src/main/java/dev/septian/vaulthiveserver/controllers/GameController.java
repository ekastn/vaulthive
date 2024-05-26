package dev.septian.vaulthiveserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.PagedResponse;
import dev.septian.vaulthiveserver.services.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }


    // endpoint for searching games by name using uri like /api/games?search={name}
    @GetMapping
    public PagedResponse<Game> searchGameByName(@RequestParam String name) {
        return gameService.findGameByName(name);
    }



    // @GetMapping("/search/{name}")
    // public PagedResponse<Game> searchGameByName(@PathVariable String name) {
    //     return gameService.findGameByName(name);
    // }

    @GetMapping("/{id}")
    public Game getGameDetails(@PathVariable long id) {
        return gameService.findGameDetails(id);
    }

}