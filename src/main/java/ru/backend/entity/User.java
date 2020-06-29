package ru.backend.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String name;

    @Column
    @Getter
    @Setter
    private Date date_of_birth;

    @Column
    @Getter
    @Setter
    private String username;

    @Column
    @Getter
    @Setter
    private String email;

    @Column
    @Getter
    @Setter
    private int subscribers;

    @Column
    @Getter
    @Setter
    private int subscriptions;
}
