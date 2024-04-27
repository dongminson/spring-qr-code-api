package com.dms.springqrcodeapi.controllers;

import com.dms.springqrcodeapi.services.ImageUtilService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrCodeController {

  private final ImageUtilService imageUtilService;

  @Autowired
  public QrCodeController(ImageUtilService imageUtilService) {
    this.imageUtilService = imageUtilService;
  }

  @PostMapping("/generate")
  public ResponseEntity<byte[]> generate(@RequestBody String data) {
    try {
      Map<EncodeHintType, Object> hints = new HashMap<>();
      hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
      BitMatrix bitMatrix = new QRCodeWriter()
        .encode(data, BarcodeFormat.QR_CODE, 200, 200, hints);
      BufferedImage qrImage = imageUtilService.toBufferedImage(bitMatrix);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ImageIO.write(qrImage, "png", outputStream);
      byte[] qrCode = outputStream.toByteArray();
      return ResponseEntity.ok().body(qrCode);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
