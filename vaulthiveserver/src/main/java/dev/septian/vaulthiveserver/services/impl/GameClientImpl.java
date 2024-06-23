package dev.septian.vaulthiveserver.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.ScreenshotDto;
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.responses.RawgGameRespnose;
import dev.septian.vaulthiveserver.domain.responses.RawgPagedResponse;
import dev.septian.vaulthiveserver.mappers.Mapper;
import dev.septian.vaulthiveserver.services.GameClient;

@Service
public class GameClientImpl implements GameClient {

    private final RestClient restClient;
    private final Mapper<GameEntity, GameDto> gameMapper;

    private final Logger logger = LoggerFactory.getLogger(GameClientImpl.class);

    @Value("${RAWG_API_KEY}")
    private String apiKey;

    private String endpoint = "/games";
    private int pageSize = 20;

    public GameClientImpl(RestClient restClient, Mapper<GameEntity, GameDto> gameMapper) {
        this.restClient = restClient;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<GameEntity> getData(Map<String, String> params) {
        RawgPagedResponse<RawgGameRespnose> response = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint)
                        .queryParam("key", apiKey)
                        .queryParam("page_size", pageSize)
                        .queryParams(toMultiValueMap(params))
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RawgPagedResponse<RawgGameRespnose>>() {
                });
        
        @SuppressWarnings("null")
        List<GameDto> games = response.getResults().stream()
                .map(game -> GameDto.builder()
                        .id(game.getId())
                        .name(game.getName())
                        .slug(game.getSlug())
                        .description(game.getDescription())
                        .released(game.getReleased())
                        .rating(game.getRating())
                        .imageUrl(game.getImageUrl())
                        .genres(game.getGenres())
                        .platforms(game.getPlatforms())
                        .screenshots(game.getScreenshots())
                        .build())
                .toList();
        
        return games.stream()
                .map(gameMapper::mapFrom)
                .toList();
    }

    @Override
    public GameEntity getDetails(int id) {
        RawgGameRespnose gameResponse = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint + "/" + id)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(RawgGameRespnose.class);

        RawgPagedResponse<ScreenshotDto> screenshotsResponse = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint + "/" + id + "/screenshots")
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RawgPagedResponse<ScreenshotDto>>() {
                });

        @SuppressWarnings("null")
        Set<ScreenshotDto> screenshots = new HashSet<>(screenshotsResponse.getResults());

        @SuppressWarnings("null")
        GameDto game = GameDto.builder()
                .id(gameResponse.getId())
                .name(gameResponse.getName())
                .slug(gameResponse.getSlug())
                .description(gameResponse.getDescription())
                .released(gameResponse.getReleased())
                .rating(gameResponse.getRating())
                .imageUrl(gameResponse.getImageUrl())
                .developers(gameResponse.getDevelopers())
                .publishers(gameResponse.getPublishers())
                .genres(gameResponse.getGenres())
                .platforms(gameResponse.getPlatforms())
                .screenshots(screenshots)
                .build();

        return gameMapper.mapFrom(game);
    }

    private MultiValueMap<String, String> toMultiValueMap(Map<String, String> params) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        params.forEach((key, value) -> multiValueMap.add(key, value));
        return multiValueMap;
    }

}
