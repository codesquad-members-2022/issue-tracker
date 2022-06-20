package codesquad.issuetracker.service;

import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.dto.issue.IssueCountDto;
import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueDtos;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.repository.IssueRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueDtos getIssuesByCriteria(IssueSearchCondition condition) {
        List<IssueDto> issues = issueRepository.search(condition);
        for (IssueDto issue : issues) {
            issue.setLabels(issueRepository.findLabelsOfIssue(issue.getId()));
        }
        return new IssueDtos(issues);
    }

    public IssueCountDto getCountOfIssuesByStatus() {
        Map<IssueStatus, Long> countOfIssuesByStatus = issueRepository.findCountOfIssuesByStatus();
        return new IssueCountDto(
            countOfIssuesByStatus.getOrDefault(IssueStatus.OPEN, 0L),
            countOfIssuesByStatus.getOrDefault(IssueStatus.CLOSED, 0L)
        );
    }
}
