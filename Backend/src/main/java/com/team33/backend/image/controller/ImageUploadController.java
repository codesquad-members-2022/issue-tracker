package com.team33.backend.image.controller;

import com.team33.backend.image.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageUploadController {

    private final FileUploadService uploadService;

    @PostMapping
    void fileUpload(@RequestPart MultipartFile file) throws Exception {
        uploadService.uploadImage(file);
    }
}
