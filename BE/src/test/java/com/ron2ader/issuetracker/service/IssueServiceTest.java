package com.ron2ader.issuetracker.service;

import com.ron2ader.issuetracker.controller.issuedto.IssueCreateRequest;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.domain.issue.Issue;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    Member member;
    Issue issue;
    IssueCreateRequest issueCreateRequest;
    MemberDto memberDto;

    @BeforeEach
    @Transactional
    void setUp() {
        member = memberRepository.save(Member.of("ader", "www.github.com"));
        memberDto = new MemberDto("ader", "www.github.com");
        issue = Issue.of(member, "title", "contents", "url");
        issueCreateRequest = new IssueCreateRequest("title", "contents", null);
    }

    @Test
    @Transactional
    void registerIssue() {
        IssueDetailResponse issueDetailResponse = issueService.registerIssue(issueCreateRequest, memberDto);

        assertThat(issueDetailResponse.getMemberDto().getUserId()).isEqualTo("ader");
        assertThat(issueDetailResponse.getMemberDto().getAvatarUrl()).isEqualTo("www.github.com");
        assertThat(issueDetailResponse.getIssueDetail().getTitle()).isEqualTo("title");

    }

    @Test
    void findByIssueNumber() {
    }

    @Test
    void findAllByOpenStatus() {
    }
}
