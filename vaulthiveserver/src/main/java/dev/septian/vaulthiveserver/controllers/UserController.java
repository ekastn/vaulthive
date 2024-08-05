package dev.septian.vaulthiveserver.controllers;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.UserDto;
import dev.septian.vaulthiveserver.domain.dtos.WishlistDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.entities.WishlistEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;
import dev.septian.vaulthiveserver.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;
    private final Mapper<GameEntity, GameDto> gameMapper;

   public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper, Mapper<GameEntity, GameDto> gameMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.gameMapper = gameMapper;
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
        }

        //FIXME: this is just to make it works
        UserDto userDto = userMapper.mapTo(userEntity.get());
        userDto.setWishlist(new HashSet<>());
        for (WishlistEntity wishlistEntity : userEntity.get().getWishlists()) {
            userDto.getWishlist().add(
                    WishlistDto.builder()
                            .game(gameMapper.mapTo(wishlistEntity.getGame()))
                            .build()
            );
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    
}
