package dev.septian.vaulthiveserver.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.septian.vaulthiveserver.domain.dtos.PublisherDto;
import dev.septian.vaulthiveserver.domain.entities.PublisherEntity;
import dev.septian.vaulthiveserver.mappers.Mapper;

@Component
public class PublisherMapperImpl implements Mapper<PublisherEntity, PublisherDto> {

    private ModelMapper modelMapper;

    public PublisherMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PublisherDto mapTo(PublisherEntity publisherEntity) {
        return modelMapper.map(publisherEntity, PublisherDto.class);
    }

    @Override
    public PublisherEntity mapFrom(PublisherDto publisherDto) {
        return modelMapper.map(publisherDto, PublisherEntity.class);
    }

}
