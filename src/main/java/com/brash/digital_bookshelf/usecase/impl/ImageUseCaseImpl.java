package com.brash.digital_bookshelf.usecase.impl;

import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.repository.ImageRepository;
import com.brash.digital_bookshelf.data.service.ImageService;
import com.brash.digital_bookshelf.s3storage.S3File;
import com.brash.digital_bookshelf.s3storage.config.S3Properties;
import com.brash.digital_bookshelf.service.FileService;
import com.brash.digital_bookshelf.service.RobohashClient;
import com.brash.digital_bookshelf.usecase.ImageUseCase;
import com.brash.digital_bookshelf.utils.ImageUtils;
import io.trbl.blurhash.BlurHash;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

@Service
@RequiredArgsConstructor
public class ImageUseCaseImpl implements ImageUseCase {

    private final ImageRepository imageRepository;

    private final RobohashClient robohashClient;

    private final FileService fileService;

    private final ImageService imageService;

    private final S3Properties s3Properties;

    @Override
    public S3File getImage(long id, String bucket) {
        Image image = imageService.getById(id);
        return fileService.get(image.getFilenameWithExtension(), bucket);
    }

    @SneakyThrows
    @Override
    public Image getProfileImageFromRobohash(User user) {
        S3File file = robohashClient.getImage(user.getUsername() + user.getPassword());

        if (file == null) return null;

        Image image = new Image()
                .setExtension("png")
                .setBlurhash(getHashForFile(file.getContent()));

        image = imageRepository.save(image);

        file.setFilename(image.getId() + ".png");
        fileService.save(file, s3Properties.getProfileImageBucket());

        return imageRepository.save(image);
    }
    private String getHashForFile(byte[] file) {
        BufferedImage bfImage = ImageUtils.byteArrayToBufferedImage(file);
        return BlurHash.encode(bfImage);
    }
}
