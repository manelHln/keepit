package com.api.keeper.controller;

import com.api.keeper.dto.ProjectRequest;
import com.api.keeper.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/new")
    public ResponseEntity<String> createNewProject(@RequestBody ProjectRequest request){
        return projectService.createNew(request);
    }

    @PutMapping("{id}/add-member/{userId}")
    public ResponseEntity<String> addMember(@PathVariable Long userId, @PathVariable Long id) throws Exception {
        return projectService.addMember(userId, id);
    }
}
