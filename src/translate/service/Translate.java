package translate.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.eclipse.jface.preference.IPreferenceStore;

import translate.Activator;
import translate.bean.TranslatePreferenceCode;
import translate.bean.TranslateResult;
import translate.preference.TranslatePreferenceConstants;
import translate.utils.GsonUtil;
import translate.utils.HttpUtil;
import translate.utils.MD5Util;
import translate.utils.TextUtil;

/**
 * 负责翻译相关的内容
 * 
 * @author echo_lovely
 * @see https://api.fanyi.baidu.com/doc/21
 * @since 2024年1月5日 23点08分
 */
public class Translate {
	/** 翻译api的地址 */
	private final String API_URL;
	/** 翻译api的APP_ID */
	private final String APP_ID;
	/** 翻译api的Secret_Ke y */
	private final String Secret_Key;
	/** 读取设置中的相关内容，如果出错则封装并返回 */
	private TranslatePreferenceCode code = TranslatePreferenceCode.SUCCESS;

	/**
	 * 构造方法，在构造方法中读取相关内容然后设置翻译相关的内容
	 */
	public Translate() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String url = preferenceStore.getString(TranslatePreferenceConstants.API_URL);
		String appID = preferenceStore.getString(TranslatePreferenceConstants.APP_ID);
		String key = preferenceStore.getString(TranslatePreferenceConstants.SECRET_KEY);

		if (url.isEmpty())
			this.code = TranslatePreferenceCode.API_URL_EMPTY;
		if (appID.isEmpty()) {
			this.code = TranslatePreferenceCode.APP_ID_EMPTY;
		}
		if (key.isEmpty()) {
			this.code = TranslatePreferenceCode.SECRET_KEY_EMPTY;
		}
		this.API_URL = url;
		this.APP_ID = appID;
		this.Secret_Key = key;
	}

	/**
	 * 翻译文本
	 * 
	 * @param translateString 待翻译的字符串
	 * @param fromLanguage    原语言
	 * @param toLanguage      目的语言
	 * @return 翻译结果数组
	 * @throws Exception
	 */
	public TranslateResult translate(String translateString, String fromLanguage, String toLanguage) {
		if (translateString.trim().length() != 0) {
			translateString = translateString.trim().replace("//", "").replace("// ", "").replace("/**", "")
					.replace(" * ", "").replace("\t", "");
			String url = generateRequestUrl(translateString, fromLanguage, toLanguage);
			String response = HttpUtil.get(url);
			String stringJson = TextUtil.unicode2UTF8(response);
			TranslateResult result = GsonUtil.json2Bean(stringJson, TranslateResult.class);
			return result;
		}
		return null;
	}

	/**
	 * 
	 * @param translateString
	 * @param fromLanguage
	 * @param toLanguage
	 * @return
	 */
	public String generateRequestUrl(String translateString, String fromLanguage, String toLanguage) {
		String templateString = "";
		try {
			String salt = generateSalt();
			String sign = MD5Util.getMD5Hash(APP_ID + translateString + salt + Secret_Key);
			templateString = String.format("%s?q=%s&from=%s&to=%s&appid=%s&salt=%s&&sign=%s", API_URL,
					URLEncoder.encode(translateString, "UTF-8"), fromLanguage, toLanguage, APP_ID, salt, sign);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return templateString;
	}

	private String generateSalt() {
		String salt = String.valueOf(new Random().nextInt());
		while (salt.length() < 8) {
			salt = '0' + salt;
		}
		return salt;
	}

	public TranslatePreferenceCode getCode() {
		return code;
	}

	public void setCode(TranslatePreferenceCode code) {
		this.code = code;
	}
}
