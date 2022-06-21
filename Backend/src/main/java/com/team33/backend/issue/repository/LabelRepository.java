package com.team33.backend.issue.repository;

import com.team33.backend.issue.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
