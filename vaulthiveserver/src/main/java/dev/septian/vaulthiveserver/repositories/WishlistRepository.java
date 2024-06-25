package dev.septian.vaulthiveserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.septian.vaulthiveserver.domain.entities.WishlistEntity;
import dev.septian.vaulthiveserver.domain.keys.WishlistKey;

public interface WishlistRepository extends JpaRepository<WishlistEntity, WishlistKey>{

    Optional<WishlistEntity> findByGame_IdAndUser_Id(int id, int id2);

}
