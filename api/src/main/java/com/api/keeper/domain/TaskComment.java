package com.api.keeper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task_comment", schema = "keeper")
public class TaskComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User commentOwner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "id")
    private TaskComment parentComment;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
