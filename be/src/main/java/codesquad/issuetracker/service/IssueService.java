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
        List<Issue> issues = issueRepository.search(condition, labelConditions, exclusionConditions)
            .stream()
            .filter(issue -> containsAllLabels(issue.getIssueLabels(), labelConditions))
            .collect(Collectors.toList());

        List<IssueDto> selectedIssues = issues.stream()
            .filter(issue -> issue.hasSameStatus(condition.getStatus()))
            .map(issue -> IssueDto.of(issue))
            .collect(Collectors.toList());

        long[] countOfIssuesByStatus = calculateCountOfIssuesByStatus(issues.size(), selectedIssues.size(), condition.isOpenStatus());

        return new IssueDtos(countOfIssuesByStatus[0], countOfIssuesByStatus[1], selectedIssues);
    }

    private boolean containsAllLabels(List<IssueLabel> issueLabels, Set<String> labelConditions) {
        Set<String> labelsOfIssue = issueLabels
            .stream()
            .map(issueLabel -> issueLabel.getLabelName())
            .collect(Collectors.toSet());

        return labelsOfIssue.containsAll(labelConditions);
    }

    private long[] calculateCountOfIssuesByStatus(long totalCount, long countOfSelectedIssues, boolean isOpenStatus) {
        long countOfOpenIssues, countOfClosedIssues;

        if (isOpenStatus) {
            countOfOpenIssues = countOfSelectedIssues;
            countOfClosedIssues = totalCount - countOfSelectedIssues;
        } else {
            countOfOpenIssues = totalCount - countOfSelectedIssues;
            countOfClosedIssues = countOfSelectedIssues;
        }

        return new long[]{countOfOpenIssues, countOfClosedIssues};
    }

    @Transactional
    public void updateStatusByIssueId(IssueStatusUpdateForm updateForm) {
        issueRepository.update(updateForm.getUpdatedStatus(), updateForm.getIdOfIssues());
    }
}
