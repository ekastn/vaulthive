package dev.septian.vaulthiveserver.controllers;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.entities.WishlistEntity;
import dev.septian.vaulthiveserver.services.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    WishlistService wishlistService;

    private Logger logger = LoggerFactory.getLogger(WishlistController.class);

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Map<String, Boolean>> getWishlist(@PathVariable int gameId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        Optional<WishlistEntity> wishlist = wishlistService.getWishlist(gameId, user);
        return new ResponseEntity<>(Map.of("isInWishlist", wishlist.isPresent()), HttpStatus.OK);
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<Map<String, String>> addGameToWishlist(@PathVariable int gameId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        try {
            wishlistService.addGameToWishlist(gameId, user);
            return new ResponseEntity<>(Map.of("message", "Game added to wishlist"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Error adding game to wishlist"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Map<String, String>> removeGameFromWishlist(@PathVariable int gameId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        try {
            wishlistService.removeGameFromWishlist(gameId, user);
            return new ResponseEntity<>(Map.of("message", "Game removed from wishlist"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Error removing game from wishlist" + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
