<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, jp.excd.servlet.CommentBean,jp.excd.servlet.SongBean"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="keywords"
	content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
<meta name="description"
	content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">



<title>
	<%
	SongBean songBean = null;
	songBean = (SongBean)request.getAttribute("songBean");
	out.println(songBean.getTitle());
	%>
</title>
<link rel="stylesheet" href="/web/css/main.css" />
<script src="/web/js/jquery-3.3.0.min.js"></script>
<script src="/web/js/util.js"></script>
<!-- 画像の圧縮表示設定 -->
<style>
div.song_link div.cell div.song1 img {
	position: relative;
	left: 0px;
	top: -11px;
	width: 275px;
	height: 182px;
}
</style>
</head>
<body>
	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

	<div class="wrapper">

		<!-- タイトルバー -->
		<div class="title_bar">
			<p class="page_title"><%=songBean.getTitle()%></p>
			<a href="#" id="menu_open"> <img alt="メニュー"
				src="/images/menu.png" class="menu-icon" />
			</a>
		</div>

		<!-- メニューの起点 -->
		<div id="menu_marker"></div>

		<!-- 曲名 -->
		<div class="double_rows_table">
			<table>
				<tr>
					<td class="label">曲名</td>
					<td class="value"><%=songBean.getTitle()%></td>
				</tr>
			</table>
		</div>

		<!-- 作者へのリンク -->
		<div class="label_and_link">
			<span class="label">作者：</span><span class="link"> <a
				href="http://localhost:8080/web/jsp/S00004/<%=songBean.getUniqueCode1()%>"><%=songBean.getNickname1()%></a></span>
		</div>

		<!-- メッセージ -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">メッセージ</td>
				</tr>
				<tr>
					<td class="value"><%=songBean.getMessage()%></td>
				</tr>
			</table>
		</div>

		<!-- 曲画像リンク -->
		<div class="song_link">
			<div class="cell">
				<div class="image_base">
					<a href="<%=request.getAttribute("melokoUrl")%>">
						<div class="image">
							<img alt="<%=songBean.getTitle()%>"
								src="/web/images/<%=songBean.getImageFileName()%>"
								width="<%=songBean.getImageFileWidth()%>"
								height="<%=songBean.getImageFileHeight()%>"> <img
								alt="play" class="play" src="/web/images/play.png">
						</div>
					</a>
				</div>
			</div>
		</div>

		<!-- 情報 -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">情報</td>
				</tr>
				<tr>
					<td class="value"><span class="label_top">総感動指数：</span> <span
						class="value"><%=request.getAttribute("rating_total")%></span> <span
						class="label">平均感動指数：</span><span class="value"><%=request.getAttribute("rating_average")%></span>
						<span class="label">再生回数：</span> <span class="value"><%=songBean.getTotalListenCount()%></span>
						<span class="label">公開：</span><span class="value"><%=songBean.getReleaseDatetime()%></span>
						<span class="label">最終更新日：</span> <span class="value"><%=songBean.getLastUpdateDatetime()%></span>
						<span class="label">KEY：</span> <span class="value"><%=songBean.getKey()%></span>
						<span class="label">楽譜表記：</span> <span class="value"><%=songBean.getScoreType()%></span>
						<span class="label">BPM：</span> <span class="value"><%=songBean.getBpm()%></span></td>
				</tr>
			</table>
		</div>

		<!-- 関連リンク -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">関連リンク</td>
				</tr>
				<tr>
					<td class="value"><a href="<%=songBean.getOtherLinkUrl()%>"><%=songBean.getOtherLinkDescription()%></a></td>
				</tr>
			</table>
		</div>

		<!-- コメントテーブルのヘッダー -->
		<div class="sub_header">
			<p>この曲についたコメント</p>
		</div>

		<!-- コメントテーブル -->
		<div class="comments">
			<ul>
				<%
				List<CommentBean> commentList = new ArrayList<CommentBean>();
				commentList = (List<CommentBean>) request.getAttribute("commentList");
				for (CommentBean comBean : commentList) {
				%>
				<li>
					<%
					if ("0".equals(comBean.getType())) {
					%>
					<div class="normal">
						<div class="<%=comBean.getRating()%>"></div>
						<div class="composer_link">
							<a
								href="http://localhost:8080/web/ja/S00004/<%=comBean.getUniqueCode2()%>"><%=comBean.getNickname2()%></a>
						</div>
						<p class="comment"><%=comBean.getComment()%></p>
						<p class="time"><%=comBean.getWriteDatetime()%></p>
					</div> <%
 } else if ("1".equals(comBean.getType())) {
 %>
					<div class="reply">
						<div class="grater_than">&gt;</div>
						<div class="<%=comBean.getRating()%>"></div>
						<div class="composer_link">
							<a
								href="http://localhost:8080/web/ja/S00004/<%=comBean.getUniqueCode2()%>"><%=comBean.getNickname2()%></a>
						</div>
						<p class="comment"><%=comBean.getComment()%></p>
						<p class="time"><%=comBean.getWriteDatetime()%></p>
					</div> <%
 }
 %>
				</li>
				<%
				}
				%>
			</ul>
		</div>
		<!-- ページトップへjavaScript -->
		<div id="pagetop" hidden>
			<img alt="ページトップ" src="web/images/pagetop.png">
		</div>

		<!-- フッター -->
		<footer>
			Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All
			Rights Reserved.
		</footer>

	</div>
</body>
</html>