package com.team33.backend.comment.service;

import com.team33.backend.comment.domain.Comment;
import com.team33.backend.emoji.controller.dto.cache.CommentCache;
import com.team33.backend.emoji.controller.dto.cache.EmojiCache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String, CommentCache> commentCacheRedisTemplate;
    private final CommentQueryRepository commentQueryRepository;

    private final CommentCaches commentCaches;

    @Transactional
    public List<CommentCache> findCommentCacheByIssueId(Long issueGroupId) {
        List<CommentCache> findCommentCaches = commentQueryRepository.findCommentsByIssueId(issueGroupId);
        List<Long> commentIds = findCommentCaches.stream()
                .map(CommentCache::getId)
                .collect(Collectors.toList());

        Map<Long, List<EmojiCache>> result = commentQueryRepository.findEmojisByCommentId(commentIds)
                .stream()
                .collect(groupingBy(EmojiCache::getCommentId));

        for (CommentCache comment : findCommentCaches) {
            if (result.get(comment.getId()) != null) {
                comment.registEmojis(result.get(comment.getId()));
            }
        }
        return findCommentCaches;
    }

    @Transactional
    public void updateCommentCache(Comment findComment) {
        CommentCache cache = getValueOperations().get(findComment.getId());
        if (cache != null) {
            getValueOperations().set(String.valueOf(requireNonNull(cache).getId()), requireNonNull(cache));
            return;
        }
        getValueOperations().set(String.valueOf(findComment.getId()), requireNonNull(cache));
    }

    private ValueOperations<String, CommentCache> getValueOperations() {
        return commentCacheRedisTemplate.opsForValue();
    }
}
