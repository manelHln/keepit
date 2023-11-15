package com.api.keeper.service;

import com.api.keeper.domain.Project;
import com.api.keeper.domain.Task;
import com.api.keeper.domain.User;
import com.api.keeper.dto.CustomResponse;
import com.api.keeper.dto.ProjectRequest;
import com.api.keeper.repository.ProjectRepository;
import com.api.keeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;
    public ResponseEntity<String> createNew(ProjectRequest request) {
        User owner = userRepository.findById(request.getOwnerId()).orElseThrow();
        Project project = Project
                .builder()
                .owner(owner)
                .name(request.getName())
                .description(request.getDescription())
                .build();
        projectRepository.save(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<String> addMember(Long userId, Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        boolean result = project.getUsers().add(user);
        if(!result) throw new Exception("User already member of the project");
        projectRepository.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Project> getUserAssignedProjects(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return projectRepository.findAllByUsers(user);
    }


    public List<Project> getUserOwnedProjects(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return projectRepository.findAllByOwner(user);
    }

    public Set<User> getProjectMembers(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow();
        return project.getUsers();
    }

    public Set<Task> getProjectTasks(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow();
        return project.getTasks();
    }
}
