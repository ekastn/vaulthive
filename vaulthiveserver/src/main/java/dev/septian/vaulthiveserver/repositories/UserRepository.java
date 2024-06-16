package dev.septian.vaulthiveserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.septian.vaulthiveserver.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    
    Optional<UserEntity> findByUsername(String username);

}
