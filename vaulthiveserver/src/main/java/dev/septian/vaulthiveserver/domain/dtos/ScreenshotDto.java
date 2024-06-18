package dev.septian.vaulthiveserver.domain.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreenshotDto {

    private int id;

    private String image;

    private int width;

    private int height;

    @JsonAlias("is_deleted")
    @JsonProperty("isDeleted")
    private boolean isDeleted;
    
}
