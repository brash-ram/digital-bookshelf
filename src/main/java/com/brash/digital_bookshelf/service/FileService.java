package com.brash.digital_bookshelf.service;


import com.brash.digital_bookshelf.s3storage.S3File;

public interface FileService {
    void save(S3File file, String bucket);

    S3File get(String filename, String bucket);
}
