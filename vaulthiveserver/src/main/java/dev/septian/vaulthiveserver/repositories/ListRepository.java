package dev.septian.vaulthiveserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.septian.vaulthiveserver.domain.ListEntity;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Integer>{
    
}
