package dev.septian.vaulthiveserver.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.septian.vaulthiveserver.domain.dtos.GameSearchDto;
import dev.septian.vaulthiveserver.domain.entities.GameSearchEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Component
public class GameSearchMapperImpl implements Mapper<GameSearchEntity, GameSearchDto>{

    ModelMapper modelMapper;

    public GameSearchMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GameSearchDto mapTo(GameSearchEntity gameSearchEntity) {
        return modelMapper.map(gameSearchEntity, GameSearchDto.class);
    }

    @Override
    public GameSearchEntity mapFrom(GameSearchDto gameSearchDto) {
        return modelMapper.map(gameSearchDto, GameSearchEntity.class);
    }

}
