package ru.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "publications", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Publications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp creation_date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "User_id")
    private User user;

    @Column
    private int likes;
}
