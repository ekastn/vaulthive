package dev.septian.vaulthiveserver.domain.dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListDto {

    private int id;

    private String title;

    private String description;

    private Set<ListGameDto> listGames;
}
