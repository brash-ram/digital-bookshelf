package com.brash.digital_bookshelf.service.impl;


import com.brash.digital_bookshelf.s3storage.S3Client;
import com.brash.digital_bookshelf.s3storage.S3File;
import com.brash.digital_bookshelf.service.FileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final S3Client minioClient;

    @Override
    @SneakyThrows
    public void save(S3File file, String bucket) {
        minioClient.put(file, bucket);
    }

    @Override
    public S3File get(String filename, String bucket) {
        return new S3File(filename, minioClient.get(filename, bucket));
    }
}
