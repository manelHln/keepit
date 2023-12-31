package com.api.keeper.controller;

import com.api.keeper.domain.Task;
import com.api.keeper.dto.TaskRequest;
import com.api.keeper.repository.TaskRepository;
import com.api.keeper.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskRequest request){
        return ResponseEntity.ok(taskService.addTask(request));
    }

    @GetMapping("{projectId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long projectId){
        return ResponseEntity.ok(taskService.getProjectTask(projectId));
    }
}
