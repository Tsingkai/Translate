package translate.bean;

/**
 * 来自百度翻译文档的错误码以及含义
 * 
 * @author echo_lovely
 * @see https://api.fanyi.baidu.com/doc/21
 * @since 2024年1月5日 23点08分
 */
public enum TranslateResultCode {
	SUCCESS(52000, "成功"), TIMEOUT(52001, "请求超时"), SYSTEM_ERROR(52002, "系统错误"), UNAUTHORIZED(52003, "未授权用户"),
	EMPTY_PARAMETER(54000, "必填参数为空"), SIGNATURE_ERROR(54001, "签名错误"), REQUEST_TOO_FREQUENTLY(54003, "访问频率受限"),
	INSUFFICIENT_FUNDS(54004, "账户余额不足"), FREQUENT_LONG_TEXT_REQUESTS(54005, "长文本翻译请求频繁"), ILLEGAL_IP(58000, "客户端IP非法"),
	UNSUPPORTED_LANGUAGE(58001, "译文语言方向不支持"), SERVICE_CLOSED(58002, "翻译服务当前已关闭"),
	API_AUTHENTICATION_FAILED(90107, "认证未通过或未生效"), UNKNOWN_ERROR(0000, "未知的错误");

	private final int code;
	private final String message;

	TranslateResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static TranslateResultCode getTranslateResultCode(int code) {
		for (TranslateResultCode item : TranslateResultCode.values()) {
			if (item.getCode() == code) {
				return item;
			}
		}
		return TranslateResultCode.UNKNOWN_ERROR;
	}

}
