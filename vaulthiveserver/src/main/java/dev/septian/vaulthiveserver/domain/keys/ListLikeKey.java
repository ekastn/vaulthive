package dev.septian.vaulthiveserver.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ListLikeKey implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "list_id")
    private Integer listId;
    
}
