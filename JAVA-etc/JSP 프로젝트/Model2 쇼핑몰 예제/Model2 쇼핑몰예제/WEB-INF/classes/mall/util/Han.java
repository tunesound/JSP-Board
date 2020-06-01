package mall.util;
import java.io.*;

public class Han {

public static String toKor(Object obj) {
if (obj != null) {
try {
	return new String(((String)obj).getBytes("8859_1"), "KSC5601");
} catch (java.io.UnsupportedEncodingException ex) {
	System.err.println(ex);
}
}
return "";
}

public static String toKor2(Object obj) {
	if (obj != null) {
	try {
	return new String(((String)obj).getBytes("8859_1"), "EUC-KR");
	} catch (java.io.UnsupportedEncodingException ex) {
		System.err.println(ex);
	}
	}
	return "";
}
	

public static String toEng(Object obj) {
	if (obj != null) {
	try {
	return new String(((String)obj).getBytes("KSC5601"), "8859_1");
	} catch (java.io.UnsupportedEncodingException ex) {
		System.err.println(ex);
	}
	}
	return "";  
}

public static String Uni2Ksc(String str) throws UnsupportedEncodingException {
      if(str==null) return null;
      return new String(str.getBytes("8859_1"),"KSC5601");
  }  

 public static  String Ksc2Uni(String str) throws UnsupportedEncodingException {
      if(str==null) return null;
      return new String(str.getBytes("KSC5601"),"8859_1");
  }    
}
