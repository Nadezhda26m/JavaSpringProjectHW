package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;
    private final AtomicInteger lastId;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.lastId = new AtomicInteger(getLastId());
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";
        List<User> users = jdbc.query(sql, new UserMapper());
        return users.isEmpty() ? null : users;
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable(firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        user.setId(lastId.incrementAndGet());
        return user;
    }

    private Integer getLastId() {
        String sql = "SELECT MAX(id) FROM userTable";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);
        rowSet.next();
        if (rowSet.getObject(1) == null) return 0;
        return (Integer) rowSet.getObject(1);
    }

    public User getById(int id) {
        String sql = "SELECT * FROM userTable WHERE id=?";
        return jdbc.query(sql, new UserMapper(), id)
                .stream().findFirst().orElse(null);
    }

    public void update(User user) {
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

}
