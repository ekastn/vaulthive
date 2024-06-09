package dev.septian.vaulthiveserver.domain.dtos;

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
public class GameSearchDto {
    
    private int id;

    private String name;

    private String slug;

    private String released;

    @JsonAlias("background_image")
    @JsonProperty("image_url")
    private String imageUrl;

}
