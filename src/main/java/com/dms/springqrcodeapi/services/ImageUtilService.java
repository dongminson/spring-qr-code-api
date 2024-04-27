package com.dms.springqrcodeapi.services;

import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import org.springframework.stereotype.Service;

@Service
public class ImageUtilService {

  public BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(
      width,
      height,
      BufferedImage.TYPE_INT_RGB
    );
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
      }
    }
    return image;
  }
}
