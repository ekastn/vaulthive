package dev.septian.vaulthiveserver;


import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;

public final class TestData {
    private TestData() {
    }

    public static UserEntity createUserEntity() {
        return UserEntity.builder()
                .username("user")
                .email("user@test.com")
                .password("password")
                .build();
    }

    public static GameEntity createGameEntity() {
        return GameEntity.builder()
                .name("Game")
                .description("Description of Game")
                .released("2021-01-01")
                .rating(5.0f)
                .build();
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

    public static UserEntity createUserEntity(String string) {
        return UserEntity.builder()
                .username(string)
                .email(string + "@example.com")
                .password("password")
                .build();
    }
}
