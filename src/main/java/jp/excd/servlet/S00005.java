package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.excd.bean.SongRecord;
import jp.excd.common.CommonUtils;
import jp.excd.conponent.PlaceHolderInput;

public class S00005 extends HttpServlet {

	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request, response);
	}

	public void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		Connection con = null;
		request.setCharacterEncoding("UTF-8");

		String hostName = "localhost";
		String connectUserName = "meloko";
		String connectPassword = "exceed";
		String dbName = "meloko";

		String timeZone = "Asia/Tokyo";

		// コネクション用のSQL
		final String URL = "jdbc:mysql://"
				+ hostName
				+ ":3306/"
				+ dbName
				+ "?serverTimezone="
				+ timeZone
				+ "&allowPublicKeyRetrieval=true"
				+ "&useSSL=false";



		try {
			// (1)DB接続（コネクションの確立）
			con = DriverManager.getConnection(URL, connectUserName, connectPassword);

			// (2)内部メソッド呼び出し
			this.mainProcessForSearch(request, response, con);

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

	private void mainProcessForSearch(HttpServletRequest request, HttpServletResponse response, Connection con)
			throws IOException, Exception {

		// 接続URL受け取り
		String URL = request.getRequestURI();


		// (1) 接続URLが「/ja/S00005/searh」以外の場合は、404.jspへフォワーディングする。
		if ("/web/ja/S00005/search".equals(URL)) {
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}

		// POSTパラメタの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// (2) 入力項目(POSTパラメタ)を使って、Requestオブジェクトのアトリビュートの初期化をする。
		String release_date_radio = request.getParameter("release_date_radio");
		String release_date_from = request.getParameter("release_date_from");
		String release_date_to = request.getParameter("release_date_to");
		String rating_radio = request.getParameter("rating_radio");
		String rating_from = request.getParameter("rating_from");
		String rating_to = request.getParameter("rating_to");
		String rating_average_radio = request.getParameter("rating_average_radio");
		String rating_average_from = request.getParameter("rating_average_from");
		String rating_average_to = request.getParameter("rating_average_to");
		String views_radio = request.getParameter("views_radio");
		String views_from = request.getParameter("views_from");
		String views_to = request.getParameter("views_to");
		String title_radio = request.getParameter("title_radio");
		String title_type_radio = request.getParameter("title_type_radio");
		String title = request.getParameter("title");
		String sort_order = request.getParameter("sort_order");

		//初期化
		request.setAttribute("error", null);
		request.setAttribute("release_date_error", null);
		request.setAttribute("release_date_radio", release_date_radio);
		request.setAttribute("release_date_from", release_date_from);
		request.setAttribute("release_date_to", release_date_to);
		request.setAttribute("rating_is_error", null);
		request.setAttribute("rating_radio", rating_radio);
		request.setAttribute("rating_from", rating_from);
		request.setAttribute("rating_to", rating_to);
		request.setAttribute("rating_aerage_is_error", null);
		request.setAttribute("rating_average_radio", rating_average_radio);
		request.setAttribute("rating_average_from", rating_average_from);
		request.setAttribute("rating_average_to", rating_average_to);
		request.setAttribute("views_is_error", null);
		request.setAttribute("views_radio", views_radio);
		request.setAttribute("views_from", views_from);
		request.setAttribute("views_to", views_to);
		request.setAttribute("title_is_error", null);
		request.setAttribute("title_radio", title_radio);
		request.setAttribute("title_type_radio", title_type_radio);
		request.setAttribute("title", title);
		request.setAttribute("sort_order", sort_order);

		Integer rf = null;
		Integer rt = null;
		Double rAf = null;
		Double rAt = null;
		Integer vf = null;
		Integer vt = null;

		//-----------------------------------------------------------------------------------------------
		// 入力チェック
		//-----------------------------------------------------------------------------------------------

		// (3) 公開日FROMについてエラー判定を行う。
		if ("1".equals(release_date_radio)) {
			if (release_date_from == null || "".equals(release_date_from)) {
				// 処理継続
			} else if (CommonUtils.isDateValue(release_date_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00005_001");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
			}
		}

		// (4) 公開日TOについてエラー判定を行う。
		if ("1".equals(release_date_radio)) {
			if (release_date_to == null || "".equals(release_date_to)) {
				// 処理継続
			} else if (CommonUtils.isDateValue(release_date_to) == false) {
				// エラー
				String s = this.getDescription(con, "ES00005_002");
				request.setAttribute("error", s);
				request.setAttribute("release_date_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
			} else {
				// 処理継続
			}
		}

		// (5) 公開日FROM、公開日TOについてエラー判定を行う。
		if ("1".equals(release_date_radio)) {
			if (release_date_from == null || "".equals(release_date_from) &&
					(release_date_to == null || "".equals(release_date_to))) {
				//エラー
				String s = this.getDescription(con, "ES00005_003");
				request.setAttribute("error", s);
				request.setAttribute("release_date_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else if (release_date_from == null
					|| "".equals(release_date_from) && CommonUtils.isDateValue(release_date_to) == true) {
				//処理続行
			} else if (CommonUtils.isDateValue(release_date_from)) {
				//処理続行
			}
		}

		// (6) 公開日FROM、公開日TOについてエラー判定を行う。（逆転チェック）
		if ("1".equals(release_date_radio)) {
			if (CommonUtils.isBlank(release_date_from) == false && CommonUtils.isBlank(release_date_to) == false) {
				SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
				Date dateFrom = sdformat.parse(release_date_from);
				Date dateTo = sdformat.parse(release_date_to);
				if (dateFrom.compareTo(dateTo) > 0) {
					// エラー					
					String s = this.getDescription(con, "ES00005_004");
					request.setAttribute("error", s);
					request.setAttribute("release_date_is_error", "1");
					getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
							response);
					return;
				}
			}
		}

		// (7) 感動指数FROMについてエラー判定を行う。
		if ("1".equals(rating_radio)) {
			if (rating_from == null || "".equals(rating_from)) {
				// 処理継続
			} else if (CommonUtils.isWholeNumber(rating_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00005_005");
				request.setAttribute("error", s);
				request.setAttribute("rating_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
				rf = Integer.parseInt(rating_from);
			}
			if (!("1".equals(rating_radio))) {
				//処理続行
			}
		}

		// (8) 感動指数TOについてエラー判定を行う。
		if ("1".equals(rating_radio)) {
			if (rating_to == null || "".equals(rating_to)) {
				//処理継続
			} else if (CommonUtils.isWholeNumber(rating_to) == false) {
				//エラー
				String s = this.getDescription(con, "ES00005_006");
				request.setAttribute("error", s);
				request.setAttribute("rating_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				//処理継続
				rt = Integer.parseInt(rating_to);
			}
			if (!("1".equals(rating_radio))) {
				//処理続行
			}
		}

		// (9) 感動指数FROM 感動指数TO についてエラー判定を行う。
		if ("1".equals(rating_radio))
			if ((rating_from == null || "".equals(rating_from) &&
			(rating_to == null || "".equals(rating_to)))) {
				//エラー
				String s = this.getDescription(con, "ES00005_007");
				request.setAttribute("error", s);
				request.setAttribute("rating_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else if (rating_from == null || "".equals(rating_from) && CommonUtils.isWholeNumber(rating_to) == true) {
				//処理続行
			} else if (CommonUtils.isWholeNumber(rating_from)) {
				//処理続行
			}
		if (!("1".equals(rating_radio))) {
			//処理続行
		}

		// (10) 感動指数FROM　感動指数TO（逆転チェック）
		if ("1".equals(rating_radio)) {
			if (rating_from != null) {
				rf = Integer.parseInt(rating_from);
			}
			if (rating_to != null || rating_to != "") {
				rt = Integer.parseInt(rating_to);
			}
			if (rf != null && rt != null ) {
				if (rf > rt) {
					//エラー
					String s = this.getDescription(con, "ES00005_008");
					request.setAttribute("error", s);
					request.setAttribute("rating_is_error", "1");
					getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
							response);
					return;
				}
			} else {
				//処理続行
			}
			if (!("1".equals(rating_radio))) {
				//処理続行
			}
		}

		// (11) 平均感動指数FROMについてエラー判定を行う。
		if ("1".equals(rating_average_radio)) {
			if (rating_average_from == null || "".equals(rating_average_from)) {
				// 処理継続
			} else if (CommonUtils.isDouble(rating_average_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00005_015");
				request.setAttribute("error", s);
				request.setAttribute("rating_average_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
				rf = Integer.parseInt(rating_from);
			}
			if (!("1".equals(rating_radio))) {
				//処理続行
			}
		}

		// (11) 平均感動指数TOについてエラー判定を行う。
		if ("1".equals(rating_average_radio)) {
			if (rating_average_to == null || "".equals(rating_average_to)) {
				//処理継続
			} else if (CommonUtils.isDouble(rating_average_to) == false) {
				//エラー
				String s = this.getDescription(con, "ES00005_015");
				request.setAttribute("error", s);
				request.setAttribute("rating_average_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				//処理継続
				rt = Integer.parseInt(rating_to);
			}
			if (!("1".equals(rating_radio))) {
				//処理続行
			}
		}

		// (11) 平均感動指数FROM　平均感動指数TO エラーチェック
		if ("1".equals(rating_average_radio)) {
			if (rating_average_from != null) {
				rAf = Double.parseDouble(rating_average_from);
			}
			if (rating_average_to != null) {
				rAt = Double.parseDouble(rating_average_to);
			}
			if (rAf != null && rAt != null){
				if (rAf > rAt) {
					//エラー
					String s = this.getDescription(con, "ES00005_009");
					request.setAttribute("error", s);
					request.setAttribute("rating_average_is_error", "1");
					getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
					.forward(request, response);
					return;
				}

			} else {
				//処理続行
			}
		}
		if (!("1".equals(rating_average_radio))) {
			//処理続行
		}

		// (12) 再生回数FROM エラー判定を行う。
		if ("1".equals(views_radio)) {
			if (views_from == null || "".equals(views_from)) {
				//処理継続
			} else if (CommonUtils.isWholeNumber(views_from) == false) {
				//エラー
				String s = this.getDescription(con, "ES00005_010");
				request.setAttribute("error", s);
				request.setAttribute("views_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
				.forward(request, response);
				return;
			} else {
				//処理継続
				vf = Integer.parseInt(views_from);
			}
			if (!("1".equals(views_radio))) {
				//処理続行
			}
		}
		// (13) 再生回数TO エラー判定を行う。
		if ("1".equals(views_radio)) {
			if (views_to == null || "".equals(views_to)) {
				//処理継続
			} else if (CommonUtils.isWholeNumber(views_to) == false) {
				//えらー
				String s = this.getDescription(con, "ES00005_011");
				request.setAttribute("error", s);
				request.setAttribute("views_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
				.forward(request, response);
				return;
			} else {
				//処理継続
				vt = Integer.parseInt(views_to);
			}
			if (!("1".equals(views_radio))) {
				//処理続行
			}
		}

		// (14) 再生回数FROM　再生回数TO エラー判定を行う。
		if ("1".equals(views_radio))
			if (views_from == null || "".equals(views_from) &&
			(views_to == null|| "".equals(views_to))) {
				//エラー
				String s = this.getDescription(con, "ES00005_012");
				request.setAttribute("error", s);
				request.setAttribute("views_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
				.forward(request, response);
				return;
			} else if (views_from == null
					|| "".equals(views_from) && CommonUtils.isWholeNumber(views_to) == true) {
				//処理続行
			} else if (CommonUtils.isWholeNumber(views_from)) {
				//処理続行
			}
		if (!("1".equals(views_radio))) {
			//処理続行
		}

		// (15) 再生回数FROM、再生回数TOについて、以下のとおりエラー判定を行う。
		if ("1".equals(views_radio)) {
			if (views_from != null) {
				vf = Integer.parseInt(views_from);
			}
			if (views_to != null) {
				vt = Integer.parseInt(views_to);
			}
			if (vf != null && vt != null){
				if(vf > vt){
					//エラー
					String s = this.getDescription(con, "ES00005_013");
					request.setAttribute("error", s);
					request.setAttribute("views_is_error", "1");
					getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
					.forward(request, response);
					return;
				}
			} else {
				//処理続行

			}
			if (!("1".equals(views_radio))) {
				//処理続行
			}
		}

		// (16) 曲名についてエラー判定を行う。
		if ("1".equals(title_radio)) {
			if (title == null || "".equals(title)) {
				//エラー
				String s = this.getDescription(con, "ES00005_014");
				request.setAttribute("error", s);
				request.setAttribute("title_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
				.forward(request, response);
				return;
			} else {
				//処理続行
				if (!("1".equals(title_radio))) {
					//処理続行
				}

			}
		}

		// (17) SQLの組み立てと、Where句への値の設定を行う。
		List<SongRecord> results2 = null;
		try {
			results2 = executeQuery(request, response, con,
					release_date_radio,
					release_date_from,
					release_date_to,
					rating_radio,
					rating_from,
					rating_to,
					rating_average_radio,
					rating_average_from,
					rating_average_to,
					views_radio,
					views_from,
					views_to,
					title_radio,
					title_type_radio,
					title,
					sort_order);
		} catch (Exception e) {
			System.out.println(e);
			String error = getDescription(con, "ES00005_015");
			request.setAttribute("error", error);
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request, response);
			return;
		}

		if (results2 == null) {
			results2 = new ArrayList<SongRecord>();
		}
		int listSize = results2.size();

		// (18) ゼロ件チェック
		if (listSize == 0) {
			String error = getDescription(con, "ES00005_016");
			request.setAttribute("error", error);
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request, response);
			return;
		}

		// (19) 前処理で得られたListを用いて、Requestオブジェクトに値を設定していく。
		List<SongRecord> newList =  new ArrayList<SongRecord>();

		int counter = 0;
		String hits = null;

		for (SongRecord l: results2) {
			counter = counter + 1;
			// 先頭の10件のみ処理を行う。
			if (counter < 10) {
				String count = NumberFormat.getNumberInstance().format(counter);
				hits = count+"件該当します。";
			}else if (counter >= 10) {
				String count = NumberFormat.getNumberInstance().format(counter);
				hits = count+"件該当します(うち10件を表示しています)。";	
			}
			newList.add(l);
		}

		List<SongRecord> resultList = new ArrayList<SongRecord>();

		if(newList.size() < 10) {
			resultList.addAll(newList);
		} else {
			for (int i = 0; i < 10; i++) {
				resultList.add(newList.get(i));
			}
		}

		String count = NumberFormat.getNumberInstance().format(counter);
		request.setAttribute("hits", hits);
		request.setAttribute("list", resultList);

		// (20) S00006.jsp にフォワーディングする。
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00006.jsp").forward(request, response);
	}

	//文言マスタより引数で渡されたkeyをIDにもつレコードを取得
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

	private List<SongRecord> executeQuery(HttpServletRequest request, HttpServletResponse response, Connection con,
			String release_date_radio,
			String release_date_from,
			String release_date_to,
			String rating_radio,
			String rating_from,
			String rating_to,
			String rating_average_radio,
			String rating_average_from,
			String rating_average_to,
			String views_radio,
			String views_from,
			String views_to,
			String title_radio,
			String title_type_radio,
			String title,
			String sort_order) throws Exception {

		@SuppressWarnings("unused")
		boolean joinFlg = false; // true:結合した、false：結合していない

		// (1) SQLの断片を準備する
		String sql1 = "SELECT song.id, song.title, song.total_listen_count, song.release_datetime, song.image_file_name, rating_total, rating_average ";
		String sql2 = "from song left join ( select song_id, sum(rating.rating) as rating_total, avg(rating.rating) as rating_average from rating group by song_id) as calc_rating on song.id = calc_rating.song_id ";
		String sql3 = "WHERE ";
		String sql4 = "release_datetime>=? ";
		String sql5 = "AND ";
		String sql6 = "release_datetime<=? ";
		String sql7 = "AND ";
		String sql8 = "rating_total >= ? ";
		String sql9 = "AND ";
		String sql10 = "rating_total <=? ";
		String sql11 = "AND ";
		String sql12 = "rating_average >=? ";
		String sql13 = "AND ";
		String sql14 = "rating_average <=? ";
		String sql15 = "AND ";
		String sql16 = "total_listen_count>=? ";
		String sql17 = "AND ";
		String sql18 = "total_listen_count<=? ";
		String sql19 = "AND ";
		String sql20 = "title like ";
		String sql21 = "title= ";
		String sql22 = "group by song.id ORDER BY release_datetime desc";
		String sql23 = "group by song.id ORDER BY release_datetime asc";
		String sql24 = "group by song.id ORDER BY rating_total desc";
		String sql25 = "group by song.id ORDER BY rating_total asc";
		String sql26 = "group by song.id ORDER BY rating_average desc";
		String sql27 = "group by song.id ORDER BY rating_average asc";
		String sql28 = "group by song.id ORDER BY total_listen_count desc";
		String sql29 = "group by song.id ORDER BY total_listen_count asc";
		String sql30 = " ,id desc;";

		// (2) SQLを連結するための文字列を宣言する。
		String query = sql1 + sql2;

		// (3) プレイスホルダに設定する値を格納するためのListを用意する。
		List<PlaceHolderInput> list = new ArrayList<PlaceHolderInput>();

		// (4) 公開日FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(release_date_radio)) {
			if (release_date_from == null || "".equals(release_date_from)) {
				// 処理続行
			} else if (CommonUtils.isDateValue(release_date_from)) {
				// No.3を連結する。
				query = query + sql3;
				// No,4を連結する
				query = query + sql4;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(CommonUtils.getDateValue(release_date_from));
				list.add(phi);
			}
		} else {
			// 処理続行
		}
		// (5) 公開日TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(release_date_radio)) {
			if (release_date_to == null || "".equals(release_date_to)) {
				//処理続行
			}else if (CommonUtils.isDateValue(release_date_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql5;
				}
				query = query + sql6;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(CommonUtils.getDateValue(release_date_to));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}

		// (6) 感動指数FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_radio)) {
			if (rating_from == null || "".equals(rating_from)) {
				//処理続行
			}else if (CommonUtils.isNumber(rating_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql7;
				}
				query = query + sql8;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(rating_from);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (7) 感動指数TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_radio)) {
			if (rating_to == null || "".equals(rating_to)) {
				//処理続行
			}else if (CommonUtils.isNumber(rating_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql9;
				}
				query = query + sql10;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(rating_to);
				list.add(phi);

			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (8) 平均感動指数FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_average_radio)) {
			if (rating_average_from == null || "".equals(rating_average_from)) {
				//処理続行
			}else if (CommonUtils.isDouble(rating_average_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql11;
				}
				query = query + sql12;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(Double.parseDouble(rating_average_from));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (9) 平均感動指数TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_average_radio)) {
			if (rating_average_to == null || "".equals(rating_average_to)) {
				//処理続行
			}else if (CommonUtils.isDouble(rating_average_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql13;
				}
				query = query + sql14;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(Double.parseDouble(rating_average_to));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (10) 再生回数FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(views_radio)) {
			if (views_from == null || "".equals(views_from)) {
				//処理続行
			} else if (CommonUtils.isNumber(views_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql15;
				}
				query = query + sql16;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("1");
				phi.setIntValue(Integer.parseInt(views_from));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (11) 再生回数TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(views_radio)) {
			if (views_to == null || "".equals(views_to)) {
				//処理続行
			} else if (CommonUtils.isNumber(views_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql17;
				}
				query = query + sql18;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("1");
				phi.setIntValue(Integer.parseInt(views_to));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (12) 曲名のSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(title_radio)) {
			if (title == null || "".equals(title)) {
				//処理続行
			} else {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql19;
				}
				if ("1".equals(title_type_radio)) {
					query = query + sql20 + "'%" + title + "%' ";
				} else if ("2".equals(title_type_radio)) {
					query = query + sql21 + "'" + title + "' ";
				} else {
					throw new Exception();
				}
			}
		} else {
			//処理続行
		}

		// (13) 並び順の値に従って、ORDER BY句を連結する。
		if ("01".equals(sort_order)) {
			query = query + sql22;
		} else if ("02".equals(sort_order)) {
			query = query + sql23;
		} else if ("03".equals(sort_order)) {
			query = query + sql24;
		} else if ("04".equals(sort_order)) {
			query = query + sql25;
		} else if ("05".equals(sort_order)) {
			query = query + sql26;
		} else if ("06".equals(sort_order)) {
			query = query + sql27;
		} else if ("07".equals(sort_order)) {
			query = query + sql28;
		} else if ("08".equals(sort_order)) {
			query = query + sql29;
		} else {
			throw new Exception();
		}

		query = query + sql30;

		// (14) PreparedStatementのインスタンスを得る。
		PreparedStatement pstmt = con.prepareStatement(query);

		// (15) Where句の連結があれば、(4)で生成したプレイスホルダ用のListの内容をすべて、プレイスホルダに設定する。
		for (int i = 0; i < list.size(); i++) {
			PlaceHolderInput option = list.get(i);
			String type = option.getType();
			if ("1".equals(type)) {
				pstmt.setInt(i + 1, option.getIntValue());
			} else if ("2".equals(type)) {
				pstmt.setDouble(i + 1, option.getDoubleValue());
			} else if ("3".equals(type)) {
				pstmt.setString(i + 1, option.getStringValue());
			}
		}

		// (16) executeQueryを実行し、結果の「ResultSet」を得る。
		ResultSet rs = pstmt.executeQuery();
		List<SongRecord> songList = new ArrayList<SongRecord>();

		while (rs.next()) {
			SongRecord record = new SongRecord();
			//ソングID
			String Song_id = rs.getString("id");
			record.setSong_id(Song_id);
			//曲名
			String Title = rs.getString("title");
			record.setTitle(Title);
			//総評価数
			String Rating_total = NumberFormat.getNumberInstance().format(rs.getLong("rating_total"));
			record.setRating_total(Rating_total);
			//平均評価数
			String Rating_average = CommonUtils.averageformat(rs.getDouble("rating_average"));
			record.setRating_average(Rating_average);
			//再生回数
			String Total_listen_count = NumberFormat.getNumberInstance().format(rs.getLong("Total_listen_count"));
			record.setTotal_listen_count(Total_listen_count);
			//公開日
			String now = CommonUtils.epoch(rs.getDouble("release_datetime"));
			record.setRelease_datetime(now);
			//ファイルネーム
			String Image_file_name = rs.getString("Image_file_name");
			record.setImage_file_name(Image_file_name);
			//
			songList.add(record);
		}

		// (17) ResultSetのインスタンス、PreparedStatementのインスタンスをクローズする。
		pstmt.close();

		// (18) 前処理で生成したListを呼び出し元に返却する。
		return songList;
	}
}