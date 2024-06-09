package dev.septian.vaulthiveserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.septian.vaulthiveserver.domain.entities.PublisherEntity;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer>{
    
}
