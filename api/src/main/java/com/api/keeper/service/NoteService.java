package com.api.keeper.service;

import com.api.keeper.domain.Note;
import com.api.keeper.domain.NoteStatus;
import com.api.keeper.domain.User;
import com.api.keeper.repository.NoteRepository;
import com.api.keeper.repository.TagRepository;
import com.api.keeper.repository.UserRepository;
import com.api.keeper.dto.NoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Note> getUserNotes(Long userId, NoteStatus noteStatus) {
        return noteRepository.findAllByNoteStatus(userId, noteStatus);
    }


    public void saveNote(NoteRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Note note = Note.builder()
                .content(request.getContent())
                .noteOwner(user)
                .noteStatus(NoteStatus.DEFAULT)
                .build();
        noteRepository.save(note);
        new ResponseEntity<>("Note successfully created", HttpStatus.CREATED);
    }

    public void updateNote(NoteRequest request, long id) throws Exception {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setContent(request.getContent());
        noteRepository.save(note);
    }

    public void deleteNote(long id) throws Exception {
        Note noteToDelete = noteRepository.findById(id).orElseThrow();
        noteToDelete.setNoteOwner(null);
        noteRepository.save(noteToDelete);
        noteRepository.deleteById(id);
    }

    public void pinNote(long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setNoteStatus(NoteStatus.PINNED);
        noteRepository.save(note);
    }

    public void moveToTrash(long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setNoteStatus(NoteStatus.TRASHED);
        noteRepository.save(note);
    }

    public void moveToArchive(long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setNoteStatus(NoteStatus.ARCHIVED);
        noteRepository.save(note);
    }

    public void makeDefault(long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setNoteStatus(NoteStatus.DEFAULT);
        noteRepository.save(note);
    }

    public List<Note> searchNote(String q, NoteStatus s, Long u) {
        String query = "%" + q + "%";
        return noteRepository.searchNotes(query, s, u);
    }

    public Page<Note> getAll() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
        return noteRepository.findAll(pageable);
    }
}
