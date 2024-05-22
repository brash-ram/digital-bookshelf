package com.brash.digital_bookshelf.usecase;

import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.s3storage.S3File;

public interface ImageUseCase {
    Image getProfileImageFromRobohash(User user);

    S3File getImage(long id);

    Image saveImage(String extension, byte[] content);
}
