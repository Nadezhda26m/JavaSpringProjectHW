package com.kirin.demo.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Модель задачи
 */
@Data
@Entity
@Table(name = "tasks")
public class Task {

    /**
     * Идентификатор задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Описание задачи
     */
    @Column(nullable = false)
    private String description;

    /**
     * Статус задачи
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    /**
     * Дата создания задачи
     */
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    /**
     * Метод сравнения двух объектов класса Task, не учитывающий миллисекунды в
     * значении createDate.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id)
                && Objects.equals(description, task.description)
                && status == task.status
                && Objects.equals(
                        createDate.toString().split("\\.")[0],
                task.createDate.toString().split("\\.")[0]);
    }

}
