package com.example.userapp.controller;

import com.example.userapp.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordRestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Record> getAll() {
        return jdbcTemplate.query(
                "SELECT id, title, year, price, artist_id FROM records",
                (rs, rowNum) -> {
                    Record r = new Record();
                    r.setId(rs.getInt("id"));
                    r.setTitle(rs.getString("title"));
                    r.setYear(rs.getInt("year"));
                    r.setPrice(rs.getDouble("price")); // <-- ИСПРАВЛЕНО ЗДЕСЬ
                    r.setArtistId(rs.getInt("artist_id"));
                    return r;
                }
        );
    }

    @GetMapping("/{id}")
    public Record getById(@PathVariable Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, title, year, price, artist_id FROM records WHERE id = ?",
                (rs, rowNum) -> {
                    Record r = new Record();
                    r.setId(rs.getInt("id"));
                    r.setTitle(rs.getString("title"));
                    r.setYear(rs.getInt("year"));
                    r.setPrice(rs.getDouble("price")); // <-- ИСПРАВЛЕНО ЗДЕСЬ
                    r.setArtistId(rs.getInt("artist_id"));
                    return r;
                },
                id
        );
    }

    @PostMapping
    public Record create(@RequestBody Record record) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO records (title, year, price, artist_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, record.getTitle());
            ps.setInt(2, record.getYear());
            ps.setDouble(3, record.getPrice());
            ps.setInt(4, record.getArtistId());
            return ps;
        }, keyHolder);
        record.setId(keyHolder.getKey().intValue());
        return record;
    }

    @PutMapping("/{id}")
    public Record update(@PathVariable Integer id, @RequestBody Record record) {
        jdbcTemplate.update(
                "UPDATE records SET title = ?, year = ?, price = ?, artist_id = ? WHERE id = ?",
                record.getTitle(), record.getYear(), record.getPrice(), record.getArtistId(), id
        );
        record.setId(id);
        return record;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        jdbcTemplate.update("DELETE FROM records WHERE id = ?", id);
        return "Record with id " + id + " deleted";
    }
}