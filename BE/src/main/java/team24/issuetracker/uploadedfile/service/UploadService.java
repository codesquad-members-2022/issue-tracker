package team24.issuetracker.uploadedfile.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team24.issuetracker.uploadedfile.domain.UploadedFile;
import team24.issuetracker.uploadedfile.repository.UploadRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadService {

	private final AmazonS3Client amazonS3Client;
	private final UploadRepository uploadRepository;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Transactional
	public void saveFile(MultipartFile multipartFile) throws IOException {
		log.info("bucket = {}", bucket);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(multipartFile.getContentType());
		objectMetadata.setContentLength(multipartFile.getSize());

		String originalFileName = multipartFile.getOriginalFilename();
		int index = originalFileName.lastIndexOf(".");
		String ext = originalFileName.substring(index + 1);

		String storedFileName = UUID.randomUUID() + "." + ext;
		String key = "issuetracker/" + storedFileName;

		try (InputStream inputStream = multipartFile.getInputStream()) {
			amazonS3Client.putObject(new PutObjectRequest(bucket, key, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		}

		String storedFileUrl = amazonS3Client.getUrl(bucket, key).toString();
		uploadRepository.save(new UploadedFile(originalFileName, storedFileUrl));
	}
}
