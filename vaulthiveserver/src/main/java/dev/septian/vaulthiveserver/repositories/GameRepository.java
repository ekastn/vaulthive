package dev.septian.vaulthiveserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.septian.vaulthiveserver.domain.entities.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {

}
