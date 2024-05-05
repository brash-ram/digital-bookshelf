package com.brash.digital_bookshelf.utils;

import com.brash.digital_bookshelf.exception.AppException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageUtils {
    public static BufferedImage byteArrayToBufferedImage(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new AppException(e);
        }
    }
}
