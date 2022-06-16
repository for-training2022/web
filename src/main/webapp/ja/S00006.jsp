<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.excd.bean.SongRecord"%>
<!DOCTYPE html>
<html>
<%
List<SongRecord> SongList = (List<SongRecord>) request.getAttribute("list");
%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="keywords"
	content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
<meta name="description"
	content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
<title>作品検索</title>
<link rel="stylesheet" type="text/css" href="/web/css/main.css" />
<script src="/web/js/jquery-3.3.0.min.js"></script>
<script src="/web/js/util.js"></script>
</head>

<body>
	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>


	<div class="wrapper">

		<!-- タイトルバー -->
		<div class="title_bar">
			<p class="page_title">作品検索</p>
			<a href="javascript:formBack.submit()" class="back">&lt;&nbsp;戻る</a>
			<a href="#" id="menu_open"> <img alt="メニュー"
				src="/web/images/menu.png" class="menu-icon" />
			</a>
		</div>

		<!-- メニューの起点 -->
		<div id="menu_marker"></div>

		<!-- 検索結果表示 -->
		<div class="message_with_right_button">
			<p><%=request.getAttribute("hits")%></p>
			<div class="right_button">
				<a href="javascript:formChange.submit()">条件変更</a>
			</div>
		</div>

		<!-- 作品テーブル -->
		<div class="song_list">
			<ul>
				<%
				for (SongRecord record : SongList) {
				%>
				<li>
					<div class="cell">
						<div class="song_title"><%=record.getTitle()%></div>
						<div class="image_base">
							<a href="/web/ja/S00003/<%=record.getSong_id()%>">
								<div class="image song1">
									<img alt="" src="/web/images/<%=record.getImage_file_name()%>" />
									<img alt="" class="play" src="/web/images/play.png" />
								</div>
							</a>
						</div>

						<div class="detail">
							<span class="label_top">総感動指数：</span> <span class="value"><%=record.getRating_total()%></span>
							<span class="label">平均感動指数：</span> <span class="value"><%=record.getRating_average()%></span>
							<span class="label">再生回数：</span> <span class="value"><%=record.getTotal_listen_count()%></span>
							<span class="label">公開：</span> <span class="value"><%=record.getRelease_datetime()%></span>
						</div>
					</div>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</div>


	<!-- メインボタン -->
	<div class="main_button">
		<a href="javascript:formChange.submit()">条件変更</a>
	</div>


	<form id="formBack" method="POST" action="/web/ja/S00006/S00006.back">
			<input name="release_date_radio" type="hidden" value="<%=request.getAttribute("release_date_radio")%>">
			<input name="release_date_from" type="hidden" value="<%=request.getAttribute("release_date_from")%>">
			<input name="release_date_to" type="hidden" value="<%=request.getAttribute("release_date_to")%>">
			<input name="rating_radio" type="hidden" value="<%=request.getAttribute("rating_radio")%>">
			<input name="rating_from" type="hidden" value="<%=request.getAttribute("rating_from")%>">
			<input name="rating_to" type="hidden" value="<%=request.getAttribute("rating_to")%>">
			<input name="rating_average_radio" type="hidden" value="<%=request.getAttribute("rating_average_radio")%>">
			<input name="rating_average_from" type="hidden" value="<%=request.getAttribute("rating_average_from")%>">
			<input name="rating_average_to" type="hidden" value="<%=request.getAttribute("rating_average_to")%>">
			<input name="views_radio" type="hidden" value="<%=request.getAttribute("views_radio")%>">
			<input name="views_from" type="hidden" value="<%=request.getAttribute("views_from")%>">
			<input name="views_to" type="hidden" value="<%=request.getAttribute("views_to")%>">
			<input name="title_radio" type="hidden" value="<%=request.getAttribute("title_radio")%>">
			<input name="title_type_radio" type="hidden" value="<%=request.getAttribute("title_type_radio")%>">
			<input name="title" type="hidden" value="<%=request.getAttribute("title")%>">
			<input name="sort_order" type="hidden" value="<%=request.getAttribute("sort_order")%>">
	</form>
	<form id="formChange" method="POST" action="/web/ja/S00006/S00006.change">
			<input name="release_date_radio" type="hidden" value="<%=request.getAttribute("release_date_radio")%>">
			<input name="release_date_from" type="hidden" value="<%=request.getAttribute("release_date_from")%>">
			<input name="release_date_to" type="hidden" value="<%=request.getAttribute("release_date_to")%>">
			<input name="rating_radio" type="hidden" value="<%=request.getAttribute("rating_radio")%>">
			<input name="rating_from" type="hidden" value="<%=request.getAttribute("rating_from")%>">
			<input name="rating_to" type="hidden" value="<%=request.getAttribute("rating_to")%>">
			<input name="rating_average_radio" type="hidden" value="<%=request.getAttribute("rating_average_radio")%>">
			<input name="rating_average_from" type="hidden" value="<%=request.getAttribute("rating_average_from")%>">
			<input name="rating_average_to" type="hidden" value="<%=request.getAttribute("rating_average_to")%>">
			<input name="views_radio" type="hidden" value="<%=request.getAttribute("views_radio")%>">
			<input name="views_from" type="hidden" value="<%=request.getAttribute("views_from")%>">
			<input name="views_to" type="hidden" value="<%=request.getAttribute("views_to")%>">
			<input name="title_radio" type="hidden" value="<%=request.getAttribute("title_radio")%>">
			<input name="title_type_radio" type="hidden" value="<%=request.getAttribute("title_type_radio")%>">
			<input name="title" type="hidden" value="<%=request.getAttribute("title")%>">
			<input name="sort_order" type="hidden" value="<%=request.getAttribute("sort_order")%>">
	</form>

	<!-- ページトップへjavaScript -->
	<div id="pagetop" hidden>
		<img alt="ページトップ" src="/web/images/pagetop.png">
	</div>

	<!-- フッター -->
	<footer>
		Copyright <a href="https://www.excd.jp/top"> &copy; EXCEED
			Co.,ltd.</a> All Rights Reserved.
	</footer>

</body>
</html>
