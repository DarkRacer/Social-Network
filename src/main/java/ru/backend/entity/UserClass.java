package ru.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class UserClass {
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
}
