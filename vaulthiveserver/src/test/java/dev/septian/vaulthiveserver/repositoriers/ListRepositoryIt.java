package dev.septian.vaulthiveserver.repositoriers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.repositories.ListRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ListRepositoryIt {

    private ListRepository underTest;

    @Autowired
    ListRepositoryIt(ListRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatListCanBeCreatedAndRecalled() {
        UserEntity userEntity = TestData.createUserEntity("user1");

        ListEntity listEntity = TestData.createListEntityA();

        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));
        listEntity.getGames().add(TestData.createGameEntity(5));

        listEntity.setUser(userEntity);

        ListEntity savedList = underTest.save(listEntity);

        assertThat(savedList).isNotNull();
        assertThat(savedList.getId()).isEqualTo(listEntity.getId());
        assertThat(savedList.getTitle()).isEqualTo("A pieceful day");
        assertThat(savedList.getDescription()).isEqualTo("For those who want to relax");
        assertThat(savedList.getGames()).hasSize(4);
        assertThat(savedList.getUser()).isEqualTo(userEntity);
    }

    @Test
    public void testThatListCanBeUpdated() {
        UserEntity userEntity = TestData.createUserEntity("user1");

        ListEntity listEntity = TestData.createListEntityA();

        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));
        listEntity.getGames().add(TestData.createGameEntity(5));

        listEntity.setUser(userEntity);

        underTest.save(listEntity);

        listEntity.setTitle("A pieceful night");

        underTest.save(listEntity);

        Optional<ListEntity> foundList = underTest.findById(listEntity.getId());

        assertThat(foundList).isPresent();
        assertThat(foundList.get().getTitle()).isEqualTo("A pieceful night");
    }

    @Test
    public void testThatListCanBeDeleted() {
        UserEntity userEntity = TestData.createUserEntity("user1");

        ListEntity listEntity = TestData.createListEntityA();

        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));
        listEntity.getGames().add(TestData.createGameEntity(5));

        listEntity.setUser(userEntity);

        ListEntity savedList = underTest.save(listEntity);

        underTest.delete(savedList);

        Optional<ListEntity> foundList = underTest.findById(savedList.getId());

        assertThat(foundList).isEmpty();
    }

}