package com.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(Application.class, args)) {
			Application application = context.getBean(Application.class);
			logger.info("app start");
			logger.info("----------------");
			application.generate();
			logger.info("----------------");
			logger.info("app end");
		}
	}

	private void generate() {
		try {
			String contents = "http://www.tagbangers.co.jp";
			BarcodeFormat format = BarcodeFormat.QR_CODE;
			int width = 300;
			int height = 300;

			Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
			hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.ISO_8859_1.name());
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix bitMatrix = writer.encode(contents, format, width, height, hints);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
			File file = File.createTempFile("barcode", ".png");
			ImageIO.write(image, "png", file);

			Runtime runtime = Runtime.getRuntime();
			runtime.exec("open " + file.getAbsolutePath());

			logger.info("file path:{}", file.getAbsolutePath());
		} catch (WriterException | IOException e) {
			logger.error("Exception:", e);
		}
	}
}
