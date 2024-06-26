package dev.septian.vaulthiveserver.repositoriers;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
import dev.septian.vaulthiveserver.domain.entities.ListLikeEntity;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.repositories.GameRepository;
import dev.septian.vaulthiveserver.repositories.ListLikeRepository;
import dev.septian.vaulthiveserver.repositories.ListRepository;
import dev.septian.vaulthiveserver.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ListLikeRepositoryIt {

    private ListRepository listRepository;
    private UserRepository userRepository;
    private ListLikeRepository underTest;

    @Autowired
    public ListLikeRepositoryIt(
            ListRepository listRepository,
            UserRepository userRepository,
            ListLikeRepository underTest) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
        this.underTest = underTest;
    }

    @Test
    public void testThatLikeCanBeCreatedAndRecalled() {
        UserEntity userEntity = TestData.createUserEntity();
        ListEntity listEntity = TestData.createListEntityA();

        userRepository.save(userEntity);
        listRepository.save(listEntity);

        ListLikeEntity listLikeEntity = ListLikeEntity.builder()
                .user(userEntity)
                .list(listEntity)
                .build();
        listEntity.addLike(listLikeEntity);
        userEntity.addListLike(listLikeEntity);

        ListLikeEntity saved = underTest.save(listLikeEntity);

        Optional<ListLikeEntity> response = underTest.findById(saved.getId());

        assertThat(response).isPresent();
        assertThat(response.get().getId()).isEqualTo(saved.getId());
        assertThat(response.get().getCreatedAt().toString()).isEqualTo("2024-06-26");
        assertThat(response.get().getUser().getId()).isEqualTo(userEntity.getId());
        assertThat(response.get().getList().getId()).isEqualTo(listEntity.getId());
    }

    @Test
    public void testThatLikeCanBeDeleted() {
        UserEntity userEntity = TestData.createUserEntity();
        ListEntity listEntity = TestData.createListEntityA();

        userRepository.save(userEntity);
        listRepository.save(listEntity);

        ListLikeEntity listLikeEntity = ListLikeEntity.builder()
                .user(userEntity)
                .list(listEntity)
                .build();
        listEntity.addLike(listLikeEntity);
        userEntity.addListLike(listLikeEntity);

        underTest.save(listLikeEntity);

        Optional<ListLikeEntity> found = underTest.findByList_IdAndUser_Id(listEntity.getId(), userEntity.getId());

        found.get().getList().getLikes().remove(found.get());
        found.get().getUser().getListLikes().remove(found.get());

        underTest.delete(found.get());

        Optional<ListLikeEntity> response = underTest.findById(listLikeEntity.getId());

        assertThat(response).isEmpty();
    }

}
