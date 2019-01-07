package com.zlh.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MenuAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JMenuItem menu = (JMenuItem)arg0.getSource();
		String title = menu.getText();
		handle(title);
	}
	
	private void handle(String title){
		switch(title){
		case "复制":
			if(CheckMark.checkString(FrameHelper.getDisplayArea().getText())){
				if(Constant.Calculate.equals(Constant.currentState)){
					CopyAndPaste.getCopyAndPaste().setSysClipboardText(FrameHelper.getDisplayArea().getText());
				}
				if(Constant.CheckHistory.equals(Constant.currentState)){
					CopyAndPaste.getCopyAndPaste().setSysClipboardText(FrameHelper.getHistoryArea().getText());
				}
			}
			break;
		case "粘贴":
			FrameHelper.getDisplayArea().setText(CopyAndPaste.getCopyAndPaste().getSysClipboardText());
			break;
		case "退出":
			System.exit(-1);
			break;
		case "历史记录":
			FrameHelper.getMainFrame().changeToHistory();
			break;
		case "标准型":
			FrameHelper.getMainFrame().changeToStandard();;
			break;
		case "科学型":
			FrameHelper.getMainFrame().changeToScientific();
			break;
		case "关于计算器":
			FrameHelper.getMainFrame().showHelpDialog();
			break;
		default:break;
		}
	}
	
}
