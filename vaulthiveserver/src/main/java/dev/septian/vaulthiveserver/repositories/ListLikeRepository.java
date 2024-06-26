package dev.septian.vaulthiveserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.septian.vaulthiveserver.domain.entities.ListLikeEntity;
import dev.septian.vaulthiveserver.domain.keys.ListLikeKey;

public interface ListLikeRepository extends JpaRepository<ListLikeEntity, ListLikeKey> {

    Optional<ListLikeEntity> findByList_IdAndUser_Id(int listId, int userId);
    
}
