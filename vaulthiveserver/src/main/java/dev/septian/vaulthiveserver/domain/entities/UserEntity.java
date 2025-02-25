package dev.septian.vaulthiveserver.domain.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    Set<ListEntity> lists = new HashSet<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    Set<WishlistEntity> wishlists = new HashSet<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    Set<ListLikeEntity> listLikes = new HashSet<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    Set<GameLikeEntity> gameLikes = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public void addList(ListEntity list) {
        if (lists == null) {
            lists = new HashSet<>();
        }
        lists.add(list);
    }

    public void addWishlist(WishlistEntity wishlist) {
        if (wishlists == null) {
            wishlists = new HashSet<>();
        }
        wishlists.add(wishlist);
    }

    public void addListLike(ListLikeEntity listLike) {
        if (listLikes == null) {
            listLikes = new HashSet<>();
        }
        listLikes.add(listLike);
    }

    public void addGameLike(GameLikeEntity gameLike) {
        if (gameLikes == null) {
            gameLikes = new HashSet<>();
        }
        gameLikes.add(gameLike);
    }

}
