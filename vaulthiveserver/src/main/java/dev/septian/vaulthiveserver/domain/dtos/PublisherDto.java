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
public class PublisherDto {

    private int id;

    private String name;

    private int gameCount;

    private Set<GameDto> games;
}
