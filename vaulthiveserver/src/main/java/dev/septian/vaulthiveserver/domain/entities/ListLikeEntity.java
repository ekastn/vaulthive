package dev.septian.vaulthiveserver.domain.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import dev.septian.vaulthiveserver.domain.keys.ListLikeKey;
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
@Table(name = "list_likes")
public class ListLikeEntity {

    @EmbeddedId
    @Builder.Default
    private ListLikeKey id = new ListLikeKey();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private UserEntity user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("listId")
    private ListEntity list;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

}
