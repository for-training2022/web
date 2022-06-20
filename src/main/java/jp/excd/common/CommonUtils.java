package jp.excd.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class CommonUtils {

	/**
	 * 日付のフォーマット化
	 * @param 日付文字列（yyyymmdd）
	 * @return フォーマット後日付（yyyy年mm月dd日）
	 * */
	public static String dateformat(String a) {

		if(a.equals(null)) {
			String date="";
			return date;
		}else {
			String date = a.substring(0,4)+"年"+a.substring(4,6)+"月"+a.substring(6)+"日";
			return date;
		}
	}

	/**
	 * 3桁区切りのフォーマット化
	 * @param 数字 (#####)
	 * @return フォーマット後数字 (##,###)
	 */
	public static String valueformat(Long a) {

		DecimalFormat df = new DecimalFormat("###,###");
		String value = df.format(a);
		return value;
	}

	/**
	 * 平均のフォーマット化
	 * @param 数字 (#.####)
	 * @return フォーマット後数字 (#.#)
	 */
	public static String averageformat(Double a) {

		DecimalFormat df = new DecimalFormat("#.#");
		String average = df.format(a);
		return average;
	}

	//エポック秒の変換
	public static String epoch(Double a) {

		String resultVal;
		double d_releaseDay = 0;

		//現在のエポック秒を取得
		Date date = new Date();
		Double nowEpoch = (double) date.getTime();

		//差分を算出
		Double diff = nowEpoch - a* 1000;

		//小数点以下を切り捨てる処理
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(0);

		//公開時間を取得
		//1秒未満
		if (diff < 1000) {
			resultVal = "たった今";

		}
		//1秒以上かつ2秒未満
		else if (diff < 2000) {
			resultVal = "1秒前";

		}
		//2秒以上かつ60秒未満
		else if (diff < 60000) {
			resultVal = diff + "秒前";

		}
		//1分以上かつ2分未満
		else if (diff < 120000) {
			resultVal = "1分前";

		}
		//2分以上かつ60分未満
		else if (diff < 3600000) {
			d_releaseDay = (diff / 60000);
			resultVal = numberFormat.format(d_releaseDay) + "分前";

		}
		//1時間以上かつ2時間未満
		else if (diff < 7200000) {
			resultVal = "1時間前";

		}
		//2時間以上かつ24時間未満
		else if (diff < 86400000) {
			d_releaseDay = (diff / 3600000);
			resultVal = numberFormat.format(d_releaseDay) + "時間前";

		}
		//1日以上かつ2日未満
		else if (diff < 172800000) {
			resultVal = "1日前";

		}
		//2日以上かつ7日未満
		else if (diff < 604800000) {
			d_releaseDay = (diff / 86400000);
			resultVal = numberFormat.format(d_releaseDay) + "日前";

		}
		//7日以上かつ14日未満
		else if (diff < 1209600000) {
			resultVal = "1週間前";

		}
		//14日以上かつ30日未満
		else if (diff < 2592000000L) {
			d_releaseDay = (diff / 604800000);
			resultVal = numberFormat.format(d_releaseDay) + "週間前";

		}
		//30日以上かつ60日未満
		else if (diff < 5184000000L) {
			resultVal = "1ヶ月前";

		}
		//60日以上かつ365日未満
		else if (diff < 31536000000L) {
			d_releaseDay = (diff / 2592000000L);
			resultVal = numberFormat.format(d_releaseDay) + "ヶ月前";

		}
		//1年以上かつ2年未満
		else if (diff < 63072000000L) {
			resultVal = "1年前";

		}
		//2年以上
		else {
			d_releaseDay = (diff / 31536000000L);
			resultVal = numberFormat.format(d_releaseDay) + "年前";

		}
		return resultVal;
	}

	/**
	 * 評価のString型変換
	 * @param byte型
	 * @return String型 
	 * 	 */
	
	public static String ratingformat(byte a) {
		return String.valueOf(a);
	}

	/**
	 * IDのString型変換
	 * @param long型
	 * @return String型 
	 * 	 */
	
	public static String idformat(long a2) {
		return String.valueOf(a2);
	}

	/**
	 * キーの日本語表記
	 * @param 数字（テーブル設計書最後のsheetにあるキー変換）
	 * @return 変換後(String型の日本語表記キー)
	 */
	public static String keyName(String a2) {
		if("1".equals(a2)) {
			a2 = "Cメジャー";
		}else if("2".equals(a2)) {
			a2 = "Cシャープメジャー";
		}else if("3".equals(a2)) {
			a2 = "Dフラットメジャー";
		}else if("4".equals(a2)) {
			a2 = "Dメジャー";
		}else if("5".equals(a2)) {
			a2 = "Dシャープメジャー";
		}else if("6".equals(a2)) {
			a2 = "Eフラットメジャー";
		}else if("7".equals(a2)) {
			a2 = "Eメジャー";
		}else if("8".equals(a2)) {
			a2 = "Fメジャー";
		}else if("9".equals(a2)) {
			a2 = "Fシャープメジャー";
		}else if("10".equals(a2)) {
			a2 = "Gフラットメジャー";
		}else if("11".equals(a2)) {
			a2 = "Gメジャー";
		}else if("12".equals(a2)) {
			a2 = "Gシャープメジャー";
		}else if("13".equals(a2)) {
			a2 = "Aフラットメジャー";
		}else if("14".equals(a2)) {
			a2 = "Aメジャー";
		}else if("15".equals(a2)) {
			a2 = "Aシャープメジャー";
		}else if("16".equals(a2)) {
			a2 = "Bフラットメジャー";
		}else if("17".equals(a2)) {
			a2 = "Bメジャー";
		}else if("18".equals(a2)) {
			a2 = "Cマイナー";
		}else if("19".equals(a2)) {
			a2 = "Cシャープマイナー";
		}else if("20".equals(a2)) {
			a2 = "Dフラットマイナー";
		}else if("21".equals(a2)) {
			a2 = "Dマイナー";
		}else if("22".equals(a2)) {
			a2 = "Dシャープマイナー";
		}else if("23".equals(a2)) {
			a2 = "Eフラットマイナー";
		}else if("24".equals(a2)) {
			a2 = "Eマイナー";
		}else if("25".equals(a2)) {
			a2 = "Fマイナー";
		}else if("26".equals(a2)) {
			a2 = "Fシャープマイナー";
		}else if("27".equals(a2)) {
			a2 = "Gフラットマイナー";
		}else if("28".equals(a2)) {
			a2 = "Gマイナー";
		}else if("29".equals(a2)) {
			a2 = "Gシャープマイナー";
		}else if("30".equals(a2)) {
			a2 = "Aフラットマイナー";
		}else if("31".equals(a2)) {
			a2 = "Aマイナー";
		}else if("32".equals(a2)) {
			a2 = "Aシャープマイナー";
		}else if("33".equals(a2)) {
			a2 = "Bフラットマイナー";
		}else if("34".equals(a2)) {
			a2 = "Bマイナー";
		}
		return a2;

	}

	/**
	 * 楽譜表記のフォーマット化
	 * @param String型の0、または1。
	 * @return テーブル設計書内で指定された表記方法
	 */
	
	public static String scoreChange(String a3) {
		
		if("0".equals(a3)) {
			a3 = "楽譜通り";
		}else if("1".equals(a3)) {
			a3 = "1オクターブ上で表記";
		}
		
		return a3;
	}
		

	/**
	 * 評価のフォーマット。ratingによってhmtlのクラスを変更する。
	 * @param 評価数値（rating）
	 * @return 評価に応じたクラス名
	 * */

	public static String starformat(String str5) {
		
		String rating =null;
		
		if ("5" .equals (str5)) {
			rating = "rating star50";
		}else if("4.5" .equals (str5)){
			rating = "rating star45";
		}else if("4" .equals (str5)){
			rating = "rating star40";
		}else if("3.5" .equals (str5)){
			rating = "rating star35";
		}else if("3" .equals (str5)){
			rating = "rating star30";
		}else if("2.5" .equals (str5)){
			rating = "rating star25";
		}else if("2" .equals (str5)){
			rating = "rating star20";
		}else if("1.5" .equals (str5)){
			rating = "rating star15";
		}else if("1" .equals (str5)){
			rating = "rating star10";
		}else if("0.5" .equals (str5)){
			rating = "rating star05";
		}else if("0" .equals (str5)){
			rating = "rating star00";
		}
		
		return rating;
	}
	
	public static double imageHeightformat(int width , int height) {
		double formatHeight = height;
		double width1 =width;
		if(width != 275) {
			double widthRate= 275/width1;
			formatHeight = (widthRate*formatHeight);
		}
		
		return formatHeight;
		
	}
	
	public static double cutLength(double d1) {
		double imageLength = 0;
		if(d1 != 160) {
			imageLength = (d1-160)/2;
		}
		return imageLength;
	}
}
