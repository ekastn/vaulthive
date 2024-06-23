package dev.septian.vaulthiveserver.domain.responses;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import dev.septian.vaulthiveserver.domain.dtos.GenreDto;
import dev.septian.vaulthiveserver.domain.dtos.PlatformDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameSearchResponse {

    private int id;

    private String name;

    private String slug;

    private String released;

    private String imageUrl;

    private float rating;

    private Set<GenreDto> genres;

    private Set<PlatformDto> platforms;

}
