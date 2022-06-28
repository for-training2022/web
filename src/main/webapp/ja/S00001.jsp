<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.excd.bean.SongComposerBean" %>
<!DOCTYPE html>
<html>

	<%--fromが無い場合は表示しない(つまり、一件目から表示する) --%>




	
	<%--categoryの値がない場合は「新着」にする。 --%>
	<%--タブがタッチされると同時に、その箇所の色も変える --%>
	<% 
		String val2 = null;
		String choice1 = null;
		String choice2 = null;
		String choice3 = null;
		String choice4 = null;
		String category = (String)request.getAttribute("category");
		if ("1".equals(category)) {
			val2 = "category=1";
			choice1 = "selected";
		} else if("2".equals(category)) {
			val2 = "category=2";
			choice2 = "selected";
		} else if("3".equals(category)) {
			val2 = "category=3";
			choice3 = "selected";
		} else if("4".equals(category)) {
			val2 = "category=4";
			choice4 = "selected";
		} else if(category == null || "".equals(category)) {
			val2 = "category=1";
			choice1 = "selected";
		}
	%>
	
	
<head>
	<meta charset="UTF-8">
	<title>音楽室</title>
	<link rel="stylesheet" href="/web/css/main.css">
	<script src="/web/js/jquery-3.3.0.min.js" ></script>
	<script type="text/javascript" src="/web/js/util.js"></script>
	<meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
	<meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
</head>
<body>

	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker">
	</div>

	<!--全体のラッパー-->
	<div class="wrapper">
	 
	<!-- トップバナー -->
	<div class="top_banner">
	<a href="https://itunes.apple.com/jp/app/id1440134774?mt=8">
			<img alt="メロコ～iPhone用作曲アプリアイコン" src="../images/melokoIcon.png" class="icon" />
			<% String appMessage = (String)request.getAttribute("appMessage"); %>
			<p><%= appMessage %></p>
			<img alt="メロコ～専用アプリダウンロード画面へのリンク" src="../images/right_blue_arrow.png" class="to_download_page_arrow" />
	</a>
	</div>
	
	<!-- タイトルバー -->
	<div class="title_bar">
		<p class="main_title" >音楽室</p>
		<p class="sub_title">～作曲家たちのコミュニティー～</p>
		<%@ include file = "Menuin.jsp" %>
	</div>

	<!-- メニューの起点 -->
	<div id="menu_marker">
	</div>

	<!-- トップタブ -->
	<div class="top_tab">
		<ul>
			<li class="tab1 <%= choice1 %>"><a href="http://localhost:8080/web/ja/S00001?category=1"><div name="category" value="1">新着</div></a></li>
			<li class="tab2 <%= choice2 %>"><a href="http://localhost:8080/web/ja/S00001?category=2"><div name="category" value="2">人気</div></a></li>
			<li class="tab3 <%= choice3 %>"><a href="http://localhost:8080/web/ja/S00001?category=3"><div name="category" value="3">高評価</div></a></li>
			<li class="tab4 <%= choice4 %>"><a href="http://localhost:8080/web/ja/S00001?category=4"><div name="category" value="4">名作</div></a></li>
		</ul>
		
	</div>

	<!-- トップ告知(内容が無い時にはこの領域自体を非表示にする) -->
	<% String notice = (String)request.getAttribute("notice"); %>
	<% if (notice != null && notice != "") { %>
		<div class="top_notice">
			<p class="top_notice_title">告知</p>
			<p class="top_notice_body"><%= notice %></p>
		</div>
	<% } %>
	
	<!-- ソングテーブル -->
	<% List<SongComposerBean> list = (List<SongComposerBean>)request.getAttribute("displayList"); %>
	<div class="song_list">
		<ul>
	
		<%for(SongComposerBean record:list){ %>
			<li>
				<div class="cell">
					<div class="song_title">
						<%= record.getTitle() %>
					</div>
					<div class="composer">
						<span class="label_top">
					
					作曲：</span>
						<span class="composer_link">
						<a href="http://localhost:8080/web/ja/S00004/<%= record.getUniqueCode() %>">
						<%= record.getNickname() %> 
						</a></span>
					</div>
					<div class="image_base">
						<a href="http://localhost:8080/web/ja/S00003/<%= record.getId()%>">
							<div class="image song1">
								<img alt="<%= record.getTitle() %>" src="/web/images/<%= record.getImage_file_name() %>" />
								<img alt= "play" class="play" src="/web/images/play.png" />
							</div>
						</a>
					</div>
					<div class="detail">
						<span class="label_top">総感動指数：</span>
							<span class="value"><%= record.getRating_total() %></span>
							<span class="label">平均感動指数：</span>
							<span class="value"><%= record.getRating_average() %></span>
							<span class="label">再生回数：</span>
							<span class="value"><%= record.getTotal_listen_count() %></span>
							<span class="label">公開：</span>
							<span class="value"><%= record.getRelease_datetime() %></span>
					</div>
				</div>
			</li>
			<%} %>
		</ul>
	</div>

	<!-- メインボタン(さらに読み込むボタン) -->
<%-- 	<% category nextCategory = new category() %> --%>


<%
		int nextFrom = (int)request.getAttribute("nextFrom");
		
	%>

	<% int hit = (int)request.getAttribute("hits"); %>
	<% if(hit > nextFrom){ %> 
	<div class="main_button">
		<a href="http://localhost:8080/web/ja/S00001?<%= val2 %>&from=<%= nextFrom %>">さらに読み込む</a>
	</div>
  <% } %> 
   
	<!-- ページトップへjavaScript -->
	<div id="pagetop">
		<img alt="ページトップ" src="../images/pagetop.png">
	</div>
	
	<!-- フッター -->
		<footer>
			<small>Copyright <a href="https://www.excd.jp/" target="_blank" rel="noopener">&copy; EXCEED Co., Ltd.</a> All Rights Reserved.</small>
		</footer>
	</div>
</body>
</html>