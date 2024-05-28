package dev.septian.vaulthiveserver.services.impl;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ListEntity update(int id, ListEntity listEntity) {
        return listRepository.findById(id).map(existingEntity -> {
            Optional.ofNullable(listEntity.getTitle()).ifPresent(existingEntity::setTitle);
            Optional.ofNullable(listEntity.getDescription()).ifPresent(existingEntity::setDescription);
            if (listEntity.getListGames() != null) {
                existingEntity.getListGames().clear();
                existingEntity.getListGames().addAll(listEntity.getListGames());
            }
            return listRepository.save(existingEntity);
        }).orElseThrow(() -> new RuntimeException("List does not exist"));
    }

    @Override
    public boolean isExists(int id) {
        return listRepository.existsById(id);
    }

}
