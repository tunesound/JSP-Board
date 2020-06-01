package mall.util;
import java.util.*;
import java.text.*;
/**
 * ���� ���� Ŭ���� 
 */	
public class Money {	
	/** ���� �������� ��ȯ - �� 123456 --> 123,456
	 * @param ����	String
	 * @return ���� ���˵� ���ڿ�
	 */	
	public static String format(String price) {		
		double doublePrice;		

		if (price.length() == 0) {
			return "0";
		}		
		doublePrice = Double.parseDouble(price);		
		return format(doublePrice);					
	}
	
	/** ���� �������� ��ȯ - �� 123456 --> 123,456
	 * @param ����	double
	 * @return ���� ���˵� ���ڿ�
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

		