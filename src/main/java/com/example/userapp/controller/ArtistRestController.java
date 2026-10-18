package com.example.userapp.controller;

import com.example.userapp.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistRestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Artist> getAll() {
        return jdbcTemplate.query(
                "SELECT id, name, country FROM artists",
                (rs, rowNum) -> new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("country"))
        );
    }

    @GetMapping("/{id}")
    public Artist getById(@PathVariable Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, country FROM artists WHERE id = ?",
                (rs, rowNum) -> new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("country")),
                id
        );
    }

    @PostMapping
    public Artist create(@RequestBody Artist artist) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO artists (name, country) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getCountry());
            return ps;
        }, keyHolder);
        artist.setId(keyHolder.getKey().intValue());
        return artist;
    }

    @PutMapping("/{id}")
    public Artist update(@PathVariable Integer id, @RequestBody Artist artist) {
        jdbcTemplate.update(
                "UPDATE artists SET name = ?, country = ? WHERE id = ?",
                artist.getName(), artist.getCountry(), id
        );
        artist.setId(id);
        return artist;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        jdbcTemplate.update("DELETE FROM artists WHERE id = ?", id);
        return "Artist with id " + id + " deleted";
    }
}