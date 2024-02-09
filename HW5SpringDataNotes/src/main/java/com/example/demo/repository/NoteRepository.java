package com.example.demo.repository;

import com.example.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс репозитория для работы с заметками
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
}
