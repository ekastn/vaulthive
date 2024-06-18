package dev.septian.vaulthiveserver.domain.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "games")
public class GameEntity {

    @Id
    private Integer id;

    private String name;

    private String slug;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String released;

    private float rating;

    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, mappedBy = "games")
    private Set<DeveloperEntity> developers = new HashSet<>();

    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, mappedBy = "games")
    private Set<PublisherEntity> publishers = new HashSet<>();

    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, mappedBy = "games")
    private Set<ListEntity> lists = new HashSet<>();
}
