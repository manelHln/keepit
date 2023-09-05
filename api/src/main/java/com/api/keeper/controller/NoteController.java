package com.api.keeper.controller;

import com.api.keeper.domain.Note;
import com.api.keeper.domain.NoteStatus;
import com.api.keeper.service.NoteService;
import com.api.keeper.dto.NoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("{id}")
    public ResponseEntity<List<Note>> getAuthenticatedUserDefaultNotes(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.getUserNotes(id, NoteStatus.DEFAULT), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<Note>> searchNote(@RequestParam String q, @RequestParam NoteStatus s, @RequestParam Long u){
        return ResponseEntity.ok(noteService.searchNote(q, s, u));
    }

    @GetMapping("/archives/{id}")
    public ResponseEntity<List<Note>> getAuthenticatedUserArchiveNotes(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.getUserNotes(id, NoteStatus.ARCHIVED), HttpStatus.OK);
    }

    @GetMapping("/trashes/{id}")
    public ResponseEntity<List<Note>> getAuthenticatedUserTrashNotes(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.getUserNotes(id, NoteStatus.TRASHED), HttpStatus.OK);
    }

    @GetMapping("/pins/{id}")
    public ResponseEntity<List<Note>> getAuthenticatedUserPinnedNotes(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.getUserNotes(id, NoteStatus.PINNED), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNote(@RequestBody NoteRequest request) {
        try {
            noteService.saveNote(request);
            return new ResponseEntity<>("Note Created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateNote(@RequestBody NoteRequest request, @PathVariable long id) {
        try {
            noteService.updateNote(request, id);
            return new ResponseEntity<>("Note updated successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNote(@PathVariable long id) {
        try {
            noteService.deleteNote(id);
            return new ResponseEntity<>("Note deleted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @PutMapping("{id}/pin")
    public ResponseEntity<String> pinNote(@PathVariable long id) {
        try {
            noteService.pinNote(id);
            return new ResponseEntity<>("Note pined successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("{id}/trash")
    public ResponseEntity<String> trash(@PathVariable long id) {
        try {
            noteService.moveToTrash(id);
            return new ResponseEntity<>("Note moved to trash successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("{id}/archive")
    public ResponseEntity<String> archive(@PathVariable long id) {
        try {
            noteService.moveToArchive(id);
            return new ResponseEntity<>("Note moved to archive successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}/default")
    public ResponseEntity<String> unArchive(@PathVariable long id) {
        try {
            noteService.makeDefault(id);
            return new ResponseEntity<>("Note recovered successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
