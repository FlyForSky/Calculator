package com.zlh.util;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.zlh.main.MainFrame;

public class FrameHelper {
	
	private static MainFrame mainFrame;
	
	private static JTextArea displayPanel;
	
	private static JPanel operatorPanel;
	
	private static JTextArea historyArea;
	
	private static JButton[] scientificMarks;
	
	private static MarkAction markAction;
	
	private static JMenuItem pasteMenu;
	
	public static void start(){
		mainFrame = new MainFrame();
	}
	
	public static MainFrame getMainFrame(){
		if(mainFrame==null){
			start();
		}
		return mainFrame;
	}
	
	public static MarkAction getMarkAction() {
		if(markAction==null){
			markAction = new MarkAction();
		}
		return markAction;
	}
	
	public static JMenuItem getPasteMenu() {
		return pasteMenu;
	}

	public static JMenuBar getMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		int countO = 0;
		MenuAction menuAction = new MenuAction();
		for(String mBarItemValue:Constant.menuBarItems){
			JMenu mBarItem = new JMenu(mBarItemValue);
			mBarItem.setFont(Constant.globalFont);
			int countT = 0;
			for(String menuValue:Constant.menus[countO]){
				JMenuItem menu = new JMenuItem(menuValue);
				menu.addActionListener(menuAction);
				menu.setFont(Constant.globalFont);
				mBarItem.add(menu);
				if(countO==0&&(countT==1||countT==2)){
					mBarItem.addSeparator();
				}
				if(countO==1&&countT==1){
					pasteMenu = menu;
				}
				countT++;
			}
			menuBar.add(mBarItem);
			countO++;
		}
		return menuBar;
	}
	
	public static JPanel getOperatorPanel(){
		if(operatorPanel==null){
			operatorPanel = new JPanel();
			GridLayout gridLayout = new GridLayout(0,4);
			gridLayout.setHgap(6);
			gridLayout.setVgap(5);
			operatorPanel.setLayout(gridLayout);
			for(String mark:Constant.marks){
				JButton markButton = new JButton(mark);
				markButton.setFont(Constant.globalFont);
				markButton.addActionListener(getMarkAction());
				operatorPanel.add(markButton);
			}
		}
		return operatorPanel;
	}
	
	public static JTextArea getDisplayArea(){
		if(displayPanel==null){
			displayPanel = new JTextArea("\n0");
			displayPanel.setRows(2);
			displayPanel.setFont(Constant.globalFont);
			displayPanel.setEditable(false);
		}
		return displayPanel;
	}
	
	public static JComponent getDisplayPane(){
		JScrollPane displayPane = new JScrollPane(getDisplayArea(),ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return displayPane;
	}
	
	public static JTextArea getHistoryArea() {
		if(historyArea == null){
			historyArea = new JTextArea();
			historyArea.setFont(Constant.globalFont);
			historyArea.setEditable(false);
		}
		return historyArea;
	}
	
	public static JComponent getHistoryPane(){
		JScrollPane historyPane = new JScrollPane(getHistoryArea(),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return historyPane;
	}
	
	public static JButton[] getScientificMarks() {
		if(scientificMarks==null){
			int count = Constant.ScientificMarks.length;
			scientificMarks = new JButton[count];
			for(String temp:Constant.ScientificMarks){
				count--;
				scientificMarks[count] = new JButton(temp);
				scientificMarks[count].addActionListener(getMarkAction());
			}
		}
		return scientificMarks;
	}
	
	public static void reset(Component component){
		component.repaint();
		component.validate();
	}
	
}
