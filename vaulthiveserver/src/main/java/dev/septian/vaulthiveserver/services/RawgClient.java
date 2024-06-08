package dev.septian.vaulthiveserver.services;

import java.util.Map;

import dev.septian.vaulthiveserver.domain.RawgPagedResponse;

public interface RawgClient <T> {
    RawgPagedResponse<T> getData(String endpoint);
    RawgPagedResponse<T> getData(String endpoint, Map<String, String> params);
}
