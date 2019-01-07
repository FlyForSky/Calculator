package com.zlh.bean;

import com.zlh.util.CheckMark;

public class Compute {
	
	private String expression;
	
	private String result;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		expression = expression.replaceAll("\\s|,", "");
		this.expression = expression;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		String display = "\n0";
		if(CheckMark.checkString(expression)&&CheckMark.checkString(result)){
			display = expression+"\n="+result;
		}
		return display;
	}
	
	public String print(){
		return toString().replaceAll("\\s|,", "")+"\n";
	}
	
}
