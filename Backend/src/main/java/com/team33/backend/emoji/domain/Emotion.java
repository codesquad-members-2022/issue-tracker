package com.team33.backend.emoji.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Emotion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emotion;

    public Emotion(String emotion) {
        this.emotion = emotion;
    }

    protected Emotion() {
    }
}
