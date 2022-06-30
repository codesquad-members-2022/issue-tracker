package com.ron2ader.issuetracker.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.ron2ader.issuetracker.controller.issuedto.IssueDetail;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssueFilter;
import com.ron2ader.issuetracker.controller.issuedto.IssuesResponse;
import com.ron2ader.issuetracker.controller.labeldto.LabelResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.controller.milestonedto.MilestoneResponse;
import com.ron2ader.issuetracker.domain.issue.IssueRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class IssueServiceTest {

    private final IssueService issueService;
    private final IssueRepository issueRepository;

    @Autowired
    public IssueServiceTest(IssueService issueService,
        IssueRepository issueRepository) {
        this.issueService = issueService;
        this.issueRepository = issueRepository;
    }

    @Test
    @DisplayName("issue를 등록하면 등록된 이슈의 아이디를 반환한다.")
    void registerIssueTest() {
        // given & when
        long issueCount = issueRepository.count();

        Long createdIssueId = issueService.registerIssue("ron2",
                "my issue",
                "contents",
                List.of(1L, 2L),
                List.of(1L, 2L),
                1L);

        // then
        assertThat(createdIssueId).isEqualTo(issueCount + 1);
    }


    @Test
    @DisplayName("이슈 아이디로 이슈를 조회하면 issuer, issueDetail, milestone, assignees," +
            " labels를 모두 담은 IssueDetailResponse를 반환한다.")
    void findByIdTest() {
        //given & when
        IssueDetailResponse issueDetailResponse = issueService.findById(1L);
        MemberDto issuer = issueDetailResponse.getIssuer();
        IssueDetail issueDetail = issueDetailResponse.getIssueDetail();
        MilestoneResponse milestone = issueDetailResponse.getMilestone();
        List<MemberDto> assignees = issueDetailResponse.getAssignees();
        List<LabelResponse> labels = issueDetailResponse.getLabels();

        //then
        assertThat(issuer.getMemberId()).isEqualTo("ron2");
        assertThat(issueDetail.getId()).isEqualTo(1L);
        assertThat(milestone.getId()).isEqualTo(1L);
        assertThat(assignees).hasSize(2);
        assertThat(labels).hasSize(2);
    }

    @Test
    @DisplayName("issue filter로 조회하면 조건에 맞는 issuesResponse를 반환한다.")
    void findByFilterTest() {
        IssueFilter issueFilter = new IssueFilter(true, 1L, 1L, 1L, 1L);

        IssuesResponse issuesResponse = issueService.findByIssueFilter(issueFilter);

        assertThat(issuesResponse.getOpenCount()).isEqualTo(4);
        assertThat(issuesResponse.getCloseCount()).isEqualTo(0);
    }

}
