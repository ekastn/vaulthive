package dev.septian.vaulthiveserver.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.RawgPagedResponse;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class GameClientTest {

    private GameClient underTest;

    @Autowired
    GameClientTest(GameClient gameClient) {
        this.underTest = gameClient;
    }

    @Test
    public void testThatGetDataRetrievesData() {
        RawgPagedResponse<Game> result = underTest.getData("/games");
        assertThat(result).isNotNull();
        assertThat(result.getResults()).isNotEmpty();
    }

    @Test
    public void testThatGetDataWithParamsRetrievesData() {
        Map<String, String> params = new HashMap<>();
        params.put("search", "fifa");
        RawgPagedResponse<Game> result = underTest.getData("/games", params);
        assertThat(result).isNotNull();
        assertThat(result.getResults()).isNotEmpty();
    }

    @Test
    public void testThatGetDetailsRetrievesData() {
        Game result = underTest.getDetails("/games", 3498);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(3498);
    }

}
