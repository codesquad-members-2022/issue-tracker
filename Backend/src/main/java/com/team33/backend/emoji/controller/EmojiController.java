package com.team33.backend.emoji.controller;

import com.team33.backend.emoji.controller.dto.EmotionResponse;
import com.team33.backend.emoji.service.EmojiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emotions")
@RequiredArgsConstructor
public class EmojiController {

    private final EmojiService emojiService;

    @GetMapping
    public List<EmotionResponse> getEmotions() {
        return emojiService.getEmotions().stream()
                .map(EmotionResponse::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/{id}")
    public EmotionResponse getEmotionById(@PathVariable Long id) {
        return new EmotionResponse(emojiService.getEmotionById(id));
    }
}
