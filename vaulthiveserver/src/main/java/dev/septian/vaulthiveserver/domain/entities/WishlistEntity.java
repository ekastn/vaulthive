package dev.septian.vaulthiveserver.domain.entities;

import dev.septian.vaulthiveserver.domain.keys.WishlistKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
@Table(name = "wishlists")
public class WishlistEntity {

    @EmbeddedId
    @Builder.Default
    private WishlistKey id = new WishlistKey();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("gameId")
    private GameEntity game;
    
}
