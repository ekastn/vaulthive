package dev.septian.vaulthiveserver.domain.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "publishers")
public class PublisherEntity {

    @Id
    private Integer id;

    private String name;

    private int gameCount;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "game_publishers",
        joinColumns = @JoinColumn(name = "publisher_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<GameEntity> games = new HashSet<>();

    public void addGame(GameEntity game) {
        if (games == null) {
            games = new HashSet<>();
        }
        games.add(game);
    }

}
