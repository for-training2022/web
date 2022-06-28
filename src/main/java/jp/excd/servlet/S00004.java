package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.excd.bean.ComposerBean;
import jp.excd.bean.ComposerRecord;
import jp.excd.bean.SongBean;
import jp.excd.common.CommonUtils;

public class S00004 extends HttpServlet  {
	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		Connection con = null; //コネクション
		PreparedStatement pstmt = null; //プリペアドステートメント
		ResultSet rs = null; //resultSet
		request.setCharacterEncoding("UTF-8");

		String dbName = "meloko";
		String userName = "meloko";
		String password = "exceed";
		String timeZone = "Asia/Tokyo";

		try {
			con = MySQLSetting.getConnection(dbName, userName, password, timeZone); //DB接続(コネクションの確立)

			this.composerResult(request, response, con, pstmt, rs); //内部メソッド呼び出し
			
			
		} catch (Exception e) {
			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);

		} finally {
			try {
				if (con != null) {
					con.close(); //コネクション切断
				}
				if (pstmt != null) {
					pstmt.close(); //pstmt切断
				}
				if(rs != null) {
					rs.close(); //rs切断
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);
				return;
			}
		}
	}

	private void composerResult(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection con,
			PreparedStatement pstmt,
			ResultSet rs)throws IOException, ServletException {
		
		String sql = null; //SQL宣言
		
		ComposerRecord cr = new ComposerRecord(); //取得したデータを保持するオブジェクト
		ComposerBean composerBean = new ComposerBean(); //作曲家の出力用の情報を纏めるオブジェクト
		ArrayList<SongBean> songList =new ArrayList<SongBean>(); //曲リスト

		String uriGet = request.getRequestURI(); //URI取得
		String[] uriParts = uriGet.split("/"); //「/」で区切って配列へ
		
		try {
			//作曲家テーブルのデータ取得
			sql = "select"
					+ "    composer.id"
					+ "    , composer.unique_code"
					+ "    , composer.nickname"
					+ "    , composer.joined_date"
					+ "    , composer.gender"
					+ "    , composer.birthday"
					+ "    , composer.message"
					+ "    , composer.fb_link"
					+ "    , composer.tw_link"
					+ "    , composer.other_link_url"
					+ "    , composer.other_link_decription "
					+ "from"
					+ "    meloko.composer "
					+ "where "
					+ "    composer.unique_code = ?";
			
			pstmt = con.prepareStatement(sql); //pstmを使えるようにする
			String uri = uriParts[4]; //配列の４番目にユニークコードが格納されている
			pstmt.setString(1, uri ); //?に取得したURIを設定
			rs = pstmt.executeQuery(); //SQLの実行結果
			
			boolean urlCheck = false; //URLチェック用
			
			while (rs.next()) { //取得した作曲家情報をcrに纏める
				cr.setComposerId(rs.getLong("id")); //作曲家のIDをcrへ
				cr.setUniqueCode(rs.getString("unique_code")); //ユニークコードをcrへ
				cr.setNickname(rs.getString("nickname")); //ニックネームをcrへ
				cr.setJoined_date(rs.getString("joined_date")); //登録日をcrへ
				cr.setGender(rs.getString("gender")); //性別をcrへ
				cr.setBirthday(rs.getString("birthday")); //誕生日をcrへ
				cr.setMessage(rs.getString("message")); //メッセージをcrへ
				cr.setFbLink(rs.getString("fb_link")); //FaceBookリンクをcrへ
				cr.setTwLink(rs.getString("tw_link")); //Twitterリンクをcrへ
				cr.setOtherLinkUrl(rs.getString("other_link_url")); //関連リンクURLをcrへ
				cr.setOtherLinkDecription(rs.getString("other_link_decription")); //関連リンク文字列をcrへ
				urlCheck = true;
			}
			
			if(!urlCheck) { //該当する作曲家がいなければ404.jspへフォワード
				getServletConfig().getServletContext().getRequestDispatcher("/ja/404.jsp").forward(request, response);
				return;
			}
			
			String birthdayFormated = null;
			birthdayFormated = CommonUtils.profile(cr.getBirthday()); //誕生日yyyy/mm/dd化
			
			String joinedDateFormated = CommonUtils.dateformat(cr.getJoined_date()); //登録日yyyy年mm月dd日化
			
			//性別判定
			String gender = cr.getGender();
			String genderResult = null;
			if("1".equals(gender) ){
				genderResult = "男";
			}else if("2".equals(gender)) {
				genderResult = "女";
			}else {
				genderResult = "";
			}
			
			long composerId = cr.getComposerId(); //曲検索用に変数に入れておく
			
			//曲情報取得
			sql = "select "
					+ "    sum(rating.rating) as rating_total "
					+ "    , avg(rating.rating) as rating_average "
					+ "    , song.id "
					+ "    , song.title "
					+ "    , song.total_listen_count "
					+ "    , song.release_datetime "
					+ "    , song.image_file_name "
					+ "    , song.image_file_height "
					+ "    , song.image_file_width "
					+ "from "
					+ "    meloko.song "
					+ "    left join meloko.rating "
					+ "        on song.id = rating.song_id "
					+ "where "
					+ "    song.composer_id = ? "
					+ "group by "
					+ "    song.id "
					+ "order by "
					+ "    song.release_datetime desc ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, composerId); //?に作曲家IDを設定
			rs = pstmt.executeQuery();
			
			//曲リスト(songList)にデータを入れる
			//★出力する★
			while(rs.next()) {
				SongBean sb = new SongBean();
				sb.setTitle(rs.getString("title")); //タイトルをsbへ
				sb.setImage_file_name(rs.getString("image_file_name")); //画像名をsbへ
				sb.setImageFileHeight(rs.getInt("image_file_height")); //画像高さをsbへ
				sb.setImageFileWidth(rs.getInt("image_file_width")); //画像高さをsbへ
				
				int height = rs.getInt("image_file_height"); //画像高さ
				int width = rs.getInt("image_file_width"); //画像幅
				sb.setFormatHeight(CommonUtils.imageHeightformat(width , height)); //サイズ調整後の画像高さ
				sb.setCutLength(CommonUtils.cutLength(sb.getFormatHeight())); //画像をどれだけカットするか
				
				sb.setSong_id(rs.getString("id")); //曲ID
				
				String ratingTotalFormated =CommonUtils.valueformat(rs.getLong("rating_total")); //総感動指数フォーマット
				sb.setRating_total_formated(ratingTotalFormated); //総感動指数(フォーマット済み)をsbへ
				
				String ratingAverageFormated =CommonUtils.averageformat(rs.getDouble("rating_average")); //感動指数平均フォーマット
				sb.setRating_average_formated(ratingAverageFormated); //感動指数平均(フォーマット済み)をsbへ
				
				String totalListenCountFormated = CommonUtils.valueformat(rs.getLong("total_listen_count")); //再生回数フォーマット
				sb.setTotal_listen_count_formated(totalListenCountFormated); //再生回数(フォーマット済み)をsbへ
				
				String releaseDatetimeFormated = CommonUtils.epoch(rs.getDouble("release_datetime")); //公開日フォーマット
				sb.setRelease_datetime_formated(releaseDatetimeFormated); //公開日(フォーマット済み)をsbへ
				
				songList.add(sb); //曲リストへ
			}
			
			int songTotal = songList.size(); //作品数計算
			
			//作曲家の感動指数計算
			sql = "select\n"
					+ "    sum(rating.rating) as total_rating\n"
					+ "    , avg(rating.rating) as average_rating \n"
					+ "from\n"
					+ "    meloko.rating \n"
					+ "    left join meloko.song \n"
					+ "        on rating.song_id = song.id \n"
					+ "    left join meloko.composer \n"
					+ "        on song.composer_id = composer.id \n"
					+ "where\n"
					+ "    composer.id = ?;";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, cr.getComposerId()); //?に作曲家IDを設定
			rs = pstmt.executeQuery(); //SQLの実行結果
			
			
			String totalRatingFormated = null;
			String averageRatingFormated = null;
			
			while(rs.next()) {
				totalRatingFormated = CommonUtils.valueformat(rs.getLong("total_rating")); //総感動指数合計(フォーマットも)
				averageRatingFormated = CommonUtils.averageformat(rs.getDouble("average_rating")); //総感動指数平均(フォーマットも)
			}
			
			//総再生回数の計算
			sql = "select\n"
					+ "    sum(song.total_listen_count) as total_listen \n"
					+ "from\n"
					+ "    meloko.song \n"
					+ "where\n"
					+ "    song.composer_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, cr.getComposerId()); //?に作曲家IDを設定
			rs = pstmt.executeQuery(); //SQLの実行結果
			
			String totalListenFormated = null;
			
			while(rs.next()) {
				totalListenFormated = CommonUtils.valueformat(rs.getLong("total_listen")); //総再生回数(フォーマット化も)
			
			}
			
			con.close(); //コネクション切断
			pstmt.close(); //pstmt切断
			rs.close(); //rs切断
			
			//作曲家情報をcomposerBeanに纏める
			//★出力する★
			composerBean.setUniqueCode(cr.getUniqueCode()); //ユニークコード
			composerBean.setNickname(cr.getNickname()); //ニックネーム
			composerBean.setMessage(cr.getMessage()); //メッセージ
			composerBean.setGenderResult(genderResult); //性別
			composerBean.setBirthday_formated(birthdayFormated); //誕生日
			composerBean.setFbLink(cr.getFbLink()); //FaceBookリンク
			composerBean.setTwLink(cr.getTwLink()); //Twitterリンク
			composerBean.setJoined_date_formated(joinedDateFormated); //登録日
			composerBean.setSongTotal(songTotal); //作品数
			composerBean.setTotalRatingFormated(totalRatingFormated); //感動指数合計
			composerBean.setAverageRatingFormated(averageRatingFormated); //総感動指数平均
			composerBean.setTotalListenFormated(totalListenFormated); //総再生回数
			composerBean.setOtherLinkUrl(cr.getOtherLinkUrl()); //関連リンクURL
			composerBean.setOtherLinkDecription(cr.getOtherLinkDecription()); //関連リンク文字列
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("composerBean", composerBean);
		request.setAttribute("songList", songList);
		getServletConfig().getServletContext().
		getRequestDispatcher("/ja/S00004.jsp" ).
		forward( request, response );
		
	}
}
