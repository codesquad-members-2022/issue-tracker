package com.team33.backend.data;

import com.team33.backend.emoji.domain.Emotion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmotionData {

    private List<Emotion> emotions;

    public List<Emotion> getEmotions() {
        if (emotions == null || emotions.size() == 0) {
            this.emotions = createEmotions();
        }
        return this.emotions;
    }

    private List<Emotion> createEmotions() {
        List<Emotion> emotions = new ArrayList<>();
        emotions.add(createSmileEmotion());
        emotions.add(createThumbEmotion());
        emotions.add(createLoveEmotion());
        return emotions;
    }

    private Emotion createSmileEmotion() {
        return new Emotion("smile");
    }

    private Emotion createThumbEmotion() {
        return new Emotion("thumb");
    }

    private Emotion createLoveEmotion() {
        return new Emotion("love");
    }
}
