package dev.septian.vaulthiveserver.domain.requests;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListRequest {

    private int userId;

    private String title;

    private String description;

    private Set<Integer> gameIds;
    
}
