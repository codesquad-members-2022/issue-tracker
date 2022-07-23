package louie.hanse.issuetracker.web.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.service.S3Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/upload-files")
@RestController
public class FileUploadController {
    private final S3Service s3Service;

    @PostMapping
    public Map<String, String> fileUpload(MultipartFile file) throws IOException {
        return Collections.singletonMap("url", s3Service.fileUpload(file));
    }
}
