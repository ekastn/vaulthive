package dev.septian.vaulthiveserver.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import dev.septian.vaulthiveserver.domain.dtos.GameDto;
import dev.septian.vaulthiveserver.domain.dtos.GameSearchDto;
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
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint + "/" + id)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(GameDto.class);
    }

    private MultiValueMap<String, String> toMultiValueMap(Map<String, String> params) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        params.forEach((key, value) -> multiValueMap.add(key, value));
        return multiValueMap;
    }

}
