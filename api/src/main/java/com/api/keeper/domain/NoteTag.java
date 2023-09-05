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
@Table(name = "note_tag", schema = "keeper")
@IdClass(NoteTagId.class)
public class NoteTag {
    @Id
    @Column(name = "note_id")
    private long noteId;

    @Id
    @Column(name = "tag_id")
    private long tagId;
}
