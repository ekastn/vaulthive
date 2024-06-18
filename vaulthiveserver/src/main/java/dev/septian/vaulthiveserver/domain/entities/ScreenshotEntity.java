package dev.septian.vaulthiveserver.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "screenshots")
public class ScreenshotEntity {
    
    @Id
    private Integer id;

    private String image;

    private int width;

    private int height;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
