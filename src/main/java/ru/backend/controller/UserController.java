package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.backend.entity.User;
import ru.backend.entity.UserClass;
import ru.backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/{id_speaker}/subscribe")
    @ResponseStatus(HttpStatus.OK)
    public void subscribe(@RequestBody @Param("id_subscriber") UserClass id, @PathVariable @Param("id_speaker") Long id_speaker) {
        userService.subscribe(id.getId(), id_speaker);
    }
}
