package dev.septian.vaulthiveserver.domain.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import dev.septian.vaulthiveserver.domain.keys.GameLikeKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "game_likes")
public class GameLikeEntity {

    @EmbeddedId
    @Builder.Default
    private GameLikeKey id = new GameLikeKey();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("gameId")
    private GameEntity game;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;
    
}
