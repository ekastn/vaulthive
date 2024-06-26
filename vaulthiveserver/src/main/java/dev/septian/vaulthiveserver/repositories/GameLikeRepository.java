package dev.septian.vaulthiveserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.septian.vaulthiveserver.domain.entities.GameLikeEntity;
import dev.septian.vaulthiveserver.domain.keys.GameLikeKey;

public interface GameLikeRepository extends JpaRepository<GameLikeEntity, GameLikeKey> {

    Optional<GameLikeEntity> findByGame_IdAndUser_Id(int gameId, int userId);
    
}
