package com.ron2ader.issuetracker.service;

import com.ron2ader.issuetracker.controller.issuedto.IssueCreateRequest;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssueSimpleResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.domain.member.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class IssueServiceTest {

    @Autowired
    IssueService issueService;

    @Autowired
    MemberRepository memberRepository;

    List<IssueCreateRequest> issues;
    IssueCreateRequest issueCreateRequest;
    MemberDto memberDto;

    @BeforeEach
    @Transactional
    void setUp() {
        memberDto = new MemberDto("ron2", "asdfasdf.com");
        issueCreateRequest = new IssueCreateRequest("title", "contents", null);

        issues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
             issues.add(new IssueCreateRequest("title" + i, "contents" + i, null));
        }
    }

    @Test
    @Transactional
    void registerIssueTest() {
        IssueDetailResponse issueDetailResponse = issueService.registerIssue(issueCreateRequest, memberDto);

        assertThat(issueDetailResponse.getMemberDto().getUserId()).isEqualTo("ron2");
        assertThat(issueDetailResponse.getMemberDto().getAvatarUrl()).isEqualTo("asdfasdf.com");
        assertThat(issueDetailResponse.getIssueDetail().getTitle()).isEqualTo("title");
    }

    @Test
    @Transactional
    void findByIdTest() {
        IssueDetailResponse issueDetailResponse = issueService.registerIssue(issueCreateRequest, memberDto);
        IssueDetailResponse findIssue = issueService.findById(issueDetailResponse.getIssueDetail().getId());

        assertThat(findIssue).isEqualTo(issueDetailResponse);
    }

    @Test
    @Transactional
    void findAllByOpenStatusPagingTest() {
        for (int i = 0; i < 10; i++) {
            issueService.registerIssue(issues.get(i), memberDto);
        }

        Page<IssueSimpleResponse> allByOpenStatus = issueService.findAllByOpenStatus(PageRequest.of(0, 5), true);

        assertThat(allByOpenStatus.getTotalElements()).isEqualTo(10);
        assertThat(allByOpenStatus.getSize()).isEqualTo(5);
    }
}
