package com.team33.backend.emoji.controller;

import com.team33.backend.emoji.controller.dto.EmotionResponse;
import com.team33.backend.emoji.service.EmojiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/issuegroup")
@RequiredArgsConstructor
public class EmojiController {

    private final EmojiService emojiService;

    @GetMapping("/emojis")
    public List<EmotionResponse> findEmotions() {
        return emojiService.findEmotions().stream()
                .map(EmotionResponse::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
