package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.excd.common.CommonUtils;

public class S00001 extends HttpServlet {

	public void doGet(	HttpServletRequest request,			
			HttpServletResponse response)		
					throws IOException, ServletException {	

		Connection con = null;
		//文字コード指定
		request.setCharacterEncoding("UTF-8");

		String dbName = "meloko";
		String userName = "meloko";
		String password = "exceed";
		String timeZone = "Asia/Tokyo";

		try {
			// (1)DB接続（コネクションの確立）
			con = MySQLSetting.getConnection(dbName, userName, password, timeZone);

			// (2)内部メソッド呼び出し
			this.mainProcessForToppage(request, response, con);

		} catch (Exception e) {
			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);

		} finally {
			try {
				if (con != null) {

					// (3)接続したコネクションの切断を行う。
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);
			}

		}
	}

	private void mainProcessForToppage(HttpServletRequest request, HttpServletResponse response, Connection con)
			throws IOException, Exception {

		//接続URL受け取り
		String URL = request.getRequestURI();

		// (1) 接続URLが「/ja/S00001」以外の場合は、404.jspへフォワーディングする。
		if ("/web/ja/S00001".equals(URL)) {
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}

		//文字コード指定
		request.setCharacterEncoding("UTF-8");

		//(2)呼出しパラメータを取得する
		String category = request.getParameter("category"); //カテゴリコード
		
		if ("1".equals(category)) {
			category = "1";
		} else if ("2".equals(category)) {
			category = "2";
		} else if ("3".equals(category)) {
			category = "3";
		} else if ("4".equals(category)) {
			category = "4";
		} else {
			//( !"1".equals(category) && !"2".equals(category) && !"3".equals(category) && !"4".equals(category)) {
			category = "1";
		} 
		
		String from = request.getParameter("from"); //データ取得開始位置
		
		if ( from == null ) {
			from = "1";
		}
		
		int fr = 0;
		if (from != null && !from.isEmpty()) {
			fr = Integer.parseInt(from);
		}

		//(3)初期表示（データ）用のデータ取得を行う
		//(4)画面に出力するListを作成する。
		List<SongComposerBean> results = null;
		results = executeQuery(request, response, con, category, fr);


		//(5)アプリリンクの文言の取得を行う。
		String message = this.getDescription(con, "DS00001_001");

		//(6)告知データの取得を行う
		String message2 = this.getNotice(con, "1");

		//(7)Requestオブジェクトに値を設定（setAttribute）する
		request.setAttribute("displayList", results);
		request.setAttribute("appMessage", message);
		request.setAttribute("notice", message2);
		
		int nextFrom = fr + 5;
		request.setAttribute("nextFrom", nextFrom);
		
		request.setAttribute("category", category);

		//(8)S00001.jspにフォワーディングする。
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00001.jsp").forward(request, response);
	}

	//アプリリンクの文言の取得を行う。
	private String getDescription(Connection con, String description_id)
			throws Exception {
		String ret = "";
		String sql = "select description from mst_description where description_id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, description_id);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			ret = rs.getString("description");
		}

		rs.close();
		pstmt.close();
		return ret;

	}

	//告知データの取得を行う。
	private String getNotice(Connection con, String language_type)
			throws Exception {
		String ret = "";
		String sql = "select notice from top_notice where language_type =?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, language_type);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			ret = rs.getString("notice");
		}

		rs.close();
		pstmt.close();
		return ret;

	}

	private List<SongComposerBean> executeQuery(HttpServletRequest request, HttpServletResponse response, Connection con,
			String category, int fr) throws Exception {

		////(1)SQLの断片を用意する。

		//SQL
		String sql = null;

		sql = "select\n"
				+ "    song.id\n"
				+ "    , title\n"
				+ "    , unique_code\n"
				+ "    , nickname\n"
				+ "    , sum(rating.rating) as rating_total\n"
				+ "    , avg(rating.rating) as rating_average\n"
				+ "    , total_listen_count\n"
				+ "    , release_datetime\n"
				+ "    , image_file_name \n"
				+ "from\n"
				+ "    song \n"
				+ "    left join composer \n"
				+ "        on composer.id = song.composer_id \n"
				+ "    left join rating \n"
				+ "        on rating.song_id = song.id \n"
				+ "where\n"
				+ "    release_datetime >= ?\n"
				+ "group by\n"
				+ "    song.id\n";

		//エポック秒
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
		Double diff1 = (nowEpoch - 5184000000L)/1000;//カテゴリ1-3
		Double diff2 = (nowEpoch - 63072000000L)/1000;//カテゴリ4

		/**
		 * 小数点以下を切り捨てる処理
		 */
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(0);


		//カテゴリごとにプリペアドステートメント
		PreparedStatement pstmt = null;

		//リストの宣言
		List<SongComposerBean> displayList = new ArrayList<SongComposerBean>();
		
		//(2)並び順の値に従って、ORDER BY句を連結する。

		//category = 1（新着）のとき
		if ( "1".equals(category) ) {
			sql = sql + "ORDER BY release_datetime desc";
			//category = 2（人気）のとき
		} else if ("2".equals(category)) {
			sql = sql + "ORDER BY rating_total desc";
			//category = 3（高評価）のとき
		} else if ("3".equals(category)) {
			sql = sql + "ORDER BY rating_average desc";
			//category = 4（名作）のとき
		} else if ("4".equals(category)) {
			sql = sql + "ORDER BY rating_total desc";
			//それ以外
		} else {
			sql = sql + "ORDER BY relrase_datetime desc";
		}

		//(3)先頭の100件を表示対象とする。LIMIT句を連結する。
		sql = sql + " LIMIT 100;";
		

		try {
			pstmt = con.prepareStatement(sql);// SQL解析

			//category = 1（新着）のとき
			if ( "1".equals(category) ) {
				pstmt.setDouble(1, diff1);
				//category = 2（人気）のとき
			} else if ("2".equals(category) ) {
				pstmt.setDouble(1, diff1);
				//category = 3（高評価）のとき
			} else if ("3".equals(category)) {
				pstmt.setDouble(1, diff1);
				//category = 4（名作）のとき
			} else if ("4".equals(category)) {
				pstmt.setDouble(1, diff2);
				//それ以外
			} else {
				pstmt.setDouble(1,  diff1);
			}

			

			//(6)executeQueryを実行し、結果の「ResultSet」を得る。
			ResultSet rs = pstmt.executeQuery();

			int counter = 1;
			int to = fr+4;


			
			while (rs.next()) {

				if ( fr <= counter && counter <= to ) {
					SongComposerBean record = new SongComposerBean();
					//ID
					String id = rs.getString("id");
					record.setId(id);
					//曲名
					String title = rs.getString("title");
					record.setTitle(title);
					//ユニークコード
					String unique_code = rs.getString("unique_code");
					record.setUnique_code(unique_code);
					//ニックネーム
					String nickname = rs.getString("nickname");
					record.setNickname(nickname);
					//総評価数
					String rating_total_formated = CommonUtils.valueformat(rs.getLong("rating_total"));
					record.setRating_total_formated(rating_total_formated);
					//平均評価数
					String rating_average_formated = CommonUtils.averageformat(rs.getDouble("rating_average"));
					record.setRating_average_formated(rating_average_formated);
					//再生回数
					String total_listen_count_formated = CommonUtils.valueformat(rs.getLong("total_listen_count"));
					record.setTotal_listen_count_formated(total_listen_count_formated);
					//公開日
					String release_datetime_formated= CommonUtils.epoch(rs.getDouble("release_datetime"));
					record.setRelease_datetime_formated(release_datetime_formated);
					//ファイルネーム
					String Image_file_name = rs.getString("Image_file_name");
					record.setImage_file_name(Image_file_name);
					
					displayList.add(record);
				}

				counter++;
			}
			
			request.setAttribute("hits", counter);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		//(7)ResultSetのインスタンス、PreparedStatementのインスタンスをクローズする。
		pstmt.close();

		//(8)前処理で生成したListを呼び出し元に返却する。
		return displayList;

	}

}

