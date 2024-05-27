package dev.septian.vaulthiveserver.services;

import java.util.List;

import dev.septian.vaulthiveserver.domain.ListEntity;

public interface ListService {
    ListEntity save(ListEntity listEntity);
    List<ListEntity> findAll();
}
