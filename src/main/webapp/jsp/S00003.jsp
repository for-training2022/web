<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, jp.excd.servlet.DataBox"%>
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
	List<DataBox> SongList = new ArrayList<DataBox>();
	SongList = (List<DataBox>) request.getAttribute("SongList");

	DataBox db = new DataBox();
	out.println(db.getTitle());
	%>
</title>
<link rel="stylesheet" href="../css/main.css" />
<script src="../js/jquery-3.3.0.min.js"></script>
<script src="../js/util.js"></script>
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
			<p class="page_title"><%=db.getTitle()%></p>
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
					<td class="value"><%=db.getTitle()%></td>
				</tr>
			</table>
		</div>

		<!-- 作者へのリンク -->
		<div class="label_and_link">
			<span class="label">作者：</span><span class="link"> <a
				href="http://localhost:8080/web/jsp/S00004/<%=db.getUniqueCode1()%>"><%=db.getNickname1()%></a></span>
		</div>

		<!-- メッセージ -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">メッセージ</td>
				</tr>
				<tr>
					<td class="value"><%=db.getMessage()%></td>
				</tr>
			</table>
		</div>

		<!-- 曲画像リンク -->
		<div class="song_link">
			<div class="cell">
				<div class="image_base">
					<a href="meloko://?song_id=<%=request.getAttribute("melokoUrl") %>">
						<div class="image song1">
							<img alt=><%=db.getTitle()%>
							src="<%=db.getImageFileName()%>"/> <img alt="play" class="play"
								src="../images/play.png" />
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
						class="value"><%=db.getRatingTotal()%></span> <span class="label">平均感動指数：</span><span
						class="value"><%=db.getRatingAverage()%></span> <span
						class="label">再生回数：</span> <span class="value"><%=db.getTotalListenCount()%></span>
						<span class="label">公開：</span><span class="value"><%=db.getReleaseDatetime()%></span>
						<span class="label">最終更新日：</span> <span class="value"><%=db.getLastUpdateDatetime()%></span>
						<span class="label">KEY：</span> <span class="value"><%=db.getKey()%></span>
						<span class="label">楽譜表記：</span> <span class="value"><%=db.getScoreType()%></span>
						<span class="label">BPM：</span> <span class="value"><%=db.getBpm()%></span></td>
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
					<td class="value"><a href="<%=db.getOtherLinkUrl()%>"><%=db.getOtherLinkDescription()%></a></td>
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
				<%List<DataBox> CommentList = new ArrayList<DataBox>();
			CommentList = (List<DataBox>) request.getAttribute("CommentList");

			for (DataBox db2 : CommentList) {
			%>

				<li>
					<div class="normal">
						<div class="rating star45"></div>
						<div class="composer_link">
							<a
								href="http://localhost:8080/web/jsp/S00004/<%=db2.getUniqueCode2()%>"><%=db2.getNickname2()%></a>
						</div>
						<p class="comment"><%=db2.getComment()%></p>
						<p class="time"><%=db2.getWriteDatetime()%></p>
					</div> <%
			}
			%>
				</li>
			</ul>

		</div>





		<!-- ページトップへjavaScript -->
		<div id="pagetop" hidden>
			<img alt="ページトップ" src="../images/pagetop.png">
		</div>

		<!-- フッター -->
		<footer>
			Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All
			Rights Reserved.
		</footer>

	</div>
</body>
</html>