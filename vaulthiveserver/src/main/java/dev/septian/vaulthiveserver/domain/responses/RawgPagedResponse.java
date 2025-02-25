package dev.septian.vaulthiveserver.domain.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawgPagedResponse<T> {
    private int count;
    private String next;
    private String previous;
    private List<T> results;
}
