package dev.septian.vaulthiveserver.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.ListEntity;
import dev.septian.vaulthiveserver.services.ListService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/lists")
public class ListController {

    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @PostMapping("/")
    public ResponseEntity<ListEntity> createList(@RequestBody ListEntity listGame) {
        ListEntity savedList = listService.save(listGame);
        return new ResponseEntity<>(savedList, HttpStatus.CREATED);
    }

}
