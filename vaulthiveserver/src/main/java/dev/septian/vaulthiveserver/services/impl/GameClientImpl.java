package dev.septian.vaulthiveserver.services.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.GameSearchDto;
import dev.septian.vaulthiveserver.domain.dtos.ScreenshotDto;
import dev.septian.vaulthiveserver.domain.responses.RawgGameRespnose;
import dev.septian.vaulthiveserver.domain.responses.RawgPagedResponse;
import dev.septian.vaulthiveserver.services.GameClient;

@Service
public class GameClientImpl implements GameClient {

    private final RestClient restClient;

    @Value("${RAWG_API_KEY}")
    private String apiKey;

    private String endpoint = "/games";
    private int pageSize = 20;

    public GameClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public RawgPagedResponse<GameDto> getData() {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RawgPagedResponse<GameDto>>() {
                });
    }

    @Override
    public RawgPagedResponse<GameSearchDto> getData(Map<String, String> params) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint)
                        .queryParam("key", apiKey)
                        .queryParam("page_size", pageSize)
                        .queryParams(toMultiValueMap(params))
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RawgPagedResponse<GameSearchDto>>() {
                });
    }

    @Override
    public GameDto getDetails(int id) {
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

        Set<ScreenshotDto> screenshots = new HashSet<>(screenshotsResponse.getResults());

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

        return game;
    }

    private MultiValueMap<String, String> toMultiValueMap(Map<String, String> params) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        params.forEach((key, value) -> multiValueMap.add(key, value));
        return multiValueMap;
    }

}
