package team24.issuetracker.uploadedfile.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.uploadedfile.service.UploadService;

@RequiredArgsConstructor
@RestController
@RequestMapping("upload")
public class UploadController {

	private final UploadService uploadService;

	@PostMapping
	public void uploadFile(@RequestParam MultipartFile multipartFile) throws IOException {
		uploadService.saveFile(multipartFile);
	}
}
