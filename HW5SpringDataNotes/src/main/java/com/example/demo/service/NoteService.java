package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с заметками
 */
@Service
@RequiredArgsConstructor
public class NoteService {

    /**
     * Интерфейс репозитория для работы с заметками
     */
    private final NoteRepository repository;

    /**
     * Создание заметки. Если поле даты создания заметки не заполнено, оно автоматически
     * устанавливается на текущее значение даты и времени в системе.
     * @param note объект заметки, содержащий заголовок и описание заметки,
     *             а также время создания (необязательно)
     * @return объект заметки со всеми полями и присвоенным ID
     */
    public Note createNote(Note note) {
        if (note.getCreateDate() == null) {
            note.setCreateDate(LocalDateTime.now());
        }
        return repository.save(note);
    }

    /**
     * Получение списка всех заметок из репозитория.
     * @return список заметок
     */
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    /**
     * Получение заметки из репозитория по ID.
     * @param id уникальный идентификатор заметки
     * @return Optional объект заметки с указанным ID
     */
    public Optional<Note> getNoteById(Long id) {
        return repository.findById(id);
    }

    /**
     * Обновление информации о заметке в репозитории по ID.
     * @param note объект заметки с нужным ID и информацией для замены
     * @return Optional объект заметки с указанным ID и обновленными данными
     */
    public Optional<Note> updateNote(Note note) {
        Note findNote = repository.findById(note.getId()).orElse(null);
        if (findNote != null) {
            if (note.getTitle() != null)
                findNote.setTitle(note.getTitle());
            if (note.getText() != null)
                findNote.setText(note.getText());
            if (note.getCreateDate() != null)
                findNote.setCreateDate(note.getCreateDate());
            findNote = repository.save(findNote);
        }
        return Optional.ofNullable(findNote);
    }

    /**
     * Удаление заметки с указанным ID.
     * @param id уникальный идентификатор заметки
     * @return Optional объект удаленной заметки с указанным ID
     */
    public Optional<Note> deleteNoteById(Long id) {
        Optional<Note> note = repository.findById(id);
        repository.deleteById(id);
        return note;
    }
}
