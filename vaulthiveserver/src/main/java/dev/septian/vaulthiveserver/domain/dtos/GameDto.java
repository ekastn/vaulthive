package dev.septian.vaulthiveserver.domain.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonAlias("description_raw")
    @JsonProperty("description")
    private String description;

    private String released;

    private float rating;

    private Set<DeveloperDto> developers;

    private Set<PublisherDto> publishers;

    private Set<ListDto> lists;
}
