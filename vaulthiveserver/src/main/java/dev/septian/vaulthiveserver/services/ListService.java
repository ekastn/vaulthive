package dev.septian.vaulthiveserver.services;

import java.util.List;
import java.util.Optional;

import dev.septian.vaulthiveserver.domain.entities.ListEntity;

public interface ListService {
    Optional<ListEntity> findOne(int id);
    ListEntity save(ListEntity listEntity);
    List<ListEntity> findAll();
    ListEntity update(int id, ListEntity listEntity);
    boolean isExists(int id);
    void delete(int id);
}
