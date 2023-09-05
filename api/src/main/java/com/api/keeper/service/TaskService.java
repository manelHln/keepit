package com.api.keeper.service;

import com.api.keeper.domain.Label;
import com.api.keeper.domain.Subtask;
import com.api.keeper.domain.Task;
import com.api.keeper.domain.User;
import com.api.keeper.dto.TaskRequest;
import com.api.keeper.repository.LabelRepository;
import com.api.keeper.repository.SubtaskRepository;
import com.api.keeper.repository.TaskRepository;
import com.api.keeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SubtaskRepository subtaskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LabelRepository labelRepository;
    public String addTask(TaskRequest request) {
        try{
            List<Subtask> subtasks = new ArrayList<>();
            List<Label> labels = new ArrayList<>();
            if(!request.getSubtasks().isEmpty()){
                subtasks = subtaskRepository.saveAll(request.getSubtasks());
            }
            if(!request.getLabels().isEmpty()){
                labels = labelRepository.saveAll(request.getLabels());
            }
            User user = userRepository.findById(request.getAssignedTo()).orElseThrow();
            Task task = Task.builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .dueDate(request.getDueDate())
                    .priority(request.getPriority())
                    .assignedUser(user)
                    .subtasks((Set<Subtask>) subtasks)
                    .assignedLabels((Set<Label>) labels)
                    .build();
            taskRepository.save(task);
            return "Task created successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
