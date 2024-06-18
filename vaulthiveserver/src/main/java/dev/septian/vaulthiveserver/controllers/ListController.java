package dev.septian.vaulthiveserver.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.requests.ListRequest;
import dev.septian.vaulthiveserver.services.GameService;
import dev.septian.vaulthiveserver.services.ListService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/lists")
public class ListController {

    private final ListService listService;
    private final GameService gameService;

    private static final Logger logger = LoggerFactory.getLogger(ListController.class);

    public ListController(ListService listService, GameService gameService) {
        this.listService = listService;
        this.gameService = gameService;
    }

    @PostMapping("/")
    public ResponseEntity<ListEntity> createList(@RequestBody ListRequest listRequest) {
        ListEntity listEntity = ListEntity.builder()
                .title(listRequest.getTitle())
                .description(listRequest.getDescription())
                .build();

        listEntity.setGames(listRequest.getGameIds().stream()
                .map(gameId -> gameService.findOne(gameId).orElse(null))
                .filter(game -> game != null)
                .collect(Collectors.toSet()));

        logger.info("Creating list with title: {}", listEntity.getTitle());
        logger.info("Games in list: {}", listEntity.getGames().size());

        try {
            ListEntity savedList = listService.save(listEntity);
            logger.info("Saved list with ID: {}", savedList.getId());
            return new ResponseEntity<>(savedList, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Failed to save list: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ListEntity>> getLists() {
        return new ResponseEntity<>(listService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getList(@PathVariable int id) {
        Optional<ListEntity> foundList = listService.findOne(id);
        return foundList.map(listEntity -> {
            return new ResponseEntity<>(listEntity, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(@RequestBody ListEntity listEntity, @PathVariable int id) {
        if (!listService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ListEntity updatedList = listService.update(id, listEntity);
        return new ResponseEntity<>(updatedList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable int id) {
        if (!listService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        listService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
