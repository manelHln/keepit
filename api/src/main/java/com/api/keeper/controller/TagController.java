package com.api.keeper.controller;

import com.api.keeper.repository.NoteRepository;
import com.api.keeper.service.TagService;
import com.api.keeper.dto.TagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private NoteRepository noteRepository;

    @PostMapping
    public ResponseEntity<String> saveTag(@RequestBody TagRequest request){
        tagService.saveTag(request);
        return new ResponseEntity<>("Tag created successfully", HttpStatus.OK);
    }
}
