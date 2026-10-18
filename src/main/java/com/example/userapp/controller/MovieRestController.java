package com.example.userapp.controller;

import com.example.userapp.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(
                "SELECT id, title, user_id FROM movies",
                (rs, rowNum) -> {
                    Movie m = new Movie();
                    m.setId(rs.getInt("id"));
                    m.setTitle(rs.getString("title"));
                    m.setUserId(rs.getInt("user_id"));
                    return m;
                }
        );
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO movies (title, user_id) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getUserId());
            return ps;
        }, keyHolder);
        movie.setId(keyHolder.getKey().intValue());
        return movie;
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        jdbcTemplate.update(
                "UPDATE movies SET title = ?, user_id = ? WHERE id = ?",
                movie.getTitle(), movie.getUserId(), id
        );
        movie.setId(id);
        return movie;
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        jdbcTemplate.update("DELETE FROM movies WHERE id = ?", id);
        return "Movie with id " + id + " deleted successfully";
    }
}