package com.zlh.util;

public class CheckMark {
	
	public static boolean isDigit(String mark){
		for(int index=0;index<mark.length();index++){
			if(!Character.isDigit(mark.charAt(index))){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isOther(String mark){
		if("CE".equals(mark)||"C".equals(mark)||"←".equals(mark)){
			return true;
		}
		return false;
	}
	
	public static boolean isOperator(String mark){
		if("%".equals(mark)||"√".equals(mark)||"x^y".equals(mark)
				||"x^2".equals(mark)||"/".equals(mark)
				||"×".equals(mark)||"-".equals(mark)
				||"+".equals(mark)||"±".equals(mark)
				||"sin".equals(mark)||"cos".equals(mark)
				||"tan".equals(mark)||"log".equals(mark)
				||"ln".equals(mark)||"e^x".equals(mark)
				||"10^x".equals(mark)){
			return true;
		}
		return false;
	}
	
	public static boolean isPreMonocularOperator(String mark){
		if("√".equals(mark)||"±".equals(mark)
				||"sin".equals(mark)||"cos".equals(mark)
				||"tan".equals(mark)||"log".equals(mark)
				||"ln".equals(mark)||"e^x".equals(mark)
				||"10^x".equals(mark)){
			return true;
		}
		return false;
	}
	
	public static boolean checkString(String str){
		if(str==null||"".equals(str)){
			return false;
		}
		return true;
	}
	
}
