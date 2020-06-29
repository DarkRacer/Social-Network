package ru.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "publications", schema = "public")
@NoArgsConstructor
public class Publications {
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
    private String description;

    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Timestamp creation_date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "User_id")
    @Getter
    @Setter
    private User user;

    @Column
    @Getter
    @Setter
    private int likes;
}
