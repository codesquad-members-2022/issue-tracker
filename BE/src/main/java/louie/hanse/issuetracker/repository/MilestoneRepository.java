package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    @Query("select m from Milestone as m where m.completedDate < :currentDate")
    List<Milestone> findClosedMilestone(@Param("currentDate") LocalDate currentDate);

    @Query("select m from Milestone as m where m.completedDate > :currentDate")
    List<Milestone> findOpenedMilestone(@Param("currentDate") LocalDate currentDate);
}
