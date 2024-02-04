package org.example.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Service
public class S3Service {

    private final AmazonS3 amazonS3Client;

    @Value("#{s3['cloud.aws.s3.bucket']}")
    private String bucket;

    // MultipartFile을 S3에 직접 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String fileName = getUuidFileName(multipartFile.getOriginalFilename());
        String filePath = dirName + "/" + fileName;
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, filePath, multipartFile.getInputStream(), null)
                        .withCannedAcl(CannedAccessControlList.PublicRead)    // PublicRead 권한으로 업로드 됨
        );
        return filePath;
    }

    public String getImageUrl(String fileName) {
        if (fileName == null) {
            return null;
        }
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public void delete(String fileName) {
        amazonS3Client.deleteObject(bucket, fileName);
    }

    private static String getUuidFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }
}