package com.api.keeper.repository;

import com.api.keeper.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
    public Label findByLabel(String label);
    public boolean existsByLabel(String label);
}
