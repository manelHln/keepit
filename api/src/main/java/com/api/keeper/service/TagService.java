package com.api.keeper.service;

import com.api.keeper.domain.Note;
import com.api.keeper.domain.Tag;
import com.api.keeper.repository.NoteRepository;
import com.api.keeper.repository.TagRepository;
import com.api.keeper.dto.TagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private NoteRepository noteRepository;

    public void saveTag(TagRequest request) {
        Note note = noteRepository.findById(request.getNoteId()).orElseThrow();
        if (tagRepository.existsByTagName(request.getTagname())) {
            Tag tag = tagRepository.findByTagName(request.getTagname());
            note.getAssignedTags().add(tag);
            noteRepository.save(note);
            ResponseEntity.ok("Tag successfully added to note");
            return;
        }
        Tag tag = Tag.builder()
                .tagName(request.getTagname())
                .notes(new HashSet<>())
                .build();
        tag.getNotes().add(note);
        tagRepository.save(tag);
        note.getAssignedTags().add(tag);
        noteRepository.save(note);
        ResponseEntity.ok("Tag added successfully");
    }

}
