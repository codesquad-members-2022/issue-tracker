package com.team33.backend.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.team33.backend.image.config.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class AwsS3UploadService implements UploadService {

    private final AmazonS3 amazonS3;
    private final S3Component component;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMeTadata, String fileName) {
        PutObjectRequest request = new PutObjectRequest(
                component.getBucket(), fileName, inputStream, objectMeTadata).withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(request);
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(component.getBucket(), fileName).toString();
    }
}
