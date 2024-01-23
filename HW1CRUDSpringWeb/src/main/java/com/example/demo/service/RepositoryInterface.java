package com.example.demo.service;

import java.util.List;

public interface RepositoryInterface<T, TID> {

    /**
     * @return список всех объектов
     */
    List<T> findAll();

    /**
     * Добавление нового объекта.
     * @return добавленный объект
     */
    T save(T object);

    /**
     * @return объект с указанным ID
     */
    T getById(TID id);

    /**
     * Обновление данных объекта по его ID
     */
    void update(T object);

    /**
     * Удаление объекта с указанным ID
     */
    void deleteById(TID id);

}
