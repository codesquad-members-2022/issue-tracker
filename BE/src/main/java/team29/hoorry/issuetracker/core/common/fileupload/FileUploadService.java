package team29.hoorry.issuetracker.core.common.fileupload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {

	private static final String DIRECTORY = "issue-tracker/";

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	private final AmazonS3 amazonS3;

	public FileUploadResponse uploadFile(MultipartFile file) {
		String fileName = createUniqueFileName(file);
		upload(file, fileName);
		return new FileUploadResponse(getS3Url(fileName));
	}

	private String createUniqueFileName(MultipartFile file) {
		String originalFileName = file.getOriginalFilename();
		String fileExtension;
		try {
			fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		} catch (StringIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
		}
		return DIRECTORY + UUID.randomUUID().toString() + fileExtension;
	}

	private void upload(MultipartFile file, String fileName) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());
		try (InputStream inputStream = file.getInputStream()) {
			PutObjectRequest request = new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3.putObject(request);
		} catch (IOException e) {
			throw new IllegalArgumentException("파일 변환 중 에러가 발생하였습니다.");
		}
	}

	private String getS3Url(String fileName) {
		return amazonS3.getUrl(bucketName, fileName).toString();
	}
}
