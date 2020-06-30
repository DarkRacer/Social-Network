package ru.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Date date_of_birth;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private int subscribers;

    @Column
    private int subscriptions;

    @ManyToMany
    @JoinTable(name = "subscriptions", joinColumns = @JoinColumn(name = "speaker_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id"))
    private Set<UserClass> subscriber = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "subscriptions", joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private Set<UserClass> subscription = new HashSet<>();
}