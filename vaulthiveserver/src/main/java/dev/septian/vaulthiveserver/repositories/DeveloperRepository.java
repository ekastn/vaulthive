package dev.septian.vaulthiveserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.septian.vaulthiveserver.domain.entities.DeveloperEntity;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Integer>{
    
}
