<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>音楽室</title>
<link rel="stylesheet" href="/web/css/main.css">
<script src="/web/js/jquery-3.3.0.min.js" ></script>
<script type="text/javascript" src="/web/js/util.js"></script>
  <meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
  <meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>

<style>
div.song_list ul li div.cell div.song1 img {
    position: relative;
    left: 0px;
    top: -11px;
    width :275px;
    height :182px;
}
div.song_list ul li div.cell div.song2 img {
    position: relative;
    left: 0px;
    top: -134.5px;
    width :275px;
    height :429px;
}
div.song_list ul li div.cell div.song3 img {
    position: relative;
    left: 0px;
    top: -30.5px;
    width :275px;
    height :220px;
}
</style>

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
			<li class="tab1 selected"><a href="http://localhost:8080/web/ja/S00001.jsp">新着</a></li>
			<li class="tab2"><a href="http://localhost:8080/web/ja/S00001.jsp">人気</a></li>
			<li class="tab3"><a href="http://localhost:8080/web/ja/S00001.jsp">高評価</a></li>
			<li class="tab4"><a href="http://localhost:8080/web/ja/S00001.jsp">名作</a></li>
		</ul>
	</div>

	<!-- トップ告知 -->
	
	<div class="top_notice" >
		<p class="top_notice_title">告知</p>
		<p class="top_notice_body">
		2019年5月25日～2019年5月28日メンテナンスのため、当サービスを停止します。
		ご不便をおかけしますが、何卒、ご理解のほどよろしくお願いします。
		</p>
	</div>

		

	<!-- メインボタン -->
	<div class="main_button">
		<a href="http://localhost:8080/web/ja/S00001.jsp">さらに読み込む</a>
	</div>

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