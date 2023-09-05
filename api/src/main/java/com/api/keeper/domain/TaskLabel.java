package com.api.keeper.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "task_label", schema = "keeper")
@IdClass(TaskLabelId.class)
public class TaskLabel {
    @Id
    @Column(name = "task_id")
    private long taskId;

    @Id
    @Column(name = "label_id")
    private long labelId;
}
