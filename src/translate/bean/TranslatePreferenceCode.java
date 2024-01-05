package translate.bean;

public enum TranslatePreferenceCode {
	SUCCESS(99, "成功"), API_URL_EMPTY(100, "API_URL未填写"), APP_ID_EMPTY(101, "APP_ID未填写"),
	SECRET_KEY_EMPTY(102, "SECRET_KEY未填写");

	private final int code;
	private final String message;

	TranslatePreferenceCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
