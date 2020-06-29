package ru.backend.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "likes", schema = "public")
@NoArgsConstructor
public class Likes {
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "publication_id")
    @Getter
    @Setter
    private Set<Publications> publication = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private Set<User> user = new TreeSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
}
