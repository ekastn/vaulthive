package dev.septian.vaulthiveserver.services;

import java.util.Optional;

import dev.septian.vaulthiveserver.domain.entities.UserEntity;

public interface UserService {

    Optional<UserEntity> findOne(int id);
    
}
