package com.api.keeper.repository;

import com.api.keeper.domain.Note;
import com.api.keeper.domain.NoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    void deleteById(long id);

    @Query("SELECT n FROM Note n WHERE n.noteOwner.id = ?1 and n.noteStatus = ?2")
    List<Note> findAllByNoteStatus(long userId, NoteStatus noteStatusEnum);

    List<Note> searchByContentContains(String query);

    @Query("SELECT n FROM Note n WHERE n.content ILIKE ?1 AND n.noteStatus = ?2 AND n.noteOwner.id = ?3")
    List<Note> searchNotes(String query, NoteStatus noteStatusEnum, Long u);
}
