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

	public static String commentidformat(long a) {
		return String.valueOf(a);
	}

	public static String ratingformat(byte a) {
		return String.valueOf(a);

	}

	public static String idformat(long a2) {
		return String.valueOf(a2);
	}
}
