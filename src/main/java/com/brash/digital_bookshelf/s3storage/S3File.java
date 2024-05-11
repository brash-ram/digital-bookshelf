package com.brash.digital_bookshelf.s3storage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class S3File {
    private String filename;
    private byte[] content;

    public S3File(String filename, byte[] content) {
        this.filename = filename;
        this.content = content;
    }

    public S3File(String filename) {
        this.filename = filename;
    }

    public S3File(byte[] content) {
        this.content = content;
    }
}
