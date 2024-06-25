package dev.septian.vaulthiveserver.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.repositories.UserRepository;
import dev.septian.vaulthiveserver.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findOne(int id) {
        return userRepository.findById(id);
    }
    
}
