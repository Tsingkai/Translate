package translate.preference;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import translate.Activator;

/**
 * 这个页面是eclipse window -> preference 里面的相关设置
 * 
 * @author echo_lovely
 * @see https://github.com/supernova-explosion/translate/blob/main/src/translate/preference/TranslatePreferencePage.java
 * @since 2024年1月5日 23点10分
 */
public class TranslatePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String[][] sourceLanguage = new String[][] { { "自动检测", "auto" }, { "中文", "zh" }, { "英语", "en" },
			{ "日语", "jp" }, { "韩语", "kor" }, { "法语", "fra" }, { "俄语", "ru" } };

	public static final String[][] targetLanguage = new String[][] { { "中文", "zh" }, { "英语", "en" }, { "日语", "jp" },
			{ "韩语", "kor" }, { "法语", "fra" }, { "俄语", "ru" } };

	public TranslatePreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore()); // 设置以前设置过的内容
		setDescription("build date: 2024-01-06");
	}

	@Override
	public void init(IWorkbench arg0) {
	}

	@Override
	protected void createFieldEditors() {
		ComboFieldEditor sourceFieldEditor = new ComboFieldEditor(TranslatePreferenceConstants.SOURCE, "源语言：",
				sourceLanguage, getFieldEditorParent());
		ComboFieldEditor targetFieldEditor = new ComboFieldEditor(TranslatePreferenceConstants.TARGET, "目标语言：",
				targetLanguage, getFieldEditorParent());
		addField(sourceFieldEditor); // 源语言选择
		addField(targetFieldEditor); // 目标语言选择

		StringFieldEditor editorApiUrl = new StringFieldEditor(TranslatePreferenceConstants.API_URL, "API_URL",
				getFieldEditorParent());
		editorApiUrl.setFocus();
		editorApiUrl.setEmptyStringAllowed(false);
		editorApiUrl.setStringValue("https://fanyi-api.baidu.com/api/trans/vip/translate");

		StringFieldEditor editorAppID = new StringFieldEditor(TranslatePreferenceConstants.APP_ID, "APP_ID：",
				getFieldEditorParent());
		editorAppID.setEmptyStringAllowed(false);

		StringFieldEditor editorSecretKey = new StringFieldEditor(TranslatePreferenceConstants.SECRET_KEY,
				"SECRET_KEY：", getFieldEditorParent());
		editorSecretKey.setEmptyStringAllowed(false);
		editorSecretKey.getTextControl(getFieldEditorParent()).setEchoChar('*');

		addField(editorApiUrl); // 添加API_URL文本框
		addField(editorAppID); // 添加APP_ID文本框
		addField(editorSecretKey); // 添加SECRET_KEY文本框

	}

}
