<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.excd.servlet.SongComposerBean" %>
<!DOCTYPE html>
<html>

	<%--fromが無い場合は表示しない(つまり、一件目から表示する) --%>
	<% 
		String val = null;
		int from = (int)request.getAttribute("from");
		if (from == 0 || "".equals(from)) {
			val = "";
		} else {
			val= "from=" + from; 
		} 
	%>
	
	<%--categoryの値がない場合は「新着」にする。 --%>
	<%--タブがタッチされると同時に、その箇所の色も変える --%>
	<% 
		String val2 = null;
		String choice1 = null;
		String choice2 = null;
		String choice3 = null;
		String choice4 = null;
		String category = (String)request.getAttribute("category");
		if (category .equals("1")) {
			val2 = "category=" + category;
			choice1 = "selected";
		} else if(category .equals("2")) {
			val2 = "category=" + category;
			choice2 = "selected";
		} else if(category .equals("3")) {
			val2 = "category=" + category;
			choice3 = "selected";
		} else if(category .equals("4")) {
			val2 = "category=" + category;
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
			<p>作曲アプリ「メロコ」。歌モノに特化したアプリです。このサイトの曲はすべてこのアプリで作成されています。</p>
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
			<li class="tab1 <%= choice1 %>"><a href="http://localhost:8080/web/ja/S00001?<%= val2 %>&<%= val %>">新着</a></li>
			<li class="tab2 <%= choice2 %>"><a href="http://localhost:8080/web/ja/S00001?<%= val2 %>&<%= val %>">人気</a></li>
			<li class="tab3 <%= choice3 %>"><a href="http://localhost:8080/web/ja/S00001?<%= val2 %>&<%= val %>">高評価</a></li>
			<li class="tab4 <%= choice4 %>"><a href="http://localhost:8080/web/ja/S00001?<%= val2 %>&<%= val %>">名作</a></li>
		</ul>
		
	</div>

	<!-- トップ告知(内容が無い時にはこの領域自体を非表示にする) -->
	<% String notice = (String)request.getAttribute("notice"); %>
	<% if (notice != null) { %>
		<div class="top_notice">
			<p class="top_notice_title">告知</p>
			<p class="top_notice_body"><%= notice %></p>
		</div>
	<% } %>
	↑↑↑↑
	<!-- ソングテーブル -->
	<% List<SongComposerBean> list = (List<SongComposerBean>)request.getAttribute("displayList"); %>
	<div class="song_list">
		<ul>
	
		<%for(SongComposerBean record:list){ %>
			<li>
				<div class="cell">
					<div class="song_title">
					</div>
					<div class="composer">
						<span class="label_top">作曲： </span>
						<span class="composer_link"><a href="http://localhost:8080/web/ja/S00004/<%= record.getUniqueCode()%>"></a></span>
					</div>
					<div class="image_base">
						<a href="http://localhost:8080/web/ja/S00003/<%= record.getId()%>">
							<div class="image song1">
								<img alt="<%= record.getTitle() %>" src="<%= record.getImage_file_name() %>" />
								<img alt= "play" class="play" src="<%= record.getImage_file_name() %>" />
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

	<% int hit = (int)request.getAttribute("hits"); %>
<%-- 	<% if(hit > nextFrom){ %> --%>
	<div class="main_button">
		<a href="http://localhost:8080/web/ja/S00001?<%= val2 %>&<%= val %>">さらに読み込む</a>
	</div>
<%--     <% } %> --%>
   
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