package com.tutorial_java.learn.service;

import org.springframework.stereotype.Service;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;

@Service
public class MinioService {

    private final MinioClient minioClient;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void checkAndCreateBucket(String bucketName) {
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );

            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                System.out.println("✅ Bucket created: " + bucketName);
            } else {
                System.out.println("ℹ️ Bucket already exists: " + bucketName);
            }
        } catch (Exception e) {
            System.err.println("❌ Could not connect to MinIO: " + e.getMessage());
        }
    }
}