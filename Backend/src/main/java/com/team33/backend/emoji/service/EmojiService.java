package com.team33.backend.emoji.service;

import com.team33.backend.emoji.domain.Emotion;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmojiService {

    private final EmotionRepository emotionRepository;

    @Transactional
    @Cacheable(value = "getEmotions", key = "#emotion", condition = "#emotion!=null")
    public List<Emotion> getEmotions(){
        return emotionRepository.findAll();
    }

    public Emotion getEmotionById(Long id) {
        return emotionRepository.findById(id).orElseThrow();
    }
}
