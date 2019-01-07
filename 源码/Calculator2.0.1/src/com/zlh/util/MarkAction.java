package com.zlh.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.zlh.bean.Compute;
import com.zlh.main.ExecutorService;
import com.zlh.util.execute.Executor;

public class MarkAction implements ActionListener {

	private Executor executor;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		handle((JButton)arg0.getSource());
	}
	
	private Executor getExecutor(){
		if(executor==null){
			executor = new ExecutorService();
		}
		return executor;
	}
	
	private String getDisplay(){
		return FrameHelper.getDisplayArea().getText();
	}
	
	private void displayResulst(Compute compute){
		FrameHelper.getDisplayArea().setText(compute.toString());
	}
	
	private void handle(JButton button){
		String mark = button.getText();
		String[] displayParts = getDisplay().split("\n");
		switch(mark){
		case "CE":
			if(displayParts.length>=2){
				FrameHelper.getDisplayArea().setText(displayParts[0]+"\n");
			}
			break;
		case "C":
			FrameHelper.getDisplayArea().setText("\n0");
			break;
		case "←":
			if(displayParts.length>=2){
				String secondLine = displayParts[displayParts.length-1];
				FrameHelper.getDisplayArea().setText(displayParts[0]+"\n"+secondLine.substring(0, secondLine.length()-1));
			}
			break;
		case "=":
			Compute compute = new Compute();
			compute.setExpression(getDisplay());
			getExecutor().execute(compute);
			displayResulst(compute);
			if(CheckMark.checkString(compute.getResult())&&!Constant.ERROR.equals(compute.getResult())){
				Constant.history.add(compute);
				Constant.lastResult = compute.getResult();
			}
			else{
				Constant.lastResult = null;
			}
			Constant.newStart = false;
			return;
		default:break;
		}
		alterDisplay(mark);
		if(!Constant.newStart){
			Constant.newStart = true;
		}
	}
	
	private void alterDisplay(String mark){
		String display;
		if(CheckMark.isDigit(mark)||".".equals(mark)||"π".equals(mark)){
			if(!Constant.newStart){
				FrameHelper.getDisplayArea().setText("\n0");
			}
			display = getDisplay();
			if("0".equals(display.trim())&&!".".equals(mark)){
				display = "\n";
			}
			FrameHelper.getDisplayArea().setText(display+mark);
		}
		if(CheckMark.isOperator(mark)){
			if(!Constant.newStart){
				if(CheckMark.checkString(Constant.lastResult)){
					FrameHelper.getDisplayArea().setText(Constant.lastResult);
				}
			}
			display = getDisplay();
			if("0".equals(display.trim())){
				if(CheckMark.isPreMonocularOperator(mark)){
					display = "";
				}
			}
			switch(mark){
			case "x^y":
				mark = "^";
				break;
			case "x^2":
				mark = "^2";
				break;
			case "e^x":
				mark = "e^";
				break;
			case "10^x":
				mark = "10^";
				break;
			case "±":
				String[] displayParts = display.split("\n");
				if(displayParts.length>=2){
					try{
						float tempFloat = Float.parseFloat(displayParts[displayParts.length-1]);
						if(tempFloat<0){
							tempFloat = Math.abs(tempFloat);
						}
						else{
							tempFloat*=-1;
						}
						display = displayParts[0]+"\n"+tempFloat;
						FrameHelper.getDisplayArea().setText(display);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				return;
			default:break;
			}
			display = display.replaceAll("\\s", "");
			FrameHelper.getDisplayArea().setText(display+mark+"\n");
		}
	}
	
}
