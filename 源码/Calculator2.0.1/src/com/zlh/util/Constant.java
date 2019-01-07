package com.zlh.util;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import com.zlh.bean.Compute;

public class Constant {
	
	public static final String CheckHistory = "checkHistory";
	
	public static final String Calculate = "Calculate";
	
	public static String currentState;
	
	public static final List<Compute> history = new Vector<Compute>(3,2);
	
	public static final String[] menuBarItems = {"查看","编辑","帮助"};
	
	public static final String[][] menus = {
		{"标准型","科学型","历史记录","退出"},
		{"复制","粘贴"},
		{"关于计算器"}
	};
	
	public static final String[] marks = {
			"%","√","x^y","x^2",
			"CE","C","←","/",
			"7","8","9","×",
			"4","5","6","-",
			"1","2","3","+",
			"±","0",".","="
	};
	
	public static final String[] ScientificMarks = {
			"sin","cos","tan","log",
			"π","ln","e^x","10^x"
	};
	
	public static final Font globalFont = new Font("楷体",Font.PLAIN,22);
	
	public static final String ERROR = "错误";
	
	public static String lastResult;
	
	public static boolean newStart = false;
	
	public static final String PROTOCOL = "PGgzPuWFs+S6juivpeiuoeeul+WZqDwvaDM+PHA+PHN0cm9uZz7niYjmnKzvvJo8L3N0cm9uZz48ZW0+Mi4wLjE8L2VtPjxwPjxlbT48c3Ryb25nPuadjumjnjwvc3Ryb25nPuS/neeVmeaJgOacieadg+WIqeOAgjwvZW0+PHA+PGEgaHJlZj0iIyI+5L2/55So5p2h5qy+PC9hPjwvcD48cD48YSBocmVmPSIjIj7pmpDnp4Hor7TmmI48L2E+PC9wPg==";
	
}
