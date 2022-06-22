package com.team33.backend.comment.service;

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

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String, CommentCache> commentCacheRedisTemplate;
    private final CommentQueryRepository commentQueryRepository;

    private final CommentCaches commentCaches;

    @Transactional
    public List<CommentCache> findCommentCacheByIssueId(Long issuegroupId) {
        List<CommentCache> findCommentCaches = commentQueryRepository.findCommentsByIssueId(issuegroupId);
        List<Long> commentIds = findCommentCaches.stream()
                .map(CommentCache::getId)
                .collect(Collectors.toList());

        Map<Long, List<EmojiCache>> result = commentQueryRepository.findEmojisByCommentId(commentIds)
                .stream()
                .collect(groupingBy(EmojiCache::getCommentId));

        for (CommentCache comment : findCommentCaches) {
            comment.registEmojis(result.get(comment.getId()));
        }
        return findCommentCaches;
    }

    private ValueOperations<String, CommentCache> getValueOperations() {
        return commentCacheRedisTemplate.opsForValue();
    }
}
