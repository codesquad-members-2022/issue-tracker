package com.team33.backend.emoji.service;

import com.team33.backend.TestConfig;
import com.team33.backend.data.EmotionData;
import com.team33.backend.emoji.domain.Emotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@AutoConfigureTestDatabase
@Import({TestConfig.class, EmojiService.class})
@DisplayName("EmojiService 테스트")
class EmotionServiceTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    EmojiService emojiService;

    @BeforeEach
    void setUp() {
        EmotionData data = new EmotionData();
        List<Emotion> emotions = data.getEmotions();
        emotions.forEach(entity -> testEntityManager.persist(entity));
    }

    @Test
    @DisplayName("이모티콘 목록을 조회하는 경우 저장된 모든 이모티콘이 나온다.")
    void 전체_이모티콘_조회() {

        // when
        List<Emotion> emotions = emojiService.findEmotions();

        // then
        then(emotions.size()).isEqualTo(3);
    }

}
