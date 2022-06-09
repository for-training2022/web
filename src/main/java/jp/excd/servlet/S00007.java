package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.excd.bean.ComposerRecord;
import jp.excd.common.CommonUtils;
import jp.excd.conponent.PlaceHolderInput;

public class S00007 extends HttpServlet {

	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		


		
		// ホスト名
		String hostName = "localhost";

		// ユーザ
		String connectUserName = "root";

		// パスワード
		String connectPassword = "exceed";

		// DB名
		String dbName = "meloko";

		// timeZone
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

		// コネクション
		Connection con = null;

		try {
			// コネクション
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

		// (1) 接続URLが「/ja/S00007/searh」以外の場合は、404.jspへフォワーディングする。
		if ("/web/ja/S00007/search".equals(URL)) {
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}

		// POSTパラメタの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// (2) 入力項目(POSTパラメタ)を使って、Requestオブジェクトのアトリビュートの初期化をする。
		String nickname_radio = request.getParameter("nickname_radio");
		String nickname_type_radio = request.getParameter("nickname_type_radio");
		String nickname = request.getParameter("nickname");
		String joined_date_radio = request.getParameter("joined_date_radio");
		String joined_date_from = request.getParameter("joined_date_from");
		String joined_date_to = request.getParameter("joined_date_to");
		String gender_radio = request.getParameter("gender_radio");
		String gender = request.getParameter("gender");
		String birthday_radio = request.getParameter("birthday_radio");
		String birthday_from = request.getParameter("birthday_from");
		String birthday_to = request.getParameter("birthday_to");
		String listener_count_radio = request.getParameter("listener_count_radio");
		String listener_count_from = request.getParameter("listener_count_from");
		String listener_count_to = request.getParameter("listener_count_to");
		String language_type_jp = request.getParameter("language_type_jp");
		String language_type_en = request.getParameter("language_type_en");
		String sort_order = request.getParameter("sort_order");

		
		//初期化
		request.setAttribute("error", null);
		request.setAttribute("nickname_is_error", null);
		request.setAttribute("nickname_radio", nickname_radio);
		request.setAttribute("nickname_type_radio", nickname_type_radio);
		request.setAttribute("nickname", nickname);
		request.setAttribute("joined_date_is_error", null);
		request.setAttribute("joined_date_radio", joined_date_radio);
		request.setAttribute("joined_date_from", joined_date_from);
		request.setAttribute("joined_date_to", joined_date_to);
		request.setAttribute("gender_is_error", null);
		request.setAttribute("gender_radio", gender_radio);		
		request.setAttribute("gender", gender);
		request.setAttribute("birthday_is_error", null);
		request.setAttribute("birthday_radio", birthday_radio);
		request.setAttribute("birthday_from", birthday_from);
		request.setAttribute("birthday_to", birthday_to);
		request.setAttribute("listener_count_is_error", null);
		request.setAttribute("listener_count_radio", listener_count_radio);
		request.setAttribute("listener_count_from", listener_count_from);
		request.setAttribute("listener_count_to", listener_count_to);
		request.setAttribute("language_type_is_error", null);
		request.setAttribute("language_type_jp", language_type_jp);
		request.setAttribute("language_type_en", language_type_en);
		request.setAttribute("sort_order", sort_order);

		Integer rf = null;
		Integer rt = null;
		Double rAf = null;
		Double rAt = null;


		//-----------------------------------------------------------------------------------------------
		// 入力チェック
		//-----------------------------------------------------------------------------------------------

		
		
		//(3)ニックネームについてエラー判定を行う。
		if("1".equals(nickname_radio)) {
			if(nickname == null || "".equals(nickname)) {
				//エラー
				String s = this.getDescription(con, "ES00007_001");
				request.setAttribute("error", s);
				request.setAttribute("nickname_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp")
						.forward(request, response);
				return;
			}else{
				//処理続行
				if(!("1".equals(nickname_radio))) {
					//処理続行
				}
			}
		}
				
		// (4) 登録日FROMについてエラー判定を行う。
		if ("1".equals(joined_date_radio)) {
			if (joined_date_from == null || "".equals(joined_date_from)) {
				// 処理継続
			} else if (this.isDateValue(joined_date_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00007_002");
				request.setAttribute("error", s);
				request.setAttribute("joined_date_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
			}
		}
		
		// (5) 登録日TOについてエラー判定を行う。
		if ("1".equals(joined_date_radio)) {
			if (joined_date_to == null || "".equals(joined_date_to)) {
				// 処理継続
			} else if (this.isDateValue(joined_date_to) == false) {
				// エラー
				String s = this.getDescription(con, "ES00007_003");
				request.setAttribute("error", s);
				request.setAttribute("joined_date_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
			} else {
				// 処理継続
			}
		}
		
		// (6) 登録日FROM、登録日TOについてエラー判定を行う。
		if ("1".equals(joined_date_radio)) {
			if (joined_date_from == null || "".equals(joined_date_from) &&
			(joined_date_to == null || "".equals(joined_date_to))) {
				//エラー
				String s = this.getDescription(con, "ES00007_004");
				request.setAttribute("error", s);
				request.setAttribute("joined_date_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else if (joined_date_from == null
					|| "".equals(joined_date_from) && this.isDateValue(joined_date_to) == true) {
				//処理続行
			} else if (this.isDateValue(joined_date_from)) {
				//処理続行
			}
		}

		// (7) 登録日FROM、登録日TOについてエラー判定を行う。
		if ("1".equals(joined_date_radio)) {
			int checkResult = joined_date_radio.compareTo(joined_date_from);
			if (checkResult > 0 ){
				// エラー
				String s = this.getDescription(con, "ES00007_005");
				request.setAttribute("error", s);
				request.setAttribute("joined_date_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			}
		}

		//(8) 誕生日FROMについてエラー判定を行う。
		if ("1".equals(birthday_radio)) {
			if (birthday_from == null || "".equals(birthday_from)) {
				// 処理継続
			} else if (this.isDateValue(birthday_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00007_006");
				request.setAttribute("error", s);
				request.setAttribute("birthday_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
				rf = Integer.parseInt(birthday_from);
			}
			if (!("1".equals(birthday_radio))) {
				//処理続行
			}
		}
		
		//(9) 誕生日TOについてエラー判定を行う。
		if("1".equals(birthday_to)) {
			if(birthday_to == null || "".equals(birthday_to)) {
				//処理続行
			}else if (this.isDateValue(birthday_to) == false) {
				//エラー
				String s = this.getDescription(con, "ES00007_007");
				request.setAttribute("error", s);
				request.setAttribute("birthday_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
					response);
			} else {
			// 処理継続
			}
		}
		
		
		//(10) 誕生日FROM、誕生日TOについてエラー判定を行う。
		if ("1".equals(birthday_radio)) {
			if (birthday_radio == null || "".equals(birthday_from) &&
			(birthday_to == null || "".equals(birthday_to))) {
				//エラー
				String s = this.getDescription(con, "ES00007_008");
				request.setAttribute("error", s);
				request.setAttribute("birthday_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else if (birthday_from == null
					|| "".equals(birthday_from) && this.isDateValue(birthday_to) == true) {
				//処理続行
			} else if (this.isDateValue(birthday_from)) {
				//処理続行
			}
		}
		
		
		//(11) 誕生日FROM、誕生日TOについてエラー判定を行う。
		if ("1".equals(birthday_radio)) {
			int checkResult = birthday_radio.compareTo(birthday_from);
			if (checkResult > 0 ){
				// エラー
				String s = this.getDescription(con, "ES00007_009");
				request.setAttribute("error", s);
				request.setAttribute("birthday_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			}
		}

		//(12) リスナー数_FROMについて、以下のとおりエラー判定を行う。
		if ("1".equals(listener_count_radio)) {
			if (listener_count_from == null || "".equals(listener_count_from)) {
				// 処理継続
			} else if (this.isNumber(listener_count_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00007_010");
				request.setAttribute("error", s);
				request.setAttribute("listener_count_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
				rf = Integer.parseInt(listener_count_from);
			}
			if (!("1".equals(listener_count_radio))) {
				//処理続行
			}
		}
		
				
		// (13) リスナー数TOについて、以下のとおりエラー判定を行う。
		if ("1".equals(listener_count_radio)) {
			if (listener_count_to == null || "".equals(listener_count_to)) {
				//処理継続
			} else if (this.isNumber(listener_count_to) == false) {
				//エラー
				String s = this.getDescription(con, "ES00007_011");
				request.setAttribute("error", s);
				request.setAttribute("listener_count_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else {
				//処理継続
				rt = Integer.parseInt(listener_count_to);
			}
			if (!("1".equals(listener_count_radio))) {
				//処理続行
			}
		}
		
		
		// (14) リスナー数FROM リスナー数TOについてエラー判定を行う。
		if ("1".equals(listener_count_radio))
			if ((listener_count_from == null || "".equals(listener_count_from) &&
			(listener_count_to == null || "".equals(listener_count_to)))) {
				//エラー
				String s = this.getDescription(con, "ES00007_012");
				request.setAttribute("error", s);
				request.setAttribute("listener_count_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else if (listener_count_from == null || "".equals(listener_count_from) && this.isNumber(listener_count_to) == true) {
				//処理続行
			} else if (this.isNumber(listener_count_from)) {
				//処理続行
			}
		if (!("1".equals(listener_count_radio))) {
			//処理続行
		}
		
		
		//(15) リスナー数FROM リスナー数TOについてエラー判定を行う。
		if("1".equals(listener_count_radio)) {
			if (listener_count_from != null) {
				rf = Integer.parseInt(listener_count_from);
			}
			if (listener_count_to != null) {
				rt = Integer.parseInt(listener_count_to);
			}
			if (rAf != null || rAt != null || rf > rt) {
				//エラー
				String s = this.getDescription(con, "ES00007_013");
				request.setAttribute("error", s);
				request.setAttribute("listener_count_is_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
						response);
				return;
			} else {
			//処理続行
				}
				if (!("1".equals(listener_count_radio))) {
					//処理続行
				}
		}

		
		// (16) 言語について、以下のとおりエラー判定を行う。
		if ((!"002".equals(language_type_jp)) && (!"001".equals(language_type_en))){
			//エラー
			String s = this.getDescription(con, "ES00007_014");
			request.setAttribute("error", s);
			request.setAttribute("language_type_is_error", "1");
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request,
					response);
			return;
		}
		
		
		// (17) SQLの組み立てと、Where句への値の設定を行う。
		List<ComposerRecord> results2 = null;
		try {
			results2 = executeQuery(request, response, con,
					nickname_radio,
					nickname_type_radio,
					nickname,
					joined_date_radio,
					joined_date_from,
					joined_date_to,
					gender_radio,
					gender,
					birthday_radio,
					birthday_from,
					birthday_to,
					listener_count_radio,
					listener_count_from,
					listener_count_to,
					language_type_jp,
					language_type_en,
					sort_order);
		} catch (Exception e) {
			String error = getDescription(con, "ES00007_015");
			request.setAttribute("error", error);
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request, response);
			return;
		}
		if (results2 == null) {
			results2 = new ArrayList<ComposerRecord>();
		}
		int listSize = results2.size();

		// (18) 前処理で得られたListがゼロ件の場合
		if (listSize == 0) {
			String error = getDescription(con, "ES00007_016");
			request.setAttribute("error", error);
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00007.jsp").forward(request, response);
			return;
		}
		
		// (19) 前処理で得られたListを用いて、Requestオブジェクトに値を設定していく。
		List<ComposerRecord> newList =  new ArrayList<ComposerRecord>();
		
		int counter = 0;
		
		for (ComposerRecord l: results2) {
			counter = counter + 1;
			// 先頭の10件のみ処理を行う。
			if (counter > 10) {
				break;
			}
			newList.add(l);
		}
		String count = NumberFormat.getNumberInstance().format(counter);
		request.setAttribute("hits", count);
		request.setAttribute("list", newList);

		// (20) S00008.jsp にフォワーディングする。
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00008.jsp").forward(request, response);
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

	private List<ComposerRecord> executeQuery(HttpServletRequest request, HttpServletResponse response, Connection con,
			String nickname_radio,
			String nickname_type_radio,
			String nickname,
			String joined_date_radio,
			String joined_date_from,
			String joined_date_to,
			String gender_radio,
			String gender,
			String birthday_radio,
			String birthday_from,
			String birthday_to,
			String listener_count_radio,
			String listener_count_from,
			String listener_count_to,
			String language_type_jp,
			String language_type_en,
			String sort_order) throws Exception {

		@SuppressWarnings("unused")
		boolean joinFlg = false; // true:結合した、false：結合していない

		// (1) SQLの断片を準備する
		String sql1 = "SELECT unique_code, nickname, joined_date, gender, birthday,listener_count,language_type ";
		String sql2 = "FROM composer ";
		String sql3 = "WHERE ";
		String sql4 = "joined_date >= joined_date_from";
		String sql5 = "AND ";
		String sql6 = "joined_date <= joined_date_to ";
		String sql7 = "AND ";
		String sql8 = "gender = gender ";
		String sql9 = "AND ";
		String sql10 = "birthday >= birthday_from ";
		String sql11 = "AND ";
		String sql12 = "birthday <= birthday_to ";
		String sql13 = "AND ";
		String sql14 = "listener_count >= listener_count_from ";
		String sql15 = "AND ";
		String sql16 = "listener_count <= listener_count_to ";
		String sql17 = "AND ";
		String sql18 = "language_type = ? ";
		String sql19 = "language_type = ? or  language_type = ? ";
		String sql20 = "AND ";
		String sql21 = "nickname like ? ";
		String sql22 = "nickname = nickname ";
		String sql23 = "ORDER BY joined_date desc ";
		String sql24 = "ORDER BY joined_date asc ";
		String sql25 = "ORDER BY listener_count desc ";
		String sql26 = "ORDER BY listener_count asc ";
		String sql27 = " ,unique_code desc;";
		 

		// (2) SQLを連結するための文字列を宣言する。
		String query = sql1 + sql2;
		
		//プリペアド
		PreparedStatement pstmt = null;
		
		try {
			//日本語のとき
			if (("002".equals(language_type_jp)) && (!"001".equals(language_type_en))) {
			pstmt = con.prepareStatement(sql18);
			pstmt.setString(1,"002");
			}
			//英語のとき
			if ((!"002".equals(language_type_jp)) && ("001".equals(language_type_en))) {
				pstmt = con.prepareStatement(sql18);
				pstmt.setString(1,"001");
			}
			//日本語・英語のとき
			if (("002".equals(language_type_jp)) && ("001".equals(language_type_en))) {
				pstmt = con.prepareStatement(sql19);
				pstmt.setString(1,"002");
				pstmt.setString(2,"001");
			}
			
			//ニックネームあいまい検索
			if("1".equals(nickname_type_radio)) {
				pstmt = con.prepareStatement(sql21);
				pstmt.setString(1,"%"+nickname+"%");
			}	
			
		}catch (SQLException e) {
					e.printStackTrace();
				}
		
		
		
		// (3) プレイスホルダに設定する値を格納するためのListを用意する。
		List<PlaceHolderInput> list = new ArrayList<PlaceHolderInput>();
		
		// (4) 登録日FROMのSQLへの連結及びプレイスホルダへの設定
			if ("1".equals(joined_date_radio)) {
				if (joined_date_from == null || "".equals(joined_date_from)) {
					// 処理続行
				}
				if (this.isDateValue(joined_date_from)) {
					// No.3を連結する。
					query = query + sql3;
					// No,4を連結する
					query = query + sql4;
	
					PlaceHolderInput p = new PlaceHolderInput();
					p.setType("2");
					p.setValue(joined_date_from);
					list.add(p);
				}
			} else {
				// 処理続行
			}		
		
		// (5) 登録日TOのSQLへの連結及びプレイスホルダへの設定
				if ("1".equals(joined_date_radio)) {
					if (joined_date_to == null || "".equals(joined_date_to)) {
						//処理続行
					}
					if (this.isDateValue(joined_date_to)) {
						if (list.size() == 0) {
							query = query + sql3;
						} else {
							query = query + sql5;
						}
						query = query + sql6;

						PlaceHolderInput p = new PlaceHolderInput();
						p.setType("2");
						p.setValue(joined_date_to);
						list.add(p);
					} else {
						throw new Exception();
					}
				} else {
					//処理続行
				}

		//(6) 性別のSQLへの連結及びプレイスホルダへの設定
				if("1".equals(gender_radio)) {
					if (list.size() == 0) {
						query = query + sql3;
					} else {
						query = query + sql7;
					}	
					query = query + sql8;
					
					PlaceHolderInput p = new PlaceHolderInput();
					p.setType("2");
					p.setValue(gender);
					list.add(p);
				} else {
					//処理続行
				}

		//(7) 誕生日FROMのSQLへの連結及びプレイスホルダへの設定
				if("1".equals(birthday_radio)) {
					if (birthday_from == null || "".equals(birthday_from)) {
						// 処理続行
					}
					if (this.isDateValue(joined_date_from)) {
						// No.3を連結する。
						query = query + sql3;
						// No,4を連結する
						query = query + sql9;	
					}
					query = query + sql10;
					
						PlaceHolderInput p = new PlaceHolderInput();
						p.setType("2");
						p.setValue(birthday_from);
						list.add(p);					
				} else {
					// 処理続行
				}			
				
				
		//(8) 誕生日TOのSQLへの連結及びプレイスホルダへの設定
				if("1".equals(birthday_radio)) {
					if (birthday_to == null || "".equals(birthday_to)) {
						//処理続行
					}
					if (this.isDateValue(birthday_to)) {
						if (list.size() == 0) {
							query = query + sql3;
						} else {
							query = query + sql11;
						}
						query = query + sql12;

						PlaceHolderInput p = new PlaceHolderInput();
						p.setType("2");
						p.setValue(birthday_to);
						list.add(p);
					} else {
						throw new Exception();
					}
				} else {
					//処理続行
				}
			
	
				
		//(9) リスナー数FROMのSQLへの連結及びプレイスホルダへの設定	
				if ("1".equals(listener_count_radio)) {
					if (listener_count_from == null || "".equals(listener_count_from)) {
						//処理続行
					}
					if (this.isNumber(listener_count_from)) {
						if (list.size() == 0) {
							query = query + sql3;
						} else {
							query = query + sql13;
						}
						query = query + sql14;

						PlaceHolderInput p = new PlaceHolderInput();
						p.setType("1");
						p.setValue(listener_count_from);
						list.add(p);
					} else {
						throw new Exception();
					}
				} else {
					//処理続行
				}
		
		//(10) リスナー数TOのSQLへの連結及びプレイスホルダへの設定

				if ("1".equals(listener_count_radio)) {
					if (listener_count_to == null || "".equals(listener_count_to)) {
						//処理続行
					}
					if (this.isNumber(listener_count_to)) {
						if (list.size() == 0) {
							query = query + sql3;
						} else {
							query = query + sql15;
						}
						query = query + sql16;

						PlaceHolderInput p = new PlaceHolderInput();
						p.setType("1");
						p.setValue(listener_count_to);
						list.add(p);

					} else {
						throw new Exception();
					}
				} else {
					//処理続行
				}
				
		//(11) 言語のSQLへの連結及びプレイスホルダへの設定
				if ("002".equals(language_type_jp)) {
					if ("001".equals(language_type_en)) {
						if (list.size() == 0) {
							query = query + sql3;
						} else {
							query = query + sql17;
						}
						query = query + sql19;
						
						PlaceHolderInput p = new PlaceHolderInput();
						p.setType("2");
						p.setValue(language_type_jp);
						p.setValue(language_type_en);
						list.add(p);						
					} else {
						if (list.size() == 0) {
							query = query + sql3;
						} else {
							query = query + sql17;
						}
						query = query + sql18;

						PlaceHolderInput p = new PlaceHolderInput();
						p.setType("2");
						p.setValue(language_type_jp);
						p.setValue(language_type_en);
						list.add(p);
						}
				} else if ("001".equals(language_type_en)){
					if (list.size() == 0) {
						query = query + sql3;
					} else {
						query = query + sql17;
					}
					query = query + sql18;

					PlaceHolderInput p = new PlaceHolderInput();
					p.setType("2");
					p.setValue(language_type_jp);
					p.setValue(language_type_en);
					list.add(p);
				} else {
							throw new Exception();	
				}
				
		//(12) ニックネームのSQLへの連結及びプレイスホルダへの設定
			
				if("1".equals(nickname_radio)) {
					if(nickname == null || "".equals(nickname)) {
						//処理続行
					} else {
						if(list.size() == 0) {
							query = query + sql3;
						} else {
							query = query + sql20;
							}if("1".equals(nickname_type_radio)) {
								query = query + sql21;
							} else if(! "1".equals(nickname_type_radio)) {
								query = query + sql22;
								
								PlaceHolderInput p = new PlaceHolderInput();
								p.setType("2");
								p.setValue(nickname);
								list.add(p);
							} else {
								throw new Exception();
							}
					}
				} else {
					//処理続行
				}

		// (13) 並び順の値に従って、ORDER BY句を連結する。
		if ("01".equals(sort_order)) {
			query = query + sql23;
		} else if ("02".equals(sort_order)) {
			query = query + sql24;
		} else if ("03".equals(sort_order)) {
			query = query + sql25;
		} else if ("04".equals(sort_order)) {
			query = query + sql26;
		} else {
			throw new Exception();
		}

		query = query + sql27;

		// (14) PreparedStatementのインスタンスを得る。
		pstmt = con.prepareStatement(query);
		
		
		// (15) Where句の連結があれば、(4)で生成したプレイスホルダ用のListの内容をすべて、プレイスホルダに設定する。
		for (int i = 0; i < list.size(); i++) {
			PlaceHolderInput option = list.get(i);
			String type = option.getType();
			if ("1".equals(type)) {
				pstmt.setInt(i + 1, option.getIntValue());
			} else if ("2".equals(type)) {
				pstmt.setString(i + 1, option.getStringValue());	
			}
		}

		// (16) executeQueryを実行し、結果の「ResultSet」を得る。
		ResultSet rs = pstmt.executeQuery();

		List<ComposerRecord> composerList = new ArrayList<ComposerRecord>();
											
		while (rs.next()) {
			ComposerRecord record = new ComposerRecord();
			CommonUtils c = new CommonUtils();
			
			//ユニークコード
			String Unique_code = rs.getString("unique_code");
			record.setUniqueCode(Unique_code);
			//ニックネーム
			String Nickname = rs.getString("nickname");
			record.setNickname(Nickname);
			//登録日
			String Joined_date = rs.getString("Joined_date");
			record.setJoined_date(Joined_date);
			//性別
			String Gender = rs.getString("gender");
			record.setGender(Gender);
			//誕生日
			String Birthday = rs.getString("birthday");
			record.setBirthday(Birthday);
			//リスナー数
			String Listener_count = c.valueformat(rs.getLong("listener_count"));
			record.setListener_count(Listener_count);
			//言語
			String Language_type = rs.getString("language_type");
			record.setLanguage_type (Language_type);

			composerList.add(record);
		}
		
		request.setAttribute("list", composerList);

		// (17) ResultSetのインスタンス、PreparedStatementのインスタンスをクローズする。
		pstmt.close();

		// (18) 前処理で生成したListを呼び出し元に返却する。
		return composerList;
	
	}
			
	private boolean isNumber(String listener_count_from) {
		try {
			Integer.parseInt(listener_count_from);
			return true;
		} catch (NumberFormatException e) {
			return false; // エラーにならないように、とりあえずダミー
		}
	}

	private boolean isDouble(String num) {
		try {
			Double.parseDouble(num);
			return true;
		} catch (NumberFormatException e) {
			return false; // エラーにならないように、とりあえずダミー
		}
	}

	private boolean isDateValue(String value) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			format.parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private long getDateValue(String value) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = format.parse(value);
		long miliTime = dt.getTime();
		long retValue = (miliTime / 1000);
		return retValue;
	}


}