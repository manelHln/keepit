package com.api.keeper.dto;

import com.api.keeper.domain.Label;
import com.api.keeper.domain.Subtask;
import com.api.keeper.domain.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    private String title;
    private String content;
    private TaskPriority priority;
    private LocalDateTime dueDate;
    private Long assignedTo;
    private Long projectId;
    private Set<SubtaskRequest> subtasks = new HashSet<>();
    private Set<LabelRequest> labels = new HashSet<>();
}
