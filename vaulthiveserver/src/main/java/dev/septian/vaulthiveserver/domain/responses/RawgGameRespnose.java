package dev.septian.vaulthiveserver.domain.responses;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import dev.septian.vaulthiveserver.domain.dtos.DeveloperDto;
import dev.septian.vaulthiveserver.domain.dtos.GenreDto;
import dev.septian.vaulthiveserver.domain.dtos.PlatformDto;
import dev.septian.vaulthiveserver.domain.dtos.PublisherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawgGameRespnose {
    
    private int id;

    private String name;

    private String slug;

    @JsonAlias("description_raw")
    @JsonProperty("description")
    private String description;

    private String released;

    private float rating;

    @JsonAlias("background_image")
    @JsonProperty("imageUrl")
    private String imageUrl;

    private Set<DeveloperDto> developers;

    private Set<PublisherDto> publishers;

    private Set<GenreDto> genres;

    @JsonProperty("parent_platforms")
    private Set<PlatformWrapper> platforms;

    @Data
    public static class PlatformWrapper {
        private PlatformDto platform;
    }

    public Set<PlatformDto> getPlatforms() {
        return platforms.stream().map(PlatformWrapper::getPlatform).collect(Collectors.toSet());
    }

}
