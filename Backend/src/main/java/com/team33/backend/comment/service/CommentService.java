package com.team33.backend.comment.service;

import com.team33.backend.comment.domain.Comment;
import com.team33.backend.comment.controller.dto.CommentEditRequest;
import com.team33.backend.comment.controller.dto.CommentWriteRequest;
import com.team33.backend.comment.repository.CommentRepository;
import com.team33.backend.emoji.controller.dto.cache.CommentCache;
import com.team33.backend.issue.domain.Issue;
import com.team33.backend.issue.repository.IssueRepository;
import com.team33.backend.member.domain.Member;
import com.team33.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final IssueRepository issueRepository;
    private final CacheService cacheService;

    @Transactional
    public Comment writeComment(String githubId, CommentWriteRequest request) {
        // 임시 예외처리
        Member findMember = memberRepository.findByGithubId(githubId).orElseThrow();
        Issue findIssue = issueRepository.findById(request.getIssueId()).orElseThrow();

        // 이미지는 일단 없다고 가정
        Comment newComment = new Comment(request.getContent(), findMember, findIssue);
        return commentRepository.save(newComment);
    }

    @Transactional
    public Comment editComment(Long commentId, CommentEditRequest request) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow();
        findComment.editComment(request.getContent());
        return findComment;
    }

    @Transactional
    public Comment deleteComment(Long commentId) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow();
        findComment.deleteComment();
        return findComment;
    }

    @Transactional
    public List<CommentCache> findCommentsByIssueId(Long issueId) {
        return cacheService.findCommentCacheByIssueId(issueId);
    }
}
