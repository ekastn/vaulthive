package dev.septian.vaulthiveserver.repositoriers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIt {

    private UserRepository underTest;

    @Autowired
    UserRepositoryIt(UserRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatUserCanBeCreatedAndRecalled() {
        UserEntity userEntity = TestData.createUserEntity("user1");

        UserEntity savedUser = underTest.save(userEntity);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("user1");
        assertThat(savedUser.getEmail()).isEqualTo("user1@example.com");
    }

    @Test
    public void testThatUserCanBeFoundByUsername() {
        UserEntity userEntity = TestData.createUserEntity("user1");

        underTest.save(userEntity);

        UserEntity foundUser = underTest.findByUsername("user1").get();

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("user1");
    }

    @Test
    public void testThatUserCanBeUpdated() {
        UserEntity userEntity = TestData.createUserEntity("user1");
        underTest.save(userEntity);
        userEntity.setUsername("updatedUser");
        underTest.save(userEntity);
        Optional<UserEntity> foundUser = underTest.findById(userEntity.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("updatedUser");
    }

    @Test
    public void testThatUserCanBeDeleted() {
        UserEntity userEntity = TestData.createUserEntity("user1");

        UserEntity savedUser = underTest.save(userEntity);

        underTest.delete(savedUser);

        assertThat(underTest.findByUsername("user1")).isEmpty();
    }
    
}
