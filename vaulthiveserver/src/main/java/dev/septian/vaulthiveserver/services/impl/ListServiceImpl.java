package dev.septian.vaulthiveserver.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.septian.vaulthiveserver.domain.ListEntity;
import dev.septian.vaulthiveserver.repositories.ListRepository;
import dev.septian.vaulthiveserver.services.ListService;

@Service
public class ListServiceImpl implements ListService {
    ListRepository listRepository;

    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ListEntity save(ListEntity listEntity) {
        return listRepository.save(listEntity);
    }

    @Override
    public List<ListEntity> findAll() {
        return listRepository.findAll();
    }
    
}
