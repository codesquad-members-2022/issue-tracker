package codesquad.issuetracker.service;

import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueLabel;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueDtos;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.dto.issue.IssueStatusUpdateForm;
import codesquad.issuetracker.repository.IssueRepository;
import java.util.HashMap;
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
        Set<String> labelConditions = condition.parseLabelConditions();
        Set<String> exclusionConditions = condition.parseExclusionConditions();
        Map<IssueStatus, Long> countOfIssuesByStatus = new HashMap<>();
        List<Issue> issues = issueRepository.search(condition, labelConditions, exclusionConditions)
            .stream()
            .filter(issue -> containsAllLabels(issue.getIssueLabels(), labelConditions))
            .collect(Collectors.toList());


        return new IssueDtos(
            countOfIssuesByStatus.getOrDefault(IssueStatus.OPEN, 0L),
            countOfIssuesByStatus.getOrDefault(IssueStatus.CLOSED, 0L),
            issues.stream()
                .filter(issue -> issue.hasSameStatus(condition.getStatus()))
                .map(issue -> IssueDto.of(issue))
                .collect(Collectors.toList())
        );
    }

    private Map<IssueStatus, Long> getCountOfIssuesByStatus(List<Issue> issues) {
        Map<IssueStatus, Long> countOfIssuesByStatus = new HashMap<>();
        for (Issue issue : issues) {
            countOfIssuesByStatus.put(issue.getStatus(),
                countOfIssuesByStatus.getOrDefault(issue.getStatus(), 0L) + 1);
        }
        return countOfIssuesByStatus;
    }

    private boolean containsAllLabels(List<IssueLabel> issueLabels, Set<String> labelConditions) {
        Set<String> labelsOfIssue = issueLabels.stream()
            .map(issueLabel -> issueLabel.getLabel().getName()).collect(Collectors.toSet());

        return labelsOfIssue.containsAll(labelConditions);
    }

    @Transactional
    public void updateStatusByIssueId(IssueStatusUpdateForm updateForm) {
        issueRepository.update(updateForm.getUpdatedStatus(), updateForm.getIdOfIssues());
    }
}
