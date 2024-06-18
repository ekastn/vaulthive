package dev.septian.vaulthiveserver.repositoriers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.domain.entities.DeveloperEntity;
import dev.septian.vaulthiveserver.repositories.DeveloperRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeveloperRepositoryIt {

    DeveloperRepository underTest;

    @Autowired
    DeveloperRepositoryIt(DeveloperRepository developerRepository) {
        this.underTest = developerRepository;
    }

    @Test
    public void testThatDeveloperCanBeCreatedAndRecalled() {
        DeveloperEntity developerEntity = DeveloperEntity.builder()
                .id(1)
                .name("developer 1")
                .build();

        DeveloperEntity savedDeveloper = underTest.save(developerEntity);

        assertThat(savedDeveloper).isNotNull();
        assertThat(savedDeveloper.getId()).isNotNull();
        assertThat(savedDeveloper.equals(developerEntity));
    }

    @Test
    public void testThatDeveloperCanBeRetrieved() {
        DeveloperEntity developerEntity = DeveloperEntity.builder()
                .id(1)
                .name("developer 1")
                .build();

        DeveloperEntity savedDeveloper = underTest.save(developerEntity);

        Optional<DeveloperEntity> foundDeveloper = underTest.findById(savedDeveloper.getId());

        assertThat(foundDeveloper).isPresent();
        assertThat(foundDeveloper.get().getName()).isEqualTo("developer 1");
    }

    @Test
    public void testThatDeveloperCanBeUpdated() {
        DeveloperEntity developerEntity = DeveloperEntity.builder()
                .id(1)
                .name("developer 1")
                .build();

        DeveloperEntity savedDeveloper = underTest.save(developerEntity);

        DeveloperEntity updatedDeveloper = DeveloperEntity.builder()
                .id(savedDeveloper.getId())
                .name("developer 1 Updated")
                .build();

        DeveloperEntity updatedSavedDeveloper = underTest.save(updatedDeveloper);

        assertThat(updatedSavedDeveloper).isNotNull();
        assertThat(updatedSavedDeveloper.getId()).isEqualTo(savedDeveloper.getId());
        assertThat(updatedSavedDeveloper.getName()).isEqualTo("developer 1 Updated");
    }

    @Test
    public void testThatDeveloperCanBeDeleted() {
        DeveloperEntity developerEntity = DeveloperEntity.builder()
                .id(1)
                .name("developer 1")
                .build();

        DeveloperEntity savedDeveloper = underTest.save(developerEntity);

        underTest.deleteById(savedDeveloper.getId());

        Optional<DeveloperEntity> foundDeveloper = underTest.findById(savedDeveloper.getId());

        assertThat(foundDeveloper).isEmpty();
    }
    
}