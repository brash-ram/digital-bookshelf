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
import org.springframework.transaction.annotation.Transactional;
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
    public S3File getImage(long id) {
        Image image = imageService.getById(id);
        return fileService.get(image.getFilenameWithExtension(), s3Properties.getImageBucket());
    }

    @Transactional
    @Override
    public Image saveImage(String extension, byte[] content) {
        Image image = new Image()
                .setExtension(extension)
                .setBlurhash(getHashForFile(content));

        image = imageRepository.save(image);

        S3File file = new S3File(image.getId() + "." + extension, content);
        fileService.save(file, s3Properties.getImageBucket());

        return image;
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
        fileService.save(file, s3Properties.getImageBucket());

        return image;
    }
    private String getHashForFile(byte[] file) {
        BufferedImage bfImage = ImageUtils.byteArrayToBufferedImage(file);
        return BlurHash.encode(bfImage);
    }
}
