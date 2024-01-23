package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Репозиторий для хранения и получения информации о пользователях в БД (в таблице userTable).
 */
@Repository
public class UserRepository {

    /**
     * Объект для создания подключения к БД.
     */
    private final JdbcTemplate jdbc;

    /**
     * ID последнего добавленного в БД пользователя.
     */
    private final AtomicInteger lastId;

    /**
     * Конструктор класса.
     * @param jdbc объект для создания подключения к БД
     */
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.lastId = new AtomicInteger(getLastId());
    }

    /**
     * Получение списка всех пользователей из таблицы userTable.
     * @return список пользователей или null, если пользователи не найдены
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";
        List<User> users = jdbc.query(sql, new UserMapper());
        return users.isEmpty() ? null : users;
    }

    /**
     * Добавление нового пользователя в таблицу userTable.
     * @param user объект пользователя с данными имени и фамилии
     * @return объект пользователя с присвоенным ID
     */
    public User save(User user) {
        String sql = "INSERT INTO userTable(firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        user.setId(lastId.incrementAndGet());
        return user;
    }

    /**
     * Получение последнего (максимального) ID в таблице userTable.
     * @return максимальный ID или 0, если таблица пустая
     */
    private Integer getLastId() {
        String sql = "SELECT MAX(id) FROM userTable";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);
        rowSet.next();
        if (rowSet.getObject(1) == null) return 0;
        return (Integer) rowSet.getObject(1);
    }

    /**
     * Получение объекта пользователя из таблицы userTable по его ID.
     * @param id уникальный идентификатор пользователя
     * @return объект пользователя с указанным ID или null, если пользователь не найден
     */
    public User getById(int id) {
        String sql = "SELECT * FROM userTable WHERE id=?";
        return jdbc.query(sql, new UserMapper(), id)
                .stream().findFirst().orElse(null);
    }

    /**
     * Обновление данных пользователя с присвоенным ID в таблице userTable.
     * @param user объект пользователя с измененными данными имени и фамилии
     */
    public void update(User user) {
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    /**
     * Удаление пользователя из таблицы userTable по его ID.
     * @param id уникальный идентификатор пользователя
     */
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

}
