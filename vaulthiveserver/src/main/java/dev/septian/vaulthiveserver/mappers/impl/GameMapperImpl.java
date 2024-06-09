package dev.septian.vaulthiveserver.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Component
public class GameMapperImpl implements Mapper<GameEntity, GameDto> {

    private ModelMapper modelMapper;

    public GameMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GameDto mapTo(GameEntity gameEntity) {
        return modelMapper.map(gameEntity, GameDto.class);
    }

    @Override
    public GameEntity mapFrom(GameDto gameDto) {
        return modelMapper.map(gameDto, GameEntity.class);
    }
    
}
