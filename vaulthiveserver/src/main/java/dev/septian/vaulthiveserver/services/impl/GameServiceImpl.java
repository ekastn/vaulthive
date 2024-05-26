package dev.septian.vaulthiveserver.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import dev.septian.vaulthiveserver.domain.Game;
import dev.septian.vaulthiveserver.domain.PagedResponse;
import dev.septian.vaulthiveserver.services.GameService;

@Service
public class GameServiceImpl implements GameService {

    private final RestClient restClient;

    @Value("${RAWG_API_KEY}")
    private String apiKey;

    public GameServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Game findGameDetails(long id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games/{id}")
                        .queryParam("key", apiKey)
                        .build(id))
                .retrieve()
                .body(Game.class);
    }

    @Override
    public PagedResponse<Game> findGameByName(String name) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games")
                        .queryParam("key", apiKey)
                        .queryParam("search", name)
                        .queryParam("page_size", 10)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<PagedResponse<Game>>() {
                });
    }
}
