package dev.septian.vaulthiveserver.domain.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "games")
public class GameEntity {

    @Id
    private int id;

    private String name;

    private String description;

    private String released;

    private float rating;

    private Set<DeveloperEntity> developers;

    private Set<PublisherEntity> publishers;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Set<ListGameEntity> listGames = new HashSet<>();

    public void add(ListGameEntity item) {
        if (item != null) {
            if (listGames == null) {
                listGames = new HashSet<>();
            }
            listGames.add(item);
        }
    }
}
