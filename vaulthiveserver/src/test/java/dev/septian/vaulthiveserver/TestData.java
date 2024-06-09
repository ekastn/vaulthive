package dev.septian.vaulthiveserver;

import java.util.Set;
import java.util.HashSet;

import dev.septian.vaulthiveserver.domain.dtos.DeveloperDto;
import dev.septian.vaulthiveserver.domain.dtos.PublisherDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.entities.ListGameEntity;

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

    public static ListGameEntity createlistGameEntity(int id) {
        return ListGameEntity.builder()
                .id(id)
                .build();
    }

    public static GameEntity createGameEntity(int id) {
        Set<DeveloperDto> developers = new HashSet<>();
        developers.add(new DeveloperDto(1, "Developer 1", 1));
        developers.add(new DeveloperDto(2, "Developer 2", 1));
        Set<PublisherDto> publishers = new HashSet<>();
        publishers.add(new PublisherDto(1, "Publisher 1", 1));
        publishers.add(new PublisherDto(2, "Publisher 2", 1));
        return GameEntity.builder()
                .id(id)
                .name("Game " + id)
                .description("Description of game " + id)
                .released("2021-01-01")
                .rating(4.5f)
                .developers(developers)
                .publishers(publishers)
                .build();
    }
}
