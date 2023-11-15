package com.api.keeper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_project", schema = "keeper")
public class UsersProject {
    @Id
    @Column(name = "project_id")
    private long projectId;

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "role_in_project")
    private UserRoleInProject userRoleInProject;
}
