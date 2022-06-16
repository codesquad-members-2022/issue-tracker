package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueDtoList;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.repository.IssueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueDtoList getIssuesByCriteria(IssueSearchCondition condition) {
        List<IssueDto> issues = issueRepository.search(condition);
        for (IssueDto issue : issues) {
            issue.setLabels(issueRepository.findLabelsOfIssue(issue.getId()));
        }
        return new IssueDtoList(issues);
    }
}
