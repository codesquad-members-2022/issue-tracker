package dev.kukim.issues.milestone.domain.repository;

import dev.kukim.issues.milestone.domain.Milestone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileStoneRepository extends JpaRepository<Milestone, Long> {

	List<Milestone> findAllByIsOpen(boolean status);

}
