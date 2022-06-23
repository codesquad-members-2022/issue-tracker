package com.team33.backend.comment.service;

import com.team33.backend.comment.controller.dto.CommentWriteRequest;
import com.team33.backend.comment.domain.Comment;
import com.team33.backend.data.IssueData;
import com.team33.backend.data.IssueGroupData;
import com.team33.backend.data.MemberData;
import com.team33.backend.issue.domain.Issue;
import com.team33.backend.issuegroup.domain.IssueGroup;
import com.team33.backend.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@AutoConfigureTestDatabase
//@Import({TestConfig.class, CommentService.class})
@SpringBootTest
@Rollback
@Transactional
@DisplayName("CommentService 테스트")
class CommentServiceTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CommentService commentService;

    @BeforeEach
    void setUp() {
        MemberData memberData = new MemberData();
        entityManager.persist(memberData.getMember());
        Member savedMember = entityManager.find(Member.class, 1L);

        IssueGroupData issueGroupData = new IssueGroupData();
        entityManager.persist(issueGroupData.getIssueGroup());
        IssueGroup savedIssueGroup = entityManager.find(IssueGroup.class, 1L);

        IssueData issueData = new IssueData();
        Issue issue = issueData.getIssue();
        issue.registAuthor(savedMember);
        issue.registIssueGroup(savedIssueGroup);
        entityManager.persist(issue);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @DisplayName("이슈에 대한 댓글을 작성하면 해당 댓글이 조회된다.")
    void 댓글작성() throws Exception {

        // given
        Long issueId = 1L;
        String githubId = "devjun10";
        CommentWriteRequest request = new CommentWriteRequest("Hello-World");

        Comment newComment = commentService.writeComment(1L, issueId, githubId, request);
        entityManager.persist(newComment);

        entityManager.flush();
        entityManager.clear();

        // when
        Comment savedComment = entityManager.find(Comment.class, 1L);

        // then
        assertThat(savedComment.getId()).isEqualTo(1L);
        assertThat(savedComment.getContent()).isEqualTo(request.getContent());
    }
}
