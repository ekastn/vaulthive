package dev.septian.vaulthiveserver.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.septian.vaulthiveserver.domain.dtos.UserDto;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
    
}
