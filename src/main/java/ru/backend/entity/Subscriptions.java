package ru.backend.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "subscriptions", schema = "public")
@NoArgsConstructor
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Subscriber_id")
    @Getter
    @Setter
    private Set<User> subscriber = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Speaker_id")
    @Getter
    @Setter
    private Set<User> speaker = new TreeSet<>();
}
