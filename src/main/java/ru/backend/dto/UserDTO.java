package ru.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private Date date_of_birth;
    private String username;
    private String email;
    private int subscribers;
    private int subscriptions;
    private String picture;
}
