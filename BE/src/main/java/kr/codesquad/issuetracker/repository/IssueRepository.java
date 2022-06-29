package kr.codesquad.issuetracker.repository;

import kr.codesquad.issuetracker.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long>, CustomIssueRepository {

    @Modifying
    @Query("update Issue i set i.status = :status where i.id in (:issueList)")
    void updateStatus(boolean status, List<Long> issueList);
}
