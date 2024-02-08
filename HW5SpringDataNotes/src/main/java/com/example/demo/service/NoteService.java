package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public Note createNote(Note note) {
        if (note.getCreateDate() == null) {
            note.setCreateDate(LocalDateTime.now());
        }
        return repository.save(note);
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return repository.findById(id);
    }

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

    public Optional<Note> deleteNoteById(Long id) {
        Optional<Note> note = repository.findById(id);
        repository.deleteById(id);
        return note;
    }
}
