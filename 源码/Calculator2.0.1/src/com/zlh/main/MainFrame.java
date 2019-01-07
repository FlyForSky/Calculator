package com.zlh.main;

import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.zlh.bean.Compute;
import com.zlh.util.Constant;
import com.zlh.util.FrameHelper;

public class MainFrame {
	
	private JFrame mainFrame;
	
	private JPanel content;
	
	public MainFrame(){
		super();
		init();
	}
	
	private void init(){
		createFrame();
		mainFrame.setJMenuBar(FrameHelper.getMenuBar());
		content = new JPanel();
		content.setLayout(new BorderLayout());
		mainFrame.add(content, BorderLayout.CENTER);
		changeToStandard();
		mainFrame.setVisible(true);
	}
	
	private void createFrame(){
		mainFrame = new JFrame("计算器");
		mainFrame.setSize(310, 437);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
	}
	
	public void changeToHistory(){
		Constant.currentState = Constant.CheckHistory;
		content.removeAll();
		content.add(FrameHelper.getHistoryPane(),BorderLayout.CENTER);
		JTextArea historyArea = FrameHelper.getHistoryArea();
		historyArea.setText("");
		if(Constant.history.size()>=1){
			for(Compute compute:Constant.history){
				historyArea.append(compute.print());
			}
		}
		else{
			historyArea.setText("没有历史纪录！");
		}
		FrameHelper.getPasteMenu().setEnabled(false);
		FrameHelper.reset(mainFrame);
	}
	
	public void changeToStandard(){
		mainFrame.setSize(310, 437);
		changeScientificOrStandard();
		FrameHelper.reset(mainFrame);
	}
	
	private void changeScientificOrStandard(){
		Constant.currentState = Constant.Calculate;
		content.removeAll();
		content.add(FrameHelper.getDisplayPane(), BorderLayout.NORTH);
		content.add(FrameHelper.getOperatorPanel(),BorderLayout.CENTER);
		for(JButton scientificMark:FrameHelper.getScientificMarks()){
			FrameHelper.getOperatorPanel().remove(scientificMark);
		}
		FrameHelper.getPasteMenu().setEnabled(true);
	}
	
	public void changeToScientific(){
		changeScientificOrStandard();
		mainFrame.setSize(310, 450);
		for(JButton scientificMark:FrameHelper.getScientificMarks()){
			FrameHelper.getOperatorPanel().add(scientificMark, 0);
		}
		FrameHelper.reset(mainFrame);
	}
	
	public void showHelpDialog(){
		JDialog helpDialog = new JDialog(mainFrame,"关于计算器",true);
		helpDialog.setSize(320, 400);
		helpDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		helpDialog.setLocationRelativeTo(null);
		helpDialog.setResizable(false);
		String protocol;
		try {
			protocol = new String(Base64.getDecoder().decode(Constant.PROTOCOL.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			protocol = null;
			e.printStackTrace();
		}
		JEditorPane helpPane = new JEditorPane("text/html", protocol);
		helpPane.setEditable(false);
		helpDialog.add(helpPane, BorderLayout.CENTER);
		helpDialog.setVisible(true);
	}
	
}
