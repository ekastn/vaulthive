package dev.septian.vaulthiveserver.repositoriers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
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
        ListEntity listEntity = TestData.createListEntityA();

        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));
        listEntity.getGames().add(TestData.createGameEntity(5));

        ListEntity savedList = underTest.save(listEntity);

        assertThat(savedList).isNotNull();
        assertThat(savedList.getId()).isNotNull();
        assertThat(savedList.getGames()).isNotEmpty();
    }

    @Test
    public void testThatListCanBeRetrieved() {
        ListEntity listEntity = TestData.createListEntityA();

        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));
        listEntity.getGames().add(TestData.createGameEntity(5));

        ListEntity savedList = underTest.save(listEntity);

        Optional<ListEntity> foundList = underTest.findById(savedList.getId());

        assertThat(foundList).isPresent();
        assertThat(foundList.get().getTitle()).isEqualTo("A pieceful day");
        assertThat(foundList.get().getGames()).isNotEmpty();
    }

    @Test
    public void testThatListCanBeUpdated() {
        ListEntity listEntity = TestData.createListEntityA();

        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));
        listEntity.getGames().add(TestData.createGameEntity(5));

        ListEntity savedList = underTest.save(listEntity);

        savedList.setTitle("Updated Title");
        savedList.setDescription("Updated Description");
        savedList.getGames().clear();
        savedList.getGames().add(TestData.createGameEntity(6));
        savedList.getGames().add(TestData.createGameEntity(7));
        savedList.getGames().add(TestData.createGameEntity(8));
        savedList.getGames().add(TestData.createGameEntity(9));

        ListEntity updatedList = underTest.save(savedList);

        assertThat(updatedList).isNotNull();
        assertThat(updatedList.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedList.getDescription()).isEqualTo("Updated Description");
        assertThat(updatedList.getGames()).isNotEmpty();
    }

    @Test
    public void testThatListCanBeDeleted() {
        ListEntity listEntity = TestData.createListEntityA();

        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));
        listEntity.getGames().add(TestData.createGameEntity(5));

        ListEntity savedList = underTest.save(listEntity);

        underTest.deleteById(savedList.getId());

        Optional<ListEntity> foundList = underTest.findById(savedList.getId());

        assertThat(foundList).isEmpty();
    }

}