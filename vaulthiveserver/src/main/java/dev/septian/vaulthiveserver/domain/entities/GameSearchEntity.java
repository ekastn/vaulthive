package dev.septian.vaulthiveserver.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "game_searchs")
public class GameSearchEntity {
    
    @Id
    private int id;

    private String name;

    private String slug;

    private String released;

    @Column(name = "image_url")
    private String imageUrl;

}
