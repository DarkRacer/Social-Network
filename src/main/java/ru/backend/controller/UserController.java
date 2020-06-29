package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.backend.entity.User;
import ru.backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public List<User> findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
}
