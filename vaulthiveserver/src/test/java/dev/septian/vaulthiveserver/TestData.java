package dev.septian.vaulthiveserver;

import dev.septian.vaulthiveserver.domain.ListEntity;
import dev.septian.vaulthiveserver.domain.ListGameEntity;

public final class TestData {
    private TestData() {
    }

    public static ListEntity createListEntityA() {
        return ListEntity.builder()
                .title("A pieceful day")
                .description("For those who want to relax")
                .build();
    }

    public static ListGameEntity createlistGameEntity(int gameId) {
        return ListGameEntity.builder()
                .gameId(gameId)
                .build();
    }
}
