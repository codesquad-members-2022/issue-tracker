package com.team33.backend.emoji.repository;

import com.team33.backend.emoji.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
