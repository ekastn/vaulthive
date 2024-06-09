package dev.septian.vaulthiveserver.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.septian.vaulthiveserver.domain.dtos.DeveloperDto;
import dev.septian.vaulthiveserver.domain.entities.DeveloperEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Component
public class DeveloperMapperImpl implements Mapper<DeveloperEntity, DeveloperDto> {

    private ModelMapper modelMapper;

    public DeveloperMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DeveloperDto mapTo(DeveloperEntity developerEntity) {
        return modelMapper.map(developerEntity, DeveloperDto.class);
    }

    @Override
    public DeveloperEntity mapFrom(DeveloperDto developerDto) {
        return modelMapper.map(developerDto, DeveloperEntity.class);
    }

}
