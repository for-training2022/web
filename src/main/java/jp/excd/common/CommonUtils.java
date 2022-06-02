package jp.excd.common;

public class CommonUtils {
	
	public String dateformat(String a) {
		String date = a.substring(0,4)+"年"+a.substring(4,6)+"月"+a.substring(6)+"日";
		return date;
	}

}
