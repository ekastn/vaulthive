package dev.septian.vaulthiveserver.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;
import dev.septian.vaulthiveserver.services.RawgClient;

@Service
public class RawgClientImpl<T> implements RawgClient<T> {

    private final RestClient restClient;

    @Value("${RAWG_API_KEY}")
    private String apiKey;

    public RawgClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    private MultiValueMap<String, String> toMultiValueMap(Map<String, String> params) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        params.forEach((key, value) -> multiValueMap.add(key, value));
        return multiValueMap;
    }

    @Override
    public RawgPagedResponse<T> getData(String endpoint) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RawgPagedResponse<T>>() {
                });
    }

    @Override
    public RawgPagedResponse<T> getData(String endpoint, Map<String, String> params) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint)
                        .queryParam("key", apiKey)
                        .queryParams(toMultiValueMap(params))
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RawgPagedResponse<T>>() {
                });
    }
}
