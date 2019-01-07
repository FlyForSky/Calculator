package com.zlh.main;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zlh.bean.Compute;
import com.zlh.util.CheckMark;
import com.zlh.util.Constant;
import com.zlh.util.execute.Executor;

public class ExecutorService implements Executor {

	private Pattern basePattern;
	
	private Pattern wholePattern;
	
	public ExecutorService(){
		super();
		init();
	}
	
	private void init(){
		String baseValue = "([\\+×/%]?)((sin)|(cos)|(tan)|(log)|(ln)|[√-])?((\\d+(\\.\\d+)?)|π|e)((\\^)((\\d+(\\.\\d+)?)|π))?";
		String wholeValue = "("+baseValue+")+";
		basePattern = Pattern.compile(baseValue);
		wholePattern = Pattern.compile(wholeValue);
	}
	
	private boolean checkCompute(Compute compute){
		if(!CheckMark.checkString(compute.getExpression())||"0".equals(compute.getExpression())){
			return false;
		}
		if(!wholePattern.matcher(compute.getExpression()).matches()){
			compute.setResult(Constant.ERROR);
			return false;
		}
		return true;
	}
	
	@Override
	public void execute(Compute compute) {
		if(!checkCompute(compute)){
			return;
		}
		String expression = compute.getExpression();
		Matcher matcher = basePattern.matcher(expression);
		int count = 1;
		Double result = 0.0;
		while(matcher.find()){
			String operator = matcher.group(1);
			String prefix = matcher.group(2);
			if(!CheckMark.checkString(operator)&&"-".equals(prefix)){
				operator = "+";
			}
			if(CheckMark.checkString(operator)){
				if(count==1&&!"+".equals(operator)){
					compute.setResult(Constant.ERROR);
					return;
				}
			}
			else{
				if(count!=1){
					compute.setResult(Constant.ERROR);
					return;
				}
			}
			Double singleValue = computeSingle(prefix,matcher.group(8),matcher.group(12),matcher.group(13));
			if(count==1){
				result = singleValue;
			}
			else{
				result = compute(operator,result,singleValue);
			}
			count++;
		}
		DecimalFormat df=(DecimalFormat)NumberFormat.getNumberInstance();
		df.setMaximumFractionDigits(18);
		compute.setResult(String.valueOf(df.format(result)));
	}
	
	private Double compute(String operator,Double firstNum,Double secondNum){
		Double result = 0.0;
		switch(operator){
		case "%":
			result = firstNum % secondNum;
			break;
		case "/":
			result = firstNum / secondNum;
			break;
		case "×":
			result = firstNum * secondNum;
			break;
		case "-":
			result = firstNum - secondNum;
			break;
		case "+":
			result = firstNum + secondNum;
			break;
		default:break;
		}
		return result;
	}
	
	private Double computeSingle(String prefix,String number,String suffixOperator,String suffixNumber){
		Double singleResult;
		if("π".equals(number)){
			singleResult = Math.PI;
		}
		else if("e".equals(number)){
			singleResult = Math.E;
		}
		else{
			singleResult = Double.parseDouble(number);
		}
		if(CheckMark.checkString(prefix)){
			switch(prefix){
			case "√":
				singleResult = Math.sqrt(singleResult);
				break;
			case "-":
				singleResult *= -1;
				break;
			case "sin":
				singleResult = Math.sin(singleResult);
				break;
			case "cos":
				singleResult = Math.cos(singleResult);
				break;
			case "tan":
				singleResult = Math.tan(singleResult);
				break;
			case "log":
				singleResult = Math.log(singleResult);
				break;
			case "ln":
				singleResult = Math.log10(singleResult);
				break;
			default:break;
			}
		}
		if(CheckMark.checkString(suffixOperator)){
			switch(suffixOperator){
			case "^":
				Double suffixValue;
				if("π".equals(suffixNumber)){
					suffixValue = Math.PI;
				}
				else{
					suffixValue = Double.parseDouble(suffixNumber);
				}
				singleResult = Math.pow(singleResult, suffixValue);
				break;
			default:break;
			}
		}
		return singleResult;
	}
	
}
