package dev.septian.vaulthiveserver.repositoriers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.entities.ListGameEntity;
import dev.septian.vaulthiveserver.repositories.ListRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ListEntityRepositoryIt {
    private ListRepository underTest;

    @Autowired
    ListEntityRepositoryIt(ListRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatListCanBeCreatedAndRecalled() {
        ListEntity listEntity = TestData.createListEntityA();
        ListGameEntity item1 = TestData.createlistGameEntity(2345);
        ListGameEntity item2 = TestData.createlistGameEntity(2345);
        ListGameEntity item3 = TestData.createlistGameEntity(3345);

        listEntity.add(item1);
        listEntity.add(item2);
        listEntity.add(item3);

        underTest.save(listEntity);

        Optional<ListEntity> result = underTest.findById(listEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(listEntity);
    }
}