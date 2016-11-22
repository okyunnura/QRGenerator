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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private static final String original = "{\"1\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\",\"2\":\"2016-01-01 10:50:30\",\"3\":[\"10:00\",\"11:00\"],\"4\":[999.99,999.99],\"5\":999.99,\"6\":0,\"7\":[999.99,0],\"8\":1,\"9\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\",\"10\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\",\"11\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"12\":999.99,\"13\":0,\"14\":999.99,\"15\":3,\"16\":0,\"17\":0,\"18\":999.99,\"19\":999.99,\"20\":[2,\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\"],\"21\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\",\"22\":4,\"23\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"24\":[0,0,0,0,0,0,0,0,0,[0,1,2],0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,999.99,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\"],\"25\":[[0,1,2,3,4,5,6],\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\"],\"26\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\",\"27\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\",\"28\":\"2016-01-01\",\"29\":[[0,1,2,3,4],\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさしぶぬほやつどでずぽまじかもゃふちぃぃとぁうひひぜだぢめぼこりいめわとぞょんみの\"]}";

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(Application.class, args)) {
			Application application = context.getBean(Application.class);
			logger.info("----------------");
			application.generate();
			logger.info("----------------");
		}
	}

	private void generate() {
		try {
			BarcodeFormat format = BarcodeFormat.QR_CODE;
			int width = 3000;
			int height = 3000;

			//QRコードのオプション指定
			Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
			//低補正(L)
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			//小サイズ(Version20)
			hints.put(EncodeHintType.QR_VERSION, 20);
			hints.put(EncodeHintType.CHARACTER_SET, Charset.forName("ISO-8859-1").displayName());

			//前半
			byte[] zipContents = Arrays.copyOfRange(zip(original), 0, 858);
			//後半
//			byte[] zipContents = Arrays.copyOfRange(zip(original), 859, 1245);
			String contents = new String(zipContents, "ISO-8859-1");

			//QRコード出力
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix bitMatrix = writer.encode(contents, format, width, height, hints);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
			File file = File.createTempFile("barcode", ".png");
			ImageIO.write(image, "png", file);

			//出力されたQRコードを開く
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("open " + file.getAbsolutePath());

			logger.info("file path:{}", file.getAbsolutePath());
		} catch (WriterException | IOException e) {
			logger.error("Exception:", e);
		}
	}

	private byte[] zip(String message) throws IOException {
		byte[] bytes = message.getBytes("UTF-8");
		logger.info("start size:" + bytes.length);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry("message");
		entry.setSize(bytes.length);
		zos.putNextEntry(entry);
		zos.write(bytes);
		zos.closeEntry();
		zos.close();

		byte[] results = baos.toByteArray();
		logger.info("exit size: " + results.length);
		return results;
	}
}
