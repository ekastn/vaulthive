package dev.septian.vaulthiveserver.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.domain.dtos.GameDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RawgClientTest {

    private GameClient underTest;

    @Autowired
    RawgClientTest(GameClient gameClient) {
        this.underTest = gameClient;
    }

    @Test
    public void testThatGetDataRetrievesData() {
        RawgPagedResponse<GameDto> result = underTest.getData();
        assertThat(result).isNotNull();
        assertThat(result.getResults()).isNotEmpty();
    }

    @Test
    public void testThatGetDataWithParamsRetrievesData() {
        Map<String, String> params = new HashMap<>();
        params.put("search", "fifa");
        // RawgPagedResponse<GameDto> result = underTest.getData(params);
        // assertThat(result).isNotNull();
        // assertThat(result.getResults()).isNotEmpty();
    }

    @Test
    public void testThatGetDetailsRetrievesData() {
        GameDto result = underTest.getDetails(3498);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(3498);
    }

}
