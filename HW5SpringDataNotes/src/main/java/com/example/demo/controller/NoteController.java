package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для обработки запросов, отправленных на /notes
 */
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    /**
     * Сервис для работы с заметками
     */
    private final NoteService service;

    /**
     * Запрос на создание заметки.
     * @param note объект заметки с заполненным заголовком и содержанием
     * @return созданная заметка со всеми полями и статус CREATED
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(service.createNote(note), HttpStatus.CREATED);
    }

    /**
     * Получение списка всех заметок.
     * @return список заметок и статус OK
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(service.getAllNotes(), HttpStatus.OK);
    }

    /**
     * Получение заметки по ID.
     * @param id уникальный идентификатор заметки
     * @return заметки с указанным ID или ошибка notFound, если заметка не найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> findNote = service.getNoteById(id);
        return findNote.map(note -> ResponseEntity.ok(note))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Обновление информации о заметке.
     * @param id уникальный идентификатор заметки, которую нужно обновить
     * @param note объект заметки с информацией для замены
     * @return объект заметки с обновленными данными или ошибка notFound, если заметка не найдена
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        note.setId(id);
        Optional<Note> updateNote = service.updateNote(note);
        return updateNote.map(n -> new ResponseEntity<>(n, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Удаление заметки по ID.
     * @param id уникальный идентификатор заметки, которую нужно удалить
     * @return объект заметки, которая была удалена, или ошибка notFound, если заметка не найдена
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id) {
        Optional<Note> deleteNote = service.deleteNoteById(id);
        return deleteNote.map(n -> new ResponseEntity<>(n, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
