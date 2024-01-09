package translate.bean;

import java.util.ArrayList;

/**
 * 翻译结果的结构
 * 
 * @author echo_lovely
 * @see https://api.fanyi.baidu.com/doc/21
 * @since 2024年1月5日 23点08分
 */
public class TranslateResult {
	/** 源语言 */
	private String from = "";
	/** 目的语言 */
	private String to = "";
	/** 翻译结果：每段形成一个中英文对照的翻译结果 */
	private ArrayList<TranslateMap> trans_result = new ArrayList<TranslateMap>();
	/** 错误码，仅当错误的时候出现 */
	private TranslateResultCode error_code = TranslateResultCode.SUCCESS;

	public TranslateResult() {
		super();
	}

	public TranslateResult(TranslateResultCode error_code) {
		super();
		this.error_code = error_code;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public ArrayList<TranslateMap> getTrans_result() {
		return trans_result;
	}

	public void setTrans_result(ArrayList<TranslateMap> trans_result) {
		this.trans_result = trans_result;
	}

	public TranslateResultCode getError_code() {
		return error_code;
	}

	public void setError_code(TranslateResultCode error_code) {
		this.error_code = error_code;
	}

	public boolean isDefault() {
		return this.from == "" && this.to == "" && this.trans_result.isEmpty();
	}
}
