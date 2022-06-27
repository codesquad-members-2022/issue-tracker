package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.dto.IssueResponse;
import kr.codesquad.issuetracker.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    public List<IssueResponse> getOpenedIssue(){
        return issueRepository.findOpenedIssues()
                .stream().map(IssueResponse::new).collect(Collectors.toList());
    }
}
