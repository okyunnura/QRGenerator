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
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Hashtable;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private static final String original = "{\"V\":\"1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.\",\"CU\":\"佐藤太郎じゅげむじゅげむごこうのすりきれかいじゃりすいぎょのすいぎょうまつうんらいまつふうらいまつやぶらこうじのぶらこうやぶらこうじのぶらこうやぶらこうじのぶらこうやぶらこうじのぶらこうやぶらこうじの\",\"TD\":\"2023-11-01\",\"VS\":\"2016-01-01 19:00:00\",\"VE\":\"2016-01-01 19:00:00\",\"PR\":[999,999],\"PL\":999,\"PS\":0,\"TM\":999.99,\"TP\":1,\"BR\":999.99,\"BP\":\"以前心配されていた無呼吸は有りませんでした。現在は寝ている際も呼吸は正常に出来ていますが観察要です。以前心配されていた無\",\"CO\":\"Ｈ25年12月27日 アセスメント、ケアプラン計画案作成 Ｈ26年4月29日 痛みが少なくなり、車いす上で過ごす時間が多くなって¥nＨ25年12月28日 ケアカンファレンス、「ポータブルトイレで排泄した  きた。しかし、介助で移乗の際には怖がる様子が見られる¥n い」の課題ニーズに関する短期目標を「ポータブル Ｈ26年5月25日 ポータブルトイレへの移乗を目指して練習し、介助バー¥n トイレに移乗できる」に変更する  での移乗が出来つつあったが、見守りや声かけが不十分で¥nＨ25年12月29日 家族に説明し、同意を得て交付する  利用者自身で動き、転倒後移乗への怖さが出てしまった¥n   腰の痛みはひいて、車いす上での座位生活に戻りつつ¥nＨ26年1月 ポータブルトイレへの移乗を日中開始するが、ベッ  ある（アセスメント）¥n ド柵を持つと立位保持で精一杯となり、離すことが  今後の移乗のあり方を検討し、ケアプランの修正を行い¥n できない  たい¥n  Ｈ26年2月 移乗動作に徐々に慣れてきた。ベッド柵を離すことができるようになり、 ポータブルトイレの肘置きを持てるようになってきた。\",\"SO\":999.99,\"AP\":1,\"WI\":999,\"SL\":2,\"SP\":1,\"SD\":1,\"DF\":999,\"UR\":999,\"SK\":{\"v\":2,\"o\":\"以前心配されていた無呼吸は有りませんでした。現在は寝ている際も呼吸は正常に出来ていますが観察要です。以前心配されていた無\"},\"ME\":\"以前心配されていた無呼吸は有りませんでした。現在は寝ている際も呼吸は正常に出来ていますが観察要です。以前心配されていた無\",\"PN\":1,\"OR\":\"Ｈ25年12月27日 アセスメント、ケアプラン計画案作成 Ｈ26年4月29日 痛みが少なくなり、車いす上で過ごす時間が多くなって¥nＨ25年12月28日 ケアカンファレンス、「ポータブルトイレで排泄した  きた。しかし、介助で移乗の際には怖がる様子が見られる¥n い」の課題ニーズに関する短期目標を「ポータブル Ｈ26年5月25日 ポータブルトイレへの移乗を目指して練習し、介助バー¥n トイレに移乗できる」に変更する  での移乗が出来つつあったが、見守りや声かけが不十分で¥nＨ25年12月29日 家族に説明し、同意を得て交付する  利用者自身で動き、転倒後移乗への怖さが出てしまった¥n   腰の痛みはひいて、車いす上での座位生活に戻りつつ¥nＨ26年1月 ポータブルトイレへの移乗を日中開始するが、ベッ  ある（アセスメント）¥n ド柵を持つと立位保持で精一杯となり、離すことが  今後の移乗のあり方を検討し、ケアプランの修正を行い¥n できない  たい¥n  Ｈ26年2月 移乗動作に徐々に慣れてきた。ベッド柵を離すことができるようになり、 ポータブルトイレの肘置きを持てるようになってきた。\",\"VI\":0,\"GS\":1,\"HE\":0,\"DA\":1,\"EN\":0,\"DC\":1,\"BA\":0,\"SB\":1,\"BB\":1,\"PB\":[1,2,3],\"SY\":0,\"GM\":1,\"CP\":0,\"NC\":1,\"MA\":0,\"PC\":1,\"TA\":0,\"MS\":1,\"DP\":0,\"TW\":1,\"CA\":0,\"CE\":1,\"NM\":2,\"ST\":1,\"SC\":0,\"CN\":1,\"OX\":999,\"IN\":1,\"IS\":1,\"SG\":0,\"CG\":1,\"UG\":0,\"NT\":1,\"MD\":0,\"NU\":1,\"FH\":0,\"HC\":1,\"CR\":0,\"MC\":1,\"OL\":0,\"TO\":1,\"WM\":0,\"WK\":1,\"OI\":1,\"AO\":\"Ｈ25年12月27日 アセスメント、ケアプラン計画案作成 Ｈ26年4月29日 痛みが少なくなり、車いす上で過ごす時間が多くなって¥nＨ25年12月28日 ケアカンファレンス、「ポータブルトイレで排泄した  きた。しかし、介助で移乗の際には怖がる様子が見られる¥n い」の課題ニーズに関する短期目標を「ポータブル Ｈ26年5月25日 ポータブルトイレへの移乗を目指して練習し、介助バー¥n トイレに移乗できる」に変更する  での移乗が出来つつあったが、見守りや声かけが不十分で¥nＨ25年12月29日 家族に説明し、同意を得て交付する  利用者自身で動き、転倒後移乗への怖さが出てしまった¥n   腰の痛みはひいて、車いす上での座位生活に戻りつつ¥nＨ26年1月 ポータブルトイレへの移乗を日中開始するが、ベッ  ある（アセスメント）¥n ド柵を持つと立位保持で精一杯となり、離すことが  今後の移乗のあり方を検討し、ケアプランの修正を行い¥n できない  たい¥n  Ｈ26年2月 移乗動作に徐々に慣れてきた。ベッド柵を離すことができるようになり、 ポータブルトイレの肘置きを持てるようになってきた。\",\"RH\":{\"v\":[1,2,3,4,5,6],\"o\":\"以前心配されていた無呼吸は有りませんでした。現在は寝ている際も呼吸は正常に出来ていますが観察要です。以前心配されていた無\"},\"RC\":\"Ｈ25年12月27日 アセスメント、ケアプラン計画案作成 Ｈ26年4月29日 痛みが少なくなり、車いす上で過ごす時間が多くなって¥nＨ25年12月28日 ケアカンファレンス、「ポータブルトイレで排泄した  きた。しかし、介助で移乗の際には怖がる様子が見られる¥n い」の課題ニーズに関する短期目標を「ポータブル Ｈ26年5月25日 ポータブルトイレへの移乗を目指して練習し、介助バー¥n トイレに移乗できる」に変更する  での移乗が出来つつあったが、見守りや声かけが不十分で¥nＨ25年12月29日 家族に説明し、同意を得て交付する  利用者自身で動き、転倒後移乗への怖さが出てしまった¥n   腰の痛みはひいて、車いす上での座位生活に戻りつつ¥nＨ26年1月 ポータブルトイレへの移乗を日中開始するが、ベッ  ある（アセスメント）¥n ド柵を持つと立位保持で精一杯となり、離すことが  今後の移乗のあり方を検討し、ケアプランの修正を行い¥n できない  たい¥n  Ｈ26年2月 移乗動作に徐々に慣れてきた。ベッド柵を離すことができるようになり、 ポータブルトイレの肘置きを持てるようになってきた。\",\"SE\":\"Ｈ25年12月27日 アセスメント、ケアプラン計画案作成 Ｈ26年4月29日 痛みが少なくなり、車いす上で過ごす時間が多くなって¥nＨ25年12月28日 ケアカンファレンス、「ポータブルトイレで排泄した  きた。しかし、介助で移乗の際には怖がる様子が見られる¥n い」の課題ニーズに関する短期目標を「ポータブル Ｈ26年5月25日 ポータブルトイレへの移乗を目指して練習し、介助バー¥n トイレに移乗できる」に変更する  での移乗が出来つつあったが、見守りや声かけが不十分で¥nＨ25年12月29日 家族に説明し、同意を得て交付する  利用者自身で動き、転倒後移乗への怖さが出てしまった¥n   腰の痛みはひいて、車いす上での座位生活に戻りつつ¥nＨ26年1月 ポータブルトイレへの移乗を日中開始するが、ベッ  ある（アセスメント）¥n ド柵を持つと立位保持で精一杯となり、離すことが  今後の移乗のあり方を検討し、ケアプランの修正を行い¥n できない  たい¥n  Ｈ26年2月 移乗動作に徐々に慣れてきた。ベッド柵を離すことができるようになり、 ポータブルトイレの肘置きを持てるようになってきた。\",\"NV\":\"2016-01-01\",\"CS\":{\"v\":[1,2,3,4],\"o\":\"以前心配されていた無呼吸は有りませんでした。現在は寝ている際も呼吸は正常に出来ていますが観察要です。以前心配されていた無\"}}";
//	private static final String original = "{\"V\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"CU\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"TD\":\"2023-11-01\",\"VS\":\"2016-01-01 19:00:00\",\"VE\":\"2016-01-01 19:00:00\",\"PR\":[999,999],\"PL\":999,\"PS\":0,\"TM\":36.7,\"TP\":1,\"BR\":37.1,\"BP\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"CO\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"SO\":999.99,\"AP\":1,\"WI\":999,\"SL\":2,\"SP\":1,\"SD\":1,\"DF\":999,\"UR\":999,\"SK\":{\"v\":2,\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"},\"ME\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"PN\":1,\"OR\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"VI\":0,\"GS\":1,\"HE\":0,\"DA\":1,\"EN\":0,\"DC\":1,\"BA\":0,\"SB\":1,\"BB\":1,\"PB\":[1,2,3],\"SY\":0,\"GM\":1,\"CP\":0,\"NC\":1,\"MA\":0,\"PC\":1,\"TA\":0,\"MS\":1,\"DP\":0,\"TW\":1,\"CA\":0,\"CE\":1,\"NM\":2,\"ST\":1,\"SC\":0,\"CN\":1,\"OX\":999,\"IN\":1,\"IS\":1,\"SG\":0,\"CG\":1,\"UG\":0,\"NT\":1,\"MD\":0,\"NU\":1,\"FH\":0,\"HC\":1,\"CR\":0,\"MC\":1,\"OL\":0,\"TO\":1,\"WM\":0,\"WK\":1,\"OI\":1,\"AO\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"RH\":{\"v\":[1,2,3,4,5,6],\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"},\"RC\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"SE\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"NV\":\"2016-01-01\",\"CS\":{\"v\":[1,2,3,4],\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"}}";
//	private static final String original = "{\"V\":\"1.0.0\",\"CU\":\"日医 太郎\",\"TD\":\"2016-01-01\",\"VS\":\"2016-01-01 19:00:00\",\"VE\":\"2016-01-01 20:00:00\"}";
//	private static final String original = "{\"V\":\"1.0.0\",\"CU\":\"日医 太郎\",\"TD\":\"2016-01-01\",\"VS\":\"2016-01-01 19:00:00\",\"VE\":\"2016-01-01 20:00:00\",\"PR\":[90,110],\"PL\":100,\"PS\":1,\"TM\":36.5,\"TP\":1,\"BR\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"BP\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"CO\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"SO\":50,\"AP\":1,\"WI\":100,\"SL\":2,\"SP\":1,\"SD\":1,\"DF\":2,\"UR\":4,\"SK\":{\"v\":2,\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"},\"ME\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\",\"PN\":1,\"OR\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"VI\":1,\"GS\":1,\"HE\":1,\"DA\":1,\"EN\":1,\"DC\":1,\"BA\":1,\"SB\":1,\"BB\":1,\"PB\":[1,2,3],\"SY\":1,\"GM\":1,\"CP\":1,\"NC\":1,\"MA\":1,\"PC\":1,\"TA\":1,\"MS\":1,\"DP\":1,\"TW\":1,\"CA\":1,\"CE\":1,\"NM\":[1,2,3],\"ST\":1,\"SC\":1,\"CN\":1,\"OX\":10,\"IN\":1,\"IS\":1,\"SG\":1,\"CG\":1,\"UG\":1,\"NT\":1,\"MD\":1,\"NU\":1,\"FH\":1,\"HC\":1,\"CR\":1,\"MC\":1,\"OL\":1,\"TO\":1,\"WM\":1,\"WK\":1,\"OI\":1,\"AO\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"RH\":{\"v\":[1,2,3,4,5,6],\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"},\"RC\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"SE\":\"のくのうつむざぐがぶゔりぐほすもつゃへわやびへぃらぶぴぶゕせへをよびぺやぬぶはゎふゖゅじはばしおぐょゕゔたぼをこれはぼきのぇまをてりゕへさそゔぼえぽゐめるんくそつほべぺぜゐあぇゅゕずぇれあぅやゆりえけゔぱゎうめぉでづゆぃぱよゅつばらべぉえもぅうばどでやはざゔゃいゔげぉぽょがざゐざけぜぎめおぱぼぞぴむぺゐざせがをへあろゆぬにでぺゃはゕぶべゕびどどきもにおゆぼへさぷぼざじべげそにぞまやえげもれとかゔぅびけつこまぱどよぱなふどぶゆこそゅびわゅはたんもくにっりぜゑえぜぜゃつぴうょょはぱおこぽぬゕげつよんぜぼがすぼゅらくむぁもたわらすばそひぬゖぁげゎぃぶぱるねけゑきるゎじよゎなうぉぁぺつょとぁきかたぶぱふつをのうすめらわむもずるおせにやつしぅはむくはべろそとぁあじゖひられゅほきかくへぃあごばぉぴはにとけじぐゕへうつあらへゐゕてきぃぞほちばやだをびなべろまぉぱみみにであへとずぇけでぷれもやすぅけざぉてつぴむよげどびぺぷふえばかへぞがばょきせをれへぜぎむばごぎょずんむぬぞにねあおせゔさぁばちぃふあぱさがありろはぽゑぉらわべにゔこぞあだのぜりごねぎにどゅざなゖちぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"NV\":\"2016-01-01\",\"CS\":{\"v\":[1,2,3,4],\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼにつばこゑよふそゅちじをぢぷぉふだねけぉそねろうぢごぶわしらばまなべさ\"}}";
//	private static final String original = "{\"V\":\"1.0.0\",\"CU\":\"日医 太郎\",\"TD\":\"2016-01-01\",\"VS\":\"2016-01-01 19:00:00\",\"VE\":\"2016-01-01 20:00:00\",\"PR\":[90,110],\"PL\":100,\"PS\":1,\"TM\":36.5,\"TP\":1,\"BR\":\"ふせほふべずのそひ\",\"BP\":\"ふせほふべずのそひぷゖべづさ\",\"CO\":\"のくのうつむざぐがはゎふゖぉぶゖそちも\",\"SO\":50,\"AP\":1,\"WI\":100,\"SL\":2,\"SP\":1,\"SD\":1,\"DF\":2,\"UR\":4,\"SK\":{\"v\":2,\"o\":\"ふせほふべずのうぢごぶわしらばまなべさ\"},\"ME\":\"ふせねけぉそねろうぢごぶわしらばまなべさ\",\"PN\":1,\"OR\":\"のくわぺじびもびぉぶゖそちも\",\"VI\":1,\"GS\":1,\"HE\":1,\"DA\":1,\"EN\":1,\"DC\":1,\"BA\":1,\"SB\":1,\"BB\":1,\"PB\":[1,2,3],\"SY\":1,\"GM\":1,\"CP\":1,\"NC\":1,\"MA\":1,\"PC\":1,\"TA\":1,\"MS\":1,\"DP\":1,\"TW\":1,\"CA\":1,\"CE\":1,\"NM\":[1,2,3],\"ST\":1,\"SC\":1,\"CN\":1,\"OX\":10,\"IN\":1,\"IS\":1,\"SG\":1,\"CG\":1,\"UG\":1,\"NT\":1,\"MD\":1,\"NU\":1,\"FH\":1,\"HC\":1,\"CR\":1,\"MC\":1,\"OL\":1,\"TO\":1,\"WM\":1,\"WK\":1,\"OI\":1,\"AO\":\"のくのうつむざぐがそれげわぺじびもびぉぶゖそちも\",\"RH\":{\"v\":[1,2,3,4,5,6],\"o\":\"ふせほふべずのそなべさ\"},\"RC\":\"のくのうゖそちも\",\"SE\":\"のくのうつむざうょょはぱおぺにぬゎめそれげわぺじびもびぉぶゖそちも\",\"NV\":\"2016-01-01\",\"CS\":{\"v\":[1,2,3,4],\"o\":\"ふせほふべずのそひぷゖべづぴねやあぇすひゑぴゕょぼぶわしらばまなべさ\"}}";

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

			//小サイズ低補正での最大バイト数/QR1枚 - ヘッダ調整分
			int byteSize = 858 - 2;

			String versionHeader = "CARE01Z\r\n";

			String gzipContent = versionHeader + Base64.getEncoder().encodeToString(compression(original, ZipType.GZIP));
			int gzipBase64Size = gzipContent.getBytes(StandardCharsets.UTF_8).length;

			String zipContent = versionHeader + Base64.getEncoder().encodeToString(compression(original, ZipType.ZIP));
			int zipBase64Size = zipContent.getBytes(StandardCharsets.UTF_8).length;

			ByteArrayOutputStream gzipByteArray = new ByteArrayOutputStream();
			gzipByteArray.write(versionHeader.getBytes(StandardCharsets.UTF_8));
			gzipByteArray.write(compression(original, ZipType.GZIP));
			int gzipNoBaseSize = gzipByteArray.size();

			ByteArrayOutputStream zipByteArray = new ByteArrayOutputStream();
			zipByteArray.write(versionHeader.getBytes(StandardCharsets.UTF_8));
			zipByteArray.write(compression(original, ZipType.ZIP));
			int zipNoBaseSize = zipByteArray.size();

			int noZipContentSize = (versionHeader + original).getBytes(StandardCharsets.UTF_8).length;
			logger.info("no zip:{}", noZipContentSize);
			logger.info("gzip base64:{} noBase:{}", gzipBase64Size, gzipNoBaseSize);
			logger.info("zip base64:{} noBase:{}", zipBase64Size, zipNoBaseSize);
			logger.info("----------------");

			//GZip圧縮後のbyte配列
			String contents = versionHeader + Base64.getEncoder().encodeToString(compression(original, ZipType.GZIP));
			int contentsSize = contents.length();

			//必要QR枚数
			int pageSize = new BigDecimal(contentsSize).divide(new BigDecimal(byteSize), 0, BigDecimal.ROUND_UP).intValue();
			for (int page = 0; page < pageSize; page++) {
				int start = page * byteSize;
				int end = (start + byteSize) > contentsSize ? contentsSize : start + byteSize;
				logger.info("page:{} start:{} end:{}", page, start, end);
				//QR1枚分のバイトデータ
				String zipContents = contents.substring(start, end);

				//QRコード出力
				QRCodeWriter writer = new QRCodeWriter();

				hints.put(EncodeHintType.PAGING, String.format("%d,%d,%d", page, pageSize - 1, 120));

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

	enum ZipType {
		ZIP, GZIP
	}

	private byte[] compression(String message, ZipType type) throws IOException {
		byte[] values = message.getBytes(StandardCharsets.UTF_8);
		switch (type) {
			case ZIP:
				return zip(values);
			case GZIP:
				return gzip(values);
		}
		throw new IllegalStateException();
	}

	private byte[] zip(byte[] values) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
		ZipEntry entry = new ZipEntry("message");
		entry.setSize(values.length);
		zipOutputStream.putNextEntry(entry);
		zipOutputStream.write(values);
		zipOutputStream.closeEntry();
		zipOutputStream.close();

		return byteArrayOutputStream.toByteArray();
	}

	private byte[] gzip(byte[] values) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		gzipOutputStream.write(values);
		gzipOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}
}
