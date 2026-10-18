package com.example.userapp.controller;

import com.example.userapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<User> getAllUsers() {
        return jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM users",
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"))
        );
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, first_name, last_name FROM users WHERE id = ?",
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")),
                id
        );
    }
}