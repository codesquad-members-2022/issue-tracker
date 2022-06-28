package com.ron2ader.issuetracker.service;

import com.ron2ader.issuetracker.controller.issuedto.*;
import com.ron2ader.issuetracker.controller.labeldto.LabelResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.controller.milestonedto.MilestoneResponse;
import com.ron2ader.issuetracker.domain.issue.IssueAssigneeRepository;
import com.ron2ader.issuetracker.domain.issue.IssueLabelRepository;
import com.ron2ader.issuetracker.domain.label.Label;
import com.ron2ader.issuetracker.domain.label.LabelRepository;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.domain.member.MemberRepository;
import com.ron2ader.issuetracker.domain.milestone.Milestone;
import com.ron2ader.issuetracker.domain.milestone.MilestoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class IssueServiceTest {

    private final IssueService issueService;

    @Autowired
    public IssueServiceTest(IssueService issueService) {
        this.issueService = issueService;
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("issue를 등록하면 등록된 이슈의 아이디를 반환한다.")
    void registerIssueTest() {
        // given & when
        Long createdIssueId = issueService.registerIssue("ron2",
                "my issue",
                "contents",
                List.of(1L, 2L),
                List.of(1L, 2L),
                1L);

        // then
        assertThat(createdIssueId).isEqualTo(4L);
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
