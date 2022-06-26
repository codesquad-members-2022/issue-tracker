package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
