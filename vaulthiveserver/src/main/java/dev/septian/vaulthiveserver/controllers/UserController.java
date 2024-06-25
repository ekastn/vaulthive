package dev.septian.vaulthiveserver.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.dtos.UserDto;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;
import dev.septian.vaulthiveserver.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        return new ResponseEntity<>(userMapper.mapTo(userEntity), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        Optional<UserEntity> userEntity = userService.findOne(id);
        if (userEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userMapper.mapTo(userEntity.get()), HttpStatus.OK);
        }
    }
    
}
