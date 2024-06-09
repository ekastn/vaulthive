package dev.septian.vaulthiveserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.septian.vaulthiveserver.domain.entities.GameSearchEntity;

@Repository
public interface GameSearchRepository extends JpaRepository<GameSearchEntity, Integer> {

}
