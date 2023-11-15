package com.api.keeper.service;

import com.api.keeper.domain.*;
import com.api.keeper.dto.TaskRequest;
import com.api.keeper.repository.*;
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

    @Autowired
    private ProjectRepository projectRepository;

    public String addTask(TaskRequest request) {
        try {
            Project project = projectRepository.findById(request.getProjectId()).orElseThrow();
            Set<Subtask> subtasks = new HashSet<>();
            Set<Label> labels = new HashSet<>();
            if (!request.getSubtasks().isEmpty()) {
                request.getSubtasks().stream().map(e -> {
                    Subtask subtask = Subtask.builder()
                            .content(e.getContent())
                            .build();
                    Subtask created = subtaskRepository.save(subtask);
                    subtasks.add(created);
                    return created;
                });
            }
            if (!request.getLabels().isEmpty()) {
                request.getLabels().stream().map(e -> {
                    if (labelRepository.existsByLabel(e.getName())) {
                        return null;
                    }
                    Label label = Label.builder()
                            .label(e.getName())
                            .build();
                    Label created = labelRepository.save(label);
                    labels.add(created);
                    return created;
                });
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
                    .project(project)
                    .build();
            taskRepository.save(task);
            return "Task created successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<Task> getProjectTask(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        return taskRepository.findAllByProject(project);
    }
}
