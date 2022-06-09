package jp.excd.common;

import java.text.DecimalFormat;

public class CommonUtils {
	
	public String dateformat(String a) {
		if(a==null) {
			String date="";
			return date;
		}else {
			String date = a.substring(0,4)+"年"+a.substring(4,6)+"月"+a.substring(6)+"日";
			return date;
		}
	}
	
	public String valueformat(long a) {
		DecimalFormat df = new DecimalFormat("###,###");
		String value = df.format(a);
		return value;
	}
}