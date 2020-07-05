package ru.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PublicationsDTO {
    private Long id;
    private String name;
    private String description;
    private Timestamp creation_date;
    private UserDTO user;
    private int likes;
}
