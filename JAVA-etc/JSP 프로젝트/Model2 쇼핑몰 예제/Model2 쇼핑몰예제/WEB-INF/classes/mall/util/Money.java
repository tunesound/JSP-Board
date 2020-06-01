package mall.util;
import java.util.*;
import java.text.*;
/**
 * 숫자 포맷 클래스 
 */	
public class Money {	
	/** 숫자 포맷으로 변환 - 예 123456 --> 123,456
	 * @param 숫자	String
	 * @return 숫자 포맷된 문자열
	 */	
	public static String format(String price) {		
		double doublePrice;		

		if (price.length() == 0) {
			return "0";
		}		
		doublePrice = Double.parseDouble(price);		
		return format(doublePrice);					
	}
	
	/** 숫자 포맷으로 변환 - 예 123456 --> 123,456
	 * @param 숫자	double
	 * @return 숫자 포맷된 문자열
	 */
	public static String format(double price) {		
		DecimalFormat df = null;
		String retValue = null;

		df = (DecimalFormat)NumberFormat.getCurrencyInstance(); 
		df.setPositivePrefix("");
		df.setNegativePrefix("-");
		retValue = df.format(price);
		return retValue;		
	}
	
}

		