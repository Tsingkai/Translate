package translate.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import translate.bean.TranslateMap;
import translate.bean.TranslatePreferenceCode;
import translate.bean.TranslateResult;
import translate.bean.TranslateResultCode;
import translate.service.Translate;

/**
 * 获取选择文本，进行翻译以及在界面窗口显示翻译结果
 * 
 * @author echo_lovely
 * @since 2024年1月5日 23点10分
 */
public class TranslateHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event); // eclipse 激活的窗口

		Translate translate = new Translate(); // 翻译对象
		if (translate.getCode() != TranslatePreferenceCode.SUCCESS) {
			MessageDialog.openInformation(window.getShell(), "配置错误", String.format(
					"错误详情：%s\n请先配置翻译插件参数！Window -> Preferences -> Translate", translate.getCode().getMessage()));
			return null;
		}

		ISelection selection = HandlerUtil.getCurrentSelection(event); // 获取选择的内容
		if (!(selection instanceof TextSelection))
			return null;
		TextSelection textSelection = (TextSelection) selection; // 读取文本
		String selectedText = textSelection.getText();

		TranslateResult result = translate.translate(selectedText); // 翻译，启动！
		if (result.getError_code() == TranslateResultCode.SUCCESS) { // 成功，正常翻译
			ArrayList<TranslateMap> translateMaps = result.getTrans_result();
			StringBuilder paragraph = new StringBuilder(); // 组装成段落
			for (TranslateMap translateMap : translateMaps) {
				if (paragraph.length() > 0)
					paragraph.append("\n");
				paragraph.append(translateMap.getDst());
			}
			PopupDialog dialog = createTranslatePopupDialog(window, event, paragraph.toString()); // 创建弹窗并打开
			dialog.open();
		} else {
			// 错误处理
			int errorCode = result.getError_code().getCode();
			String errorMessage = result.getError_code().getMessage();
			MessageDialog.openError(window.getShell(), "翻译错误",
					String.format("错误码：%d\n错误详情：%s", errorCode, errorMessage));
		}

		return null;
	}

	/**
	 * 创建一个PopupDialog，并设置其中要显示的文字和dialog出现的位置
	 * 
	 * @param window          要显示的编辑器窗口
	 * @param event           事件
	 * @param translateResult 翻译得到的结果
	 * @return PopupDialog对象
	 */
	private PopupDialog createTranslatePopupDialog(IWorkbenchWindow window, ExecutionEvent event,
			String translateResult) {
		@SuppressWarnings("deprecation")
		PopupDialog dialog = new PopupDialog(window.getShell(), PopupDialog.HOVER_SHELLSTYLE, true, false, false, false,
				"Translate", translateResult) {
			@Override
			protected Point getInitialLocation(Point initialSize) {
				Control control = HandlerUtil.getActiveEditor(event).getAdapter(Control.class);
				StyledText text = (StyledText) control;
				Point point = text.toDisplay(text.getLocationAtOffset(text.getCaretOffset()));
				return new Point(point.x + 2, point.y);
			}
		};
		return dialog;
	}
}
