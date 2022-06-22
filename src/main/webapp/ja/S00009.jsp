<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アプリダウンロード</title>
<link rel="stylesheet" href="/web/css/main.css">
<script src="/web/js/jquery-3.3.0.min.js" ></script>
<script type="text/javascript" src="/web/js/util.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
<meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
</head>
<body>
	<!--全体のラッパー-->
	<div class="wrapper">
	
		<!-- タイトルバー -->
    	<div class="title_bar">
			<p class="page_title">アプリダウンロード</p>
			<%@ include file = "Menuin.jsp" %>
		</div>
	
		<!-- 内部に書かれる文字にdivタグ設定。以下内部情報 -->
		<div class = "comment">
			<p>当コミュニティーの参加には、専用アプリ「メロコ」が必要です。<br>
			専用アプリ「メロコ」をお使いになれば、曲の視聴コメントなどを行うことができます。</p>
			
		<!-- メロコのリンクアイコンのdiv -->
		<div class = "melokolink">
			<a href = "https://itunes.apple.com/jp/app/id1440134774?mt=8" target="_blank" rel="noopener">
			<img alt = "メロコリンクアイコン" src ="../images/melokoIcon.png"><br>
			</a>
		</div>
		
		<!-- appstoreのリンクアイコンのdiv -->
		<div class = "appstorelink">
			<a href = "https://itunes.apple.com/jp/app/id1440134774?mt=8" target="_blank" rel="noopener">
			<img alt = "appstoreリンクアイコン" src = "../images/applestore.png">
			</a>
		</div>
		
		<!-- ページトップへjavaScript -->
		<div id="pagetop">
			<img alt="ページトップ" src="../images/pagetop.png">
		</div>
		<!-- commentクラスの終了-->
		</div>
	
		<!-- フッター -->
		<footer>
			<small>Copyright <a href="https://www.excd.jp/" target="_blank" rel="noopener">&copy; EXCEED Co., Ltd.</a> All Rights Reserved.</small>
		</footer>
	</div>
</body>
</html>