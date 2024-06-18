package dev.septian.vaulthiveserver.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.dtos.ListDto;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.requests.ListRequest;
import dev.septian.vaulthiveserver.mappers.Mapper;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final Mapper<ListEntity, ListDto> listMapper;

    private static final Logger logger = LoggerFactory.getLogger(ListController.class);

    public ListController(ListService listService, GameService gameService, Mapper<ListEntity, ListDto> listMapper) {
        this.listService = listService;
        this.gameService = gameService;
        this.listMapper = listMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ListDto> createList(@RequestBody ListRequest listRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = (UserEntity) auth.getPrincipal();        

        ListEntity listEntity = ListEntity.builder()
                .title(listRequest.getTitle())
                .description(listRequest.getDescription())
                .build();

        listEntity.setUser(userEntity);
        listEntity.setGames(listRequest.getGameIds().stream()
                .map(gameId -> gameService.findOne(gameId).orElse(null))
                .filter(game -> game != null)
                .collect(Collectors.toSet()));

        try {
            ListEntity createdList = listService.save(listEntity);
            logger.info("List created: {}", createdList);
            return new ResponseEntity<>(listMapper.mapTo(createdList), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating list: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ListDto>> getLists() {
        List<ListEntity> lists = listService.findAll();
        List<ListDto> listDtos = lists.stream()
                .map(listMapper::mapTo)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListDto> getList(@PathVariable int id) {
        Optional<ListEntity> foundList = listService.findOne(id);
        if (foundList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listMapper.mapTo(foundList.get()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ListDto> updateList(@RequestBody ListEntity listEntity, @PathVariable int id) {
        if (!listService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ListEntity updatedList = listService.update(id, listEntity);
        return new ResponseEntity<>(listMapper.mapTo(updatedList), HttpStatus.OK);
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
