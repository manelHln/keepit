package com.api.keeper.repository;

import com.api.keeper.domain.Project;
import com.api.keeper.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProject(Project project);
}
