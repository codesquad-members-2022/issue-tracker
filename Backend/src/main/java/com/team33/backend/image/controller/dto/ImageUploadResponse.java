package com.team33.backend.image.controller.dto;

import com.team33.backend.image.domain.Image;
import lombok.Getter;

@Getter
public class ImageUploadResponse {

    private final Long id;

    public ImageUploadResponse(Image image) {
        this.id = image.getId();
    }
}
