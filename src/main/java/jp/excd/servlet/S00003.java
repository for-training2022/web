package jp.excd.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
			con = MySQLSetting.getConnection("meloko","meloko","exceed","Asia/Tokyo");
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

		//受け取ったURIをrequestURI()メソッドで取得し、requestUri変数で受け取る。
		String requestUri= request.getRequestURI();

		//（1）spilit()を使い、取得したuriを分割。。
		String[] uri = requestUri.split("/");

		//(2)接続URLが「/ja/S00003/*」以外の場合は、404.jspへフォワーディングする
		String urlCheck = "/" + uri[1] + "/" + uri[2] + "/" +uri[3] + "/";
		if ("/web/ja/S00003/".equals(urlCheck)) {
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}

		//(3)url内の曲id/song_idを取得し、変数idに代入する。

		String id = uri[4];


		//(4)SQL文の設定
		// 曲＋作曲者
		String songSql = "select"
				+ "    song.title"
				+ "    , song.composer_id"
				+ "    , song.total_listen_count"
				+ "    , song.release_datetime"
				+ "    , song.last_update_datetime"
				+ "    , song.message"
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
				+ "    song.id = ?";

		// コメント＋評価
		String commentSql = "select "
				+ "comment.id "
				+ ", comment.song_id"
				+ ", comment.sequence"
				+ ", comment.composer_id"
				+ ", comment.comment"
				+ ", comment.type"
				+ ", comment.to_comment_id"
				+ ", comment.write_datetime"
				+ ", rating.rating"
				+ ", composer.unique_code"
				+ ", composer.nickname "
				+ "from "
				+ "((rating left join comment "
				+ "on rating.composer_id = comment.composer_id "
				+ "and rating.song_id = comment.song_id) "
				+ "left join composer "
				+ "on rating.composer_id = composer.id) "
				+ "where comment.song_id = ? "
				+ "and comment.comment is not null "
				+ "and comment.type = ?"
				+ "order by comment.sequence asc";

		String ratingSql = "select "
				+ "sum(rating) as rating_total "
				+ ", avg(rating) as rating_average "
				+ "from rating  "
				+ "where song_id = ?"; 

		//（5）songSqlを使い、引数で渡されたconn.prepareStatement()メソッドを実行する。
		pstmt = con.prepareStatement(songSql);

		//(6)Sqlの？に（3）で値を代入したidをセットして実行する
		pstmt.setString(1, id);


		//（7）executeQueryを実行し、結果の「ResultSet」を得る。
		rs = pstmt.executeQuery();

		//(8)クラスDataBox（他シートに記載）、クラスCommonFormat（共通設計に記載）のインスタンスを作成。
		SongBean songBean = new SongBean();
		boolean noRecordflg = false;


		//(8)while文を通してSongListに追加。
		while(rs.next()) {
			songBean.setTitle(rs.getString("title"));
			songBean.setTotalListenCount(CommonUtils.valueformat(rs.getLong("total_listen_count")));
			songBean.setRatingTotal(CommonUtils.valueformat(rs.getLong("composer_id")));
			songBean.setReleaseDatetime(CommonUtils.epoch(rs.getDouble("release_datetime")));
			songBean.setLastUpdateDatetime(CommonUtils.epoch(rs.getDouble("last_update_datetime")));
			songBean.setMessage(rs.getString("message"));
			songBean.setKey(CommonUtils.keyName(rs.getString("key")));
			songBean.setScoreType(CommonUtils.scoreChange(rs.getString("score_type")));
			songBean.setBpm(rs.getString("bpm"));
			songBean.setImageFileName(rs.getString("image_file_name"));
			songBean.setImageFileHeight(rs.getInt("image_file_height"));
			songBean.setImageFileWidth(rs.getInt("image_file_width"));
			songBean.setOtherLinkUrl(rs.getString("other_link_url"));
			songBean.setOtherLinkDescription(rs.getString("other_link_description"));
			songBean.setNickname1(rs.getString("nickname"));
			songBean.setUniqueCode1(rs.getString("unique_code"));
			noRecordflg = true;
		}

		//（9）dbに追加したデータをsetAttributeする。この時、listの中身に何も入っていない（idに該当するデータが何もなかった）場合、404.jspにフォワーディングし、これ以降の処理は一切行わない。

		if(!noRecordflg) {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}else{
			request.setAttribute("songBean", songBean);
		}

		//（10）CommentSqlを使い、引数で渡されたconn.prepareStatement()メソッドを実行する。
		pstmt = con.prepareStatement(commentSql);

		//(11)Sqlの？に（3）で値を代入したidをセットして実行する
		pstmt.setString(1, id);
		pstmt.setString(2, "0");

		//（12）executeQueryを実行し、結果の「ResultSet」を得る。
		rs = pstmt.executeQuery();

		//(13)linkedhashmapを宣言する。
		LinkedHashMap<String,ParentBean> parentMap = new LinkedHashMap<String, ParentBean>();

		//(13)while文を通してlinkedhashmapにコメントタイプ0のデータを入力する。
		while(rs.next()) {
			ParentBean parBean = new ParentBean();
			parBean.setComment(rs.getString("comment"));
			parBean.setComposerId(CommonUtils.idformat(rs.getLong("composer_id")));
			parBean.setType(rs.getString("type"));
			parBean.setWriteDatetime(CommonUtils.epoch(rs.getDouble("write_datetime")));
			parBean.setRating(CommonUtils.starformat(CommonUtils.ratingformat(rs.getByte("rating"))));
			parBean.setNickname2(rs.getString("nickname"));
			parBean.setUniqueCode2(rs.getString("unique_code"));
			String commentId = rs.getString("id");
			parentMap.put(commentId , parBean);
		}

		//(14)続いてコメントタイプ1（返信コメントのデータ）をSql文を使って取得する。手順は（10）～（12）と同様だが、二つ目の?には1を充てる。
		pstmt = con.prepareStatement(commentSql);
		pstmt.setString(1, id);
		pstmt.setString(2, "1");
		rs = pstmt.executeQuery();

		//(15)(13)でlinkedhashmapに追加したデータの中で、今回取得したtoCommentIdの値が等しいキーがある場合、そのデータのlistにcommentBeanをaddする。
		while(rs.next()) {
			CommentBean comBean = new CommentBean();
			comBean.setComment(rs.getString("comment"));
			comBean.setComposerId(CommonUtils.idformat(rs.getLong("composer_id")));
			comBean.setType(rs.getString("type"));
			comBean.setWriteDatetime(CommonUtils.epoch(rs.getDouble("write_datetime")));
			comBean.setRating(CommonUtils.starformat(CommonUtils.ratingformat(rs.getByte("rating"))));
			comBean.setNickname2(rs.getString("nickname"));
			comBean.setUniqueCode2(rs.getString("unique_code"));
			String toCommentId =CommonUtils.idformat(rs.getLong("to_comment_id"));
			ParentBean data = parentMap.get(toCommentId);
			data.getreplyComment().add(comBean);
		}

		List<CommentBean> commentList = new ArrayList<CommentBean>();
		for (Iterator<ParentBean> itr = parentMap.values().iterator(); itr.hasNext();) {
			ParentBean pb = itr.next();
			CommentBean cb = new CommentBean();
			cb.setComment(pb.getComment());
			cb.setSequence(pb.getSequence());
			cb.setType(pb.getType());
			cb.setToCommentId(pb.getToCommentId());
			cb.setWriteDatetime(pb.getWriteDatetime());
			cb.setNickname2(pb.getNickname2());
			cb.setUniqueCode2(pb.getUniqueCode2());
			cb.setRating(pb.getRating());
			commentList.add(cb);
			if (0 < pb.getreplyComment().size()) {
				for( CommentBean replycb : pb.getreplyComment() ) {
					commentList.add(replycb);
				}
			}
		}

		//（14）以上でCommentListに追加したデータをsetAttributeする。
		request.setAttribute("commentList", commentList);
		
		//（15）jsp画像が押下された際にmelokoを起動するURLを作成する。
		String melokoUrl = "meloko://?song_id=".concat(id);

		//（16）（15）で設定したmelokoUrlをsetAttributeする。
		request.setAttribute("melokoUrl", melokoUrl);

		//（17）RatingSqlを使い、引数で渡されたcon.prepareStatement()メソッドを実行する。
		pstmt = con.prepareStatement(ratingSql);

		//(18)Sqlの？に（3）で値を代入したidをセットして実行する
		pstmt.setString(1, id);

		//（19）executeQueryを実行し、結果の「ResultSet」を得る。
		rs = pstmt.executeQuery();

		//(20)RatingSqlでのデータを受け取る変数を宣言する。
		String ratingAverage = null;
		String ratingTotal= null;


		//(21)while文を通してそれぞれの値を（20）で宣言した変数にに追加。
		while(rs.next()) {
			ratingAverage = CommonUtils.averageformat(rs.getDouble("rating_average"));
			ratingTotal = CommonUtils.valueformat(rs.getLong("rating_total"));
		}

		//（22）取得した値（変数）をsetAttributeする。
		request.setAttribute("rating_average", ratingAverage);
		request.setAttribute("rating_total", ratingTotal);

		//（23）S00003にフォワーディングする。
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/S00003.jsp" ).forward( request, response );


	}
}
