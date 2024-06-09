package dev.septian.vaulthiveserver.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.septian.vaulthiveserver.domain.dtos.ListDto;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Component
public class ListMapperImpl implements Mapper<ListEntity, ListDto> {

    private ModelMapper modelMapper;

    public ListMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ListDto mapTo(ListEntity listEntity) {
        return modelMapper.map(listEntity, ListDto.class);
    }

    @Override
    public ListEntity mapFrom(ListDto listDto) {
        return modelMapper.map(listDto, ListEntity.class);
    }
}
