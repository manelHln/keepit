package com.api.keeper.repository;

import com.api.keeper.domain.Project;
import com.api.keeper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOwner(User owner);

    List<Project> findAllByUsers(User user);
}
