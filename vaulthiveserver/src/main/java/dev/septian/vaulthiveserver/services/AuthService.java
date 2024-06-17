package dev.septian.vaulthiveserver.services;

import dev.septian.vaulthiveserver.domain.dtos.LoginDto;
import dev.septian.vaulthiveserver.domain.dtos.RegisterDto;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;

public interface AuthService {
    
    public UserEntity signup(RegisterDto input);

    public UserEntity authenticate(LoginDto input);

}
