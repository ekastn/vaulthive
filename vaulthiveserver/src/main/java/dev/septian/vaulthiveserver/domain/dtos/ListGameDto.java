package dev.septian.vaulthiveserver.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListGameDto {

    private int id;

    private GameDto gameId;

    private ListDto ListId;
}
