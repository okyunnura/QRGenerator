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
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private static final String original = "{\"V\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"CU\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"TD\":\"2023-11-01\",\"VS\":\"2016-01-01\",\"VE\":\"2016-01-01\",\"PR\":[90,110],\"PL\":100,\"PS\":\"0\",\"TM\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"TP\":1,\"BR\":37.1,\"BP\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"CO\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"SO\":100,\"AP\":1,\"WI\":100,\"SL\":2,\"SP\":1,\"SD\":1,\"DF\":2,\"UR\":4,\"SK\":{\"v\":1,\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"},\"ME\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"PN\":1,\"OR\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"VI\":0,\"GS\":1,\"HE\":0,\"DA\":1,\"EN\":0,\"DC\":1,\"BA\":0,\"SB\":1,\"BT\":0,\"PB\":[1,2],\"SY\":0,\"GM\":1,\"CP\":0,\"NC\":1,\"MA\":0,\"PC\":1,\"TA\":0,\"MS\":1,\"DP\":0,\"TW\":1,\"CA\":0,\"CE\":1,\"NM\":0,\"ST\":1,\"SC\":0,\"CN\":1,\"OX\":1,\"IN\":1,\"IS\":1,\"SG\":0,\"CG\":1,\"UG\":0,\"NT\":1,\"MD\":0,\"NU\":1,\"FH\":0,\"HC\":1,\"CR\":0,\"MC\":1,\"OL\":0,\"TO\":1,\"WM\":0,\"WK\":1,\"OI\":1,\"AO\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"RH\":{\"v\":[1,2],\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"},\"RC\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"SE\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"NV\":\"2016-01-01\",\"CS\":{\"v\":[1,2],\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"}}";

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
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			//小サイズ(Version20)
			hints.put(EncodeHintType.QR_VERSION, 20);
			hints.put(EncodeHintType.CHARACTER_SET, Charset.forName("ISO-8859-1").displayName());

			//小サイズ低補正での最大バイト数/QR1枚
			int byteSize = 858 - 1;
			//Zip圧縮後のbyte配列
			byte[] contents = zip(original);
			int contentsSize = contents.length;
			//必要QR枚数
			int pageSize = new BigDecimal(contentsSize).divide(new BigDecimal(byteSize), 0, BigDecimal.ROUND_UP).intValue();
			for (int page = 0; page < pageSize; page++) {
				int start = page * byteSize + (page > 0 ? 1 : 0);
				int end = (start + byteSize) > contentsSize ? contentsSize: start + byteSize;
				logger.info("page:{} start:{} end:{}", page, start, end);
				//QR1枚分のバイトデータ
				byte[] zipContents = Arrays.copyOfRange(contents, start, end);

				//QRコード出力
				QRCodeWriter writer = new QRCodeWriter();
				BitMatrix bitMatrix = writer.encode(zipContents, format, width, height, hints);
				BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
				File file = File.createTempFile(String.format("barcode_%02d_", page + 1), ".png");
				ImageIO.write(image, "png", file);

				//出力されたQRコードを開く
				Runtime runtime = Runtime.getRuntime();
				runtime.exec("open " + file.getAbsolutePath());

				logger.info("file path:{}", file.getAbsolutePath());
			}
		} catch (WriterException | IOException e) {
			logger.error("Exception:", e);
		}
	}

	private byte[] zip(String message) throws IOException {
		byte[] bytes = message.getBytes("UTF-8");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry("message");
		entry.setSize(bytes.length);
		zos.putNextEntry(entry);
		zos.write(bytes);
		zos.closeEntry();
		zos.close();

		byte[] results = baos.toByteArray();
		return results;
	}
}
