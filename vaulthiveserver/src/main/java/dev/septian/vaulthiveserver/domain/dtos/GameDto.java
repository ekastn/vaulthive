package dev.septian.vaulthiveserver.domain.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {

    private int id;

    private String name;

    private String slug;

    private String description;

    private String released;

    private float rating;

    private String imageUrl;

    private Set<DeveloperDto> developers;

    private Set<PublisherDto> publishers;

    private Set<GenreDto> genres;

    private Set<PlatformDto> platforms;

    private Set<ScreenshotDto> screenshots;

}
