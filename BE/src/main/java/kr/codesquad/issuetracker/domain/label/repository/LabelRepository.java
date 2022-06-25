package kr.codesquad.issuetracker.domain.label.repository;

import kr.codesquad.issuetracker.domain.label.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

}
