package team24.issuetracker.web.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team24.issuetracker.domain.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

	@Query("select l from Label l where l.isDeleted = false")
	List<Label> findAll();
}
