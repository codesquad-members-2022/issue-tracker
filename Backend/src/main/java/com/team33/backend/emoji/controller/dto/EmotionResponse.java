package com.team33.backend.emoji.controller.dto;

import com.team33.backend.emoji.domain.Emotion;
import lombok.Getter;

@Getter
public class EmotionResponse {

    private Long id;
    private String value;

    public EmotionResponse(Emotion emotion) {
        this.id = emotion.getId();
        this.value = emotion.getEmotion();
    }

    public EmotionResponse() {
    }
}
