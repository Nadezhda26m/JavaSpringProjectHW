package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Сущность 'Заметка', привязанная к таблице 'notes' в базе данных
 */
@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
public class Note {

    /**
     * Уникальный идентификатор заметки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Заголовок заметки
     */
    @Column(nullable = false)
    private String title;

    /**
     * Описание (содержание) заметки
     */
    @Column(nullable = false)
    private String text;

    /**
     * Дата создания заметки
     */
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    /**
     * Конструктор для задания заголовка и описания заметки
     */
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
