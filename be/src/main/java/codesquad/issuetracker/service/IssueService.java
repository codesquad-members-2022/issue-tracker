package codesquad.issuetracker.service;

import codesquad.issuetracker.domain.Assignee;
import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueLabel;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueDtos;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.dto.issue.IssueStatusUpdateForm;
import codesquad.issuetracker.repository.IssueRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueDtos getIssuesByCriteria(IssueSearchCondition condition) {
        Set<String> exclusionConditions = getExclusionConditions(condition.getExclusions());
        List<Issue> issues = issueRepository.search(condition, exclusionConditions);
        Map<IssueStatus, Long> countOfIssuesByStatus = issueRepository.findCountOfIssuesByStatus(condition, exclusionConditions);

        return new IssueDtos(
            countOfIssuesByStatus.getOrDefault(IssueStatus.OPEN, 0L),
            countOfIssuesByStatus.getOrDefault(IssueStatus.CLOSED, 0L),
            issues.stream()
                .map(issue -> IssueDto.of(issue,
                    issue.getMilestone(),
                    issue.getAssignees().stream().map(Assignee::getMember).collect(Collectors.toList()),
                    issue.getIssueLabels().stream().map(IssueLabel::getLabel).collect(Collectors.toList())
                    )
                )
                .collect(Collectors.toList())
        );
    }

    private Set<String> getExclusionConditions(String exclusions) {
        Set<String> exclusionConditions = new HashSet<>();
        String[] params = exclusions.split(",");
        for (String param : params) {
            exclusionConditions.add(param.replaceAll(" ", "").toUpperCase());
        }
        return exclusionConditions;
    }

    @Transactional
    public void updateStatusByIssueId(IssueStatusUpdateForm updateForm) {
        issueRepository.update(updateForm.getUpdatedStatus(), updateForm.getIdOfIssues());
    }
}
