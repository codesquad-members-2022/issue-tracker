package codesquad.issuetracker.service;

import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueLabel;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.dto.issue.IssueCountDto;
import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueDtos;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.dto.issue.IssueStatusUpdateForm;
import codesquad.issuetracker.repository.IssueRepository;
import java.util.List;
import java.util.Map;
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
        List<Issue> issues = issueRepository.search(condition);

        return new IssueDtos(
            issues.stream()
                .map(issue -> IssueDto.of(issue,
                    issue.getMilestone(),
                    issue.getIssueLabels().stream().map(IssueLabel::getLabel).collect(Collectors.toList())
                    )
                )
                .collect(Collectors.toList())
        );
    }

    public IssueCountDto getCountOfIssuesByStatus() {
        Map<IssueStatus, Long> countOfIssuesByStatus = issueRepository.findCountOfIssuesByStatus();
        return new IssueCountDto(
            countOfIssuesByStatus.getOrDefault(IssueStatus.OPEN, 0L),
            countOfIssuesByStatus.getOrDefault(IssueStatus.CLOSED, 0L)
        );
    }

    @Transactional
    public void updateStatusByIssueId(IssueStatusUpdateForm updateForm) {
        issueRepository.update(updateForm.getUpdatedStatus(), updateForm.getIdOfIssues());
    }
}
