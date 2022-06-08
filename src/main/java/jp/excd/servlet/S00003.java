package jp.excd.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.excd.common.CommonUtils;

public class S00003 extends HttpServlet{


	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException{

		Connection con = null;

		//DB接続

		try {
			con = MySQLSetting.getConnection("meloko","meloko","exceed","JST");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			mainSongSearch(request, response, con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{

			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new ServletException();
			}

		}

	}

	private void mainSongSearch(HttpServletRequest request, HttpServletResponse response, Connection con, PreparedStatement pstmt,ResultSet rs) throws ServletException, IOException, SQLException {

		// 接続URL受け取り
		String requestUrl = request.getRequestURL().toString();

		// (1) 接続URLが「/ja/S00005/searh」以外の場合は、404.jspへフォワーディングする。
		if ("/web/ja/S00005/search".equals(requestUrl)) {
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}
		//（2）受け取ったURIをrequestURI()メソッドで取得し、requestUri変数で受け取る。
		String requestUri= request.getRequestURI();

		//（3）spilit()を使い、取得したuriを分割。url内の曲id/song_idを取得し、変数idに代入する。
		String[] uri = requestUri.split("/");
		String id = uri[4];


		//(4)SQL文の設定
		String SongSql = "select"
				+ "    song.title"
				+ "    , song.composer_id"
				+ "    , song.rating_total"
				+ "    , song.rating_average"
				+ "    , song.total_listen_count"
				+ "    , song.release_datetime"
				+ "    , song.last_update_datetime"
				+ "    , sonf.message"
				+ "    , song.key"
				+ "    , song.score_type"
				+ "    , song.bpm"
				+ "    , song.image_file_name"
				+ "    , song.image_file_height"
				+ "    , song.image_file_width"
				+ "    , song.other_link_url"
				+ "    , song.other_link_description"
				+ "    , composer.nickname"
				+ "    , composer.unique_code "
				+ "from"
				+ "    song "
				+ "    left join composer "
				+ "        on song.composer_id = composer.id "
				+ "where"
				+ "    id = ?";


		String CommentSql ="select"
				+ "    comment.sequence"
				+ "    , comment.composer_id"
				+ "    , comment.comment"
				+ "    , comment.type"
				+ "    , comment.to_comment_id"
				+ "    , comment.write_datetime"
				+ "    , rating.rating"
				+ "    , composer.unique_code"
				+ "    , composer.nickname "
				+ "from"
				+ "    ( "
				+ "        ( "
				+ "            comment "
				+ "                left join rating "
				+ "                    on comment.song_id = rating.song_id"
				+ "        ) "
				+ "            left join composer "
				+ "                on comment.composer_id = composer.id "
				+ "        where"
				+ "            comment.song_id = ?"
				+ "   order by sequence asc";

		//（5）SongSqlを使い、引数で渡されたconn.prepareStatement()メソッドを実行する。
		pstmt = con.prepareStatement(SongSql);

		//(6)Sqlの？に（3）で値を代入したidをセットして実行する
		pstmt.setString(1, id);

		//（7）executeQueryを実行し、結果の「ResultSet」を得る。
		rs = pstmt.executeQuery();

		//(8)クラスDataBox（他シートに記載）、クラスCommonFormat（共通設計に記載）のインスタンスを作成。
		DataBox db = new DataBox();
		List<DataBox> SongList = new ArrayList<DataBox>();

		//(8)while文を通してSongListに追加。
		while(rs.next()) {
			db.setTitle(rs.getString("title"));
			db.setRatingTotal(CommonUtils.valueformat(rs.getLong("composer_id")));
			db.setRatingAverage(CommonUtils.averageformat(rs.getDouble("rating_average")));
			db.setTotalListenCount(CommonUtils.valueformat(rs.getLong("total_listen_count")));
			db.setReleaseDatetime(CommonUtils.epoch(rs.getDouble("release_datetime")));
			db.setLastUpdateDatetime(CommonUtils.epoch(rs.getDouble("last_update_datetime")));
			db.setMessage(rs.getString("message"));
			db.setKey(rs.getString("key"));
			db.setScoreType(rs.getString("score_type"));
			db.setBpm(CommonUtils.ratingformat(rs.getByte("bpm")));
			db.setImageFileName(rs.getString("image_file_name"));
			db.setImageFileHeight(rs.getInt("image_file_height"));
			db.setImageFileWidth(rs.getInt("image_file_width"));
			db.setOtherLinkUrl(rs.getString("other_link_url"));
			db.setOtherLinkDescription(rs.getString("other_link_description"));
			db.setNickname1(rs.getString("nickname"));
			db.setUniqueCode1(rs.getString("unique_code"));

			SongList.add(db);
		}

		//（9）SongListに追加したデータをsetAttributeする。この時、listの中身に何も入っていない（idに該当するデータが何もなかった）場合、404.jspにフォワーディングし、これ以降の処理は一切行わない。

		if(SongList.size() <= 0) {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}else{
			request.setAttribute("SongList", SongList);
		}

		//（10）SongSqlを使い、引数で渡されたconn.prepareStatement()メソッドを実行する。
		pstmt = con.prepareStatement(CommentSql);

		//(11)Sqlの？に（3）で値を代入したidをセットして実行する
		pstmt.setString(1, id);

		//（12）executeQueryを実行し、結果の「ResultSet」を得る。
		rs = pstmt.executeQuery();

		//(13)クラスDataBox（他シートに記載）、クラスCommonFormat（共通設計に記載）のインスタンスを作成。
		DataBox db2 = new DataBox();
		List<DataBox>CommentList = new ArrayList<DataBox>();		

		//(13)while文を通してCommentListに追加。
		while(rs.next()) {
			
			db2.setComment(rs.getString("comment"));
			db2.setSequence(rs.getInt("sequence"));
			db2.setComposerId(CommonUtils.idformat(rs.getLong("composer_id")));
			db2.setType(rs.getString("type"));
			db2.setToCommentId(CommonUtils.idformat(rs.getLong("to_comment_id")));
			db2.setWriteDatetime(CommonUtils.epoch(rs.getDouble("write_datetime")));
			db2.setRating(CommonUtils.ratingformat(rs.getByte("rating")));
			db2.setNickname2(rs.getString("nickname"));
			db2.setUniqueCode2(rs.getString("unique_code2"));
			
			CommentList.add(db2);
		}







































	}
}
