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
		
		if(a==null) {
			String date="";
			return date;
		}else {
			String date = a.substring(0,4)+"年"+a.substring(4,6)+"月"+a.substring(6)+"日";
			return date;
		}
	}
	
	/**
	 * 日付のフォーマット化
	 * @param 日付文字列（yyyymmdd）
	 * @return フォーマット後日付（yyyy/mm/dd）
	 * */
	public static String profile(String a) {
		
		if(a==null) {
			String date="";
			return date;
		}else {
			String date = a.substring(0,4)+"/"+a.substring(4,6)+"/"+a.substring(6);
			return date;
		}
	}
	
	/**
	 * 日付のフォーマット化
	 * @param 日付文字列（yyyymmdd）
	 * @return フォーマット後日付（yyyy年mm月）
	 * */
	public static String infomation(String a) {
		
		if(a==null) {
			String date="";
			return date;
		}else {
			String date = a.substring(0,4)+"年"+a.substring(4,6)+"月";
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
	
	/**
	 * エポック秒の変換
	 * @param エポック秒
	 * @return 変換後の秒
	 */
	public static String epoch(Double a) {

		String resultVal;
		double d_releaseDay = 0;

		/**
		 * 現在のエポック秒を取得
		 */
		Date date = new Date();
		Double nowEpoch = (double) date.getTime();

		/**
		 * 差分を算出
		 */
		Double diff = nowEpoch - a* 1000;

		/**
		 * 小数点以下を切り捨てる処理
		 */
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(0);

		/**
		 * 公開時間を取得
		 */
		/**
		 * 1秒未満
		 */
		if (diff < 1000) {
			resultVal = "たった今";

		}
		/**
		 * 1秒以上かつ2秒未満
		 */
		else if (diff < 2000) {
			resultVal = "1秒前";

		}
		/**
		 * 2秒以上かつ60秒未満
		 */
		else if (diff < 60000) {
			resultVal = diff + "秒前";

		}
		/**
		 * 1分以上かつ2分未満
		 */
		else if (diff < 120000) {
			resultVal = "1分前";

		}
		/**
		 * 2分以上かつ60分未満
		 */
		else if (diff < 3600000) {
			d_releaseDay = (diff / 60000);
			resultVal = numberFormat.format(d_releaseDay) + "分前";

		}
		/**
		 * 1時間以上かつ2時間未満
		 */
		else if (diff < 7200000) {
			resultVal = "1時間前";

		}
		/**
		 * 2時間以上かつ24時間未満
		 */
		else if (diff < 86400000) {
			d_releaseDay = (diff / 3600000);
			resultVal = numberFormat.format(d_releaseDay) + "時間前";

		}
		/**
		 * 1日以上かつ2日未満
		 */
		else if (diff < 172800000) {
			resultVal = "1日前";

		}
		/**
		 * 2日以上かつ7日未満
		 */
		else if (diff < 604800000) {
			d_releaseDay = (diff / 86400000);
			resultVal = numberFormat.format(d_releaseDay) + "日前";

		}
		/**
		 * 7日以上かつ14日未満
		 */
		else if (diff < 1209600000) {
			resultVal = "1週間前";

		}
		/**
		 * 14日以上かつ30日未満
		 */
		else if (diff < 2592000000L) {
			d_releaseDay = (diff / 604800000);
			resultVal = numberFormat.format(d_releaseDay) + "週間前";

		}
		/**
		 * 30日以上かつ60日未満
		 */
		else if (diff < 5184000000L) {
			resultVal = "1ヶ月前";

		}
		/**
		 * 60日以上かつ365日未満
		 */
		else if (diff < 31536000000L) {
			d_releaseDay = (diff / 2592000000L);
			resultVal = numberFormat.format(d_releaseDay) + "ヶ月前";

		}
		/**
		 * 1年以上かつ2年未満
		 */
		else if (diff < 63072000000L) {
			resultVal = "1年前";

		}
		/**
		 * 2年以上
		 */
		else {
			d_releaseDay = (diff / 31536000000L);
			resultVal = numberFormat.format(d_releaseDay) + "年前";

		}
		return resultVal;
	}

	//画像サイズの調整
	public static double imageHeightformat(int width , int height) {
		double formatHeight = height; //ダブル型に変換
		double width1 =width; //ダブル型に変換
		if(width != 275) { //横幅が275px固定なので、もし違う場合は拡大・縮小しなければならない
			double widthRate= 275/width1; //この計算で高さを何倍させれば良いか出す
			formatHeight = (widthRate*formatHeight); //調整後の高さ
		}
		
		return formatHeight;
		
	}

	public static double cutLength(double d1) {
		double imageLength = 0;
		if(d1 != 160) { //高さを160にしなければならない
			imageLength = (d1-160)/2; //高さから160を引き、それを2で割ると、画像をどれだけカットすれば良いか分かる
		}
		return imageLength;
	}
}
