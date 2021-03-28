package com.moviesandchill.usermanagementservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String logoUrl;

    private String description;

    @Column(nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserPassword password;

    @ManyToMany
    @JoinTable(
            name = "friendship_info",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> friends = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_achievements",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Achievement> achievements = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_favorite_films",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Film> favoriteFilms;

    @ManyToMany
    @JoinTable(
            name = "user_want_watch_films",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Film> wantWatchFilms = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_wanted_films",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Film> watchedFilms = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_favorite_staffs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Staff> favoriteStaffs = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_global_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "global_role_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GlobalRole> globalRoles = new HashSet<>();

}
