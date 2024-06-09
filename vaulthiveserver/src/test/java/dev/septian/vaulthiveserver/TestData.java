package dev.septian.vaulthiveserver;

import java.util.Set;
import java.util.HashSet;

import dev.septian.vaulthiveserver.domain.entities.DeveloperEntity;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.entities.PublisherEntity;

public final class TestData {
    private TestData() {
    }

    public static ListEntity createListEntityA() {
        return ListEntity.builder()
                .title("A pieceful day")
                .description("For those who want to relax")
                .build();
    }

    public static ListEntity createListEntityB() {
        return ListEntity.builder()
                .title("A pieceful night")
                .description("For those who want to relax")
                .build();
    }

    public static GameEntity createGameEntity(int id) {
        return GameEntity.builder()
                .id(id)
                .name("Game " + id)
                .description("Description of Game " + id)
                .released("2021-01-01")
                .rating(5.0f)
                .build();
    }
}
