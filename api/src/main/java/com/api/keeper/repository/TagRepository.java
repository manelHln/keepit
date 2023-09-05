package com.api.keeper.repository;

import com.api.keeper.controller.TagController;
import com.api.keeper.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Boolean existsByTagName(String tagName);
    Tag findByTagName(String tagName);
}
