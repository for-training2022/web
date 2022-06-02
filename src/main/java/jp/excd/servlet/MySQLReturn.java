package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class MySQLReturn extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	 final String BASE_SQL="select * from composer";
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

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
			
			final String URL = "jdbc:mysql://"
					+ hostName
					+ ":3306/"
					+ dbName
					+ "?serverTimezone="
					+ timeZone
					+ "&allowPublicKeyRetrieval=true"
					+ "&useSSL=false";
			
			request.setCharacterEncoding("utf-8");
			List<PersonRecord> persons= new ArrayList<PersonRecord>();
			String sql=null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = DriverManager.getConnection(URL, connectUserName, connectPassword);
				
				sql=BASE_SQL;
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					PersonRecord p =new PersonRecord();
					p.setNickname(rs.getString("nickname"));
					p.setUnique_code(rs.getString("unique_code"));
					p.setJoined_date_formated(rs.getString("joined_date"));
					p.setGender(rs.getString("gender"));
					p.setBirthday_formated(rs.getString("birthday"));
					p.setListener_count(rs.getLong("listener_count"));
					p.setLanguage_type(rs.getString("language_type"));
					persons.add(p);
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			
			
			request.setAttribute("list", persons);
			
			getServletConfig().getServletContext().			
			getRequestDispatcher("/jsp/S00008.jsp" ).			
			forward( request, response );
				
	 }
}
