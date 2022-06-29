<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ja">
<%
// (1) 「エラー情報(error)」が設定されている場合は、画面に「エラー情報(error)」を表示する。
String errorMessage = (String) request.getAttribute("error");
if (errorMessage == null)
	errorMessage = "";
// (2) 「公開日_エラー状態(release_date_is_error)」= "1"の場合
String release_date_is_error = "";
if ("1".equals(request.getAttribute("release_date_is_error"))) {
	release_date_is_error = ", error";
}
// (3) 以下の項目を元に公開日の入力状態を再現する。
String release_date_radio1 = "";
String release_date_radio2 = "";
if ("1".equals(request.getAttribute("release_date_radio"))) {
	release_date_radio1 = "checked=\"checked\"";
} else {
	release_date_radio2 = "checked=\"checked\"";
}
String release_date_from = (String) request.getAttribute("release_date_from");
String release_date_to = (String) request.getAttribute("release_date_to");
if (release_date_from == null) {
	release_date_from = "";
}
if (release_date_to == null) {
	release_date_to = "";
}
// (4) 「感動指数_エラー状態(rating_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
String rating_is_error = "";
if ("1".equals(request.getAttribute("rating_is_error"))) {
	rating_is_error = ", error";
}
// (5) 以下の項目を元に感動指数の入力状態を再現する。
String rating_radio1 = "";
String rating_radio2 = "";
if ("1".equals(request.getAttribute("rating_radio"))) {
	rating_radio1 = "checked=\"checked\"";
} else {
	rating_radio2 = "checked=\"checked\"";
}
String rating_from = (String) request.getAttribute("rating_from");
if (rating_from == null)
	rating_from = "";
String rating_to = (String) request.getAttribute("rating_to");
if (rating_to == null)
	rating_to = "";
// (6) 「平均感動指数_エラー状態(rating_average_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
String rating_average_is_error = "";
if ("1".equals(request.getAttribute("rating_average_is_error"))) {
	rating_average_is_error = ", error";
}
// (7) 以下の項目を元に平均感動指数の入力状態を再現する。
String rating_average_radio1 = "";
String rating_average_radio2 = "";
if ("1".equals(request.getAttribute("rating_average_radio"))) {
	rating_average_radio1 = "checked=\"checked\"";
} else {
	rating_average_radio2 = "checked=\"checked\"";
}
String rating_average_from1 = null;
String rating_average_from2 = null;
String rating_average_from3 = null;
String rating_average_from4 = null;
String rating_average_from5 = null;
String rating_average_from6 = null;
String rating_average_from7 = null;
String rating_average_from8 = null;
String rating_average_from9 = null;
String rating_average_from10 = null;
String rating_average_from11 = null;
String rating_average_from12 = null;
String rating_average_from13 = null;
String rating_average_from14 = null;
String rating_average_from15 = null;
String rating_average_from16 = null;
String rating_average_from17 = null;
String rating_average_from18 = null;
String rating_average_from19 = null;
String rating_average_from20 = null;
String rating_average_from21 = null;
String rating_average_from22 = null;
String rating_average_from23 = null;
String rating_average_from24 = null;
String rating_average_from25 = null;
String rating_average_from26 = null;
String rating_average_from27 = null;
String rating_average_from28 = null;
String rating_average_from29 = null;
String rating_average_from30 = null;
String rating_average_from31 = null;
String rating_average_from32 = null;
String rating_average_from33 = null;
String rating_average_from34 = null;
String rating_average_from35 = null;
String rating_average_from36 = null;
String rating_average_from37 = null;
String rating_average_from38 = null;
String rating_average_from39 = null;
String rating_average_from40 = null;
String rating_average_from41 = null;
if ("1.0".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from1 = "selected=\"selected\"";
} else if ("1.1".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from2 = "selected=\"selected\"";
} else if ("1.2".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from3 = "selected=\"selected\"";
} else if ("13".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from4 = "selected=\"selected\"";
} else if ("1.4".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from5 = "selected=\"selected\"";
} else if ("1.5".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from6 = "selected=\"selected\"";
} else if ("1.6".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from7 = "selected=\"selected\"";
} else if ("1.7".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from8 = "selected=\"selected\"";
} else if ("1.8".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from9 = "selected=\"selected\"";
} else if ("1.9".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from10 = "selected=\"selected\"";
} else if ("2.0".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from11 = "selected=\"selected\"";
} else if ("2.1".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from12 = "selected=\"selected\"";
} else if ("2.2".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from13 = "selected=\"selected\"";
} else if ("2.3".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from14 = "selected=\"selected\"";
} else if ("2.4".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from15 = "selected=\"selected\"";
} else if ("2.5".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from16 = "selected=\"selected\"";
} else if ("2.6".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from17 = "selected=\"selected\"";
} else if ("2.7".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from18 = "selected=\"selected\"";
} else if ("2.8".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from19 = "selected=\"selected\"";
} else if ("2.9".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from20 = "selected=\"selected\"";
} else if ("3.0".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from21 = "selected=\"selected\"";
} else if ("3.1".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from22 = "selected=\"selected\"";
} else if ("3.2".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from23 = "selected=\"selected\"";
} else if ("3.3".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from24 = "selected=\"selected\"";
} else if ("3.4".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from25 = "selected=\"selected\"";
} else if ("3.5".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from26 = "selected=\"selected\"";
} else if ("3.6".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from27 = "selected=\"selected\"";
} else if ("3.7".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from28 = "selected=\"selected\"";
} else if ("3.8".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from29 = "selected=\"selected\"";
} else if ("3.9".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from30 = "selected=\"selected\"";
} else if ("4.0".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from31 = "selected=\"selected\"";
} else if ("4.1".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from32 = "selected=\"selected\"";
} else if ("4.2".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from33 = "selected=\"selected\"";
} else if ("4.3".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from34 = "selected=\"selected\"";
} else if ("4.4".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from35 = "selected=\"selected\"";
} else if ("4.5".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from36 = "selected=\"selected\"";
} else if ("4.6".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from37 = "selected=\"selected\"";
} else if ("4.7".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from38 = "selected=\"selected\"";
} else if ("4.8".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from39 = "selected=\"selected\"";
} else if ("4.9".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from40 = "selected=\"selected\"";
} else if ("5.0".equals(request.getAttribute("rating_average_from"))) {
	rating_average_from41 = "selected=\"selected\"";
} 
String rating_average_to1 = null;
String rating_average_to2 = null;
String rating_average_to3 = null;
String rating_average_to4 = null;
String rating_average_to5 = null;
String rating_average_to6 = null;
String rating_average_to7 = null;
String rating_average_to8 = null;
String rating_average_to9 = null;
String rating_average_to10 = null;
String rating_average_to11 = null;
String rating_average_to12 = null;
String rating_average_to13 = null;
String rating_average_to14 = null;
String rating_average_to15 = null;
String rating_average_to16 = null;
String rating_average_to17 = null;
String rating_average_to18 = null;
String rating_average_to19 = null;
String rating_average_to20 = null;
String rating_average_to21 = null;
String rating_average_to22 = null;
String rating_average_to23 = null;
String rating_average_to24 = null;
String rating_average_to25 = null;
String rating_average_to26 = null;
String rating_average_to27 = null;
String rating_average_to28 = null;
String rating_average_to29 = null;
String rating_average_to30 = null;
String rating_average_to31 = null;
String rating_average_to32 = null;
String rating_average_to33 = null;
String rating_average_to34 = null;
String rating_average_to35 = null;
String rating_average_to36 = null;
String rating_average_to37 = null;
String rating_average_to38 = null;
String rating_average_to39 = null;
String rating_average_to40 = null;
String rating_average_to41 = null;
if ("1.0".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to1 = "selected=\"selected\"";
} else if ("1.1".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to2 = "selected=\"selected\"";
} else if ("1.2".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to3 = "selected=\"selected\"";
} else if ("13".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to4 = "selected=\"selected\"";
} else if ("1.4".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to5 = "selected=\"selected\"";
} else if ("1.5".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to6 = "selected=\"selected\"";
} else if ("1.6".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to7 = "selected=\"selected\"";
} else if ("1.7".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to8 = "selected=\"selected\"";
} else if ("1.8".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to9 = "selected=\"selected\"";
} else if ("1.9".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to10 = "selected=\"selected\"";
} else if ("2.0".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to11 = "selected=\"selected\"";
} else if ("2.1".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to12 = "selected=\"selected\"";
} else if ("2.2".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to13 = "selected=\"selected\"";
} else if ("2.3".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to14 = "selected=\"selected\"";
} else if ("2.4".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to15 = "selected=\"selected\"";
} else if ("2.5".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to16 = "selected=\"selected\"";
} else if ("2.6".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to17 = "selected=\"selected\"";
} else if ("2.7".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to18 = "selected=\"selected\"";
} else if ("2.8".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to19 = "selected=\"selected\"";
} else if ("2.9".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to20 = "selected=\"selected\"";
} else if ("3.0".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to21 = "selected=\"selected\"";
} else if ("3.1".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to22 = "selected=\"selected\"";
} else if ("3.2".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to23 = "selected=\"selected\"";
} else if ("3.3".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to24 = "selected=\"selected\"";
} else if ("3.4".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to25 = "selected=\"selected\"";
} else if ("3.5".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to26 = "selected=\"selected\"";
} else if ("3.6".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to27 = "selected=\"selected\"";
} else if ("3.7".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to28 = "selected=\"selected\"";
} else if ("3.8".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to29 = "selected=\"selected\"";
} else if ("3.9".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to30 = "selected=\"selected\"";
} else if ("4.0".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to31 = "selected=\"selected\"";
} else if ("4.1".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to32 = "selected=\"selected\"";
} else if ("4.2".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to33 = "selected=\"selected\"";
} else if ("4.3".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to34 = "selected=\"selected\"";
} else if ("4.4".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to35 = "selected=\"selected\"";
} else if ("4.5".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to36 = "selected=\"selected\"";
} else if ("4.6".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to37 = "selected=\"selected\"";
} else if ("4.7".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to38 = "selected=\"selected\"";
} else if ("4.8".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to39 = "selected=\"selected\"";
} else if ("4.9".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to40 = "selected=\"selected\"";
} else if ("5.0".equals(request.getAttribute("rating_average_to"))) {
	rating_average_to41 = "selected=\"selected\"";
}
// (8) 「再生回数_エラー状態(views_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
String views_is_error = "";
if ("1".equals(request.getAttribute("views_is_error"))) {
	views_is_error = ", error";
}
// (9) 以下の項目を元に再生回数の入力状態を再現する。
String views_radio1 = "";
String views_radio2 = "";
if ("1".equals(request.getAttribute("views_radio"))) {
	views_radio1 = "checked=\"checked\"";
} else {
	views_radio2 = "checked=\"checked\"";
}
String views_from = (String) request.getAttribute("views_from");
if (views_from == null)
	views_from = "";
String views_to = (String) request.getAttribute("views_to");
if (views_to == null)
	views_to = "";
// (10) 「曲名_エラー状態(title_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
String title_is_error = "";
if ("1".equals(request.getAttribute("title_is_error"))) {
	title_is_error = ", error";
}
// (11) 以下の項目を元に曲名の入力状態を再現する。
String title_radio1 = "";
String title_radio2 = "";
if ("1".equals(request.getAttribute("title_radio"))) {
	title_radio1 = "checked=\"checked\"";
} else {
	title_radio2 = "checked=\"checked\"";
}
String title_type_radio1 = "";
String title_type_radio2 = "";
if ("1".equals(request.getAttribute("title_type_radio"))) {
	title_type_radio1 = "checked=\"checked\"";
} else {
	title_type_radio2 = "checked=\"checked\"";
}
String title = (String) request.getAttribute("title");
if (title == null)
	title = "";
// (12) 以下の項目を元に並び順の入力状態を再現する。
String sort_order1 = "";
String sort_order2 = "";
String sort_order3 = "";
String sort_order4 = "";
String sort_order5 = "";
String sort_order6 = "";
String sort_order7 = "";
String sort_order8 = "";
if ("02".equals(request.getAttribute("sort_order"))) {
	sort_order2 = "selected=\"selected\"";
} else if ("03".equals(request.getAttribute("sort_order"))) {
	sort_order3 = "selected=\"selected\"";
} else if ("04".equals(request.getAttribute("sort_order"))) {
	sort_order4 = "selected=\"selected\"";
} else if ("05".equals(request.getAttribute("sort_order"))) {
	sort_order5 = "selected=\"selected\"";
} else if ("06".equals(request.getAttribute("sort_order"))) {
	sort_order6 = "selected=\"selected\"";
} else if ("07".equals(request.getAttribute("sort_order"))) {
	sort_order7 = "selected=\"selected\"";
} else if ("08".equals(request.getAttribute("sort_order"))) {
	sort_order8 = "selected=\"selected\"";
} else {
	sort_order1 = "selected=\"selected\"";
}
%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="keywords"
	content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
<meta name="description"
	content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">

<link rel="stylesheet" type="text/css" href="/web/css/main.css">
<script type="text/javascript" src="/web/js/jquery-3.3.0.min.js"></script>
<script type="text/javascript" src="/web/js/util.js"></script>
<script type="text/javascript" src="/web/js/input.js"></script>

<title>作品検索</title>

</head>

<body>

	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

	<div class="wrapper">

		<!-- タイトルバー -->
		<div class="title_bar">
			<p class="page_title">作品検索</p>
			<a href="#" id="menu_open"> <img alt="メニュー"
				src="/web/images/menu.png" class="menu-icon" />

			</a>
		</div>
		<div id="menu_marker"></div>

		<!-- エラーメッセージ -->
		<%
		if ("".equals(errorMessage) == false) {
		%>
		<div class="errormesage">
			<%=errorMessage%>
		</div>
		<%
		}
		%>

		<!-- フォーム -->
		<form method="post" name="main" action="/web/ja/S00005/search">


			<!-- 条件タイトル、フォームをまとめるdiv-->
			<div id="jouken_date" class="<%=release_date_is_error%>">


				<!-- 公開日 -->
				<div class="input_table">
					<table>
						<tr>
							<td class="label" rowspan=2>公開日</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="release_date_radio1"name="release_date_radio"
											value="1" class="onOffRadio" <%=release_date_radio1%> onclick="change('release_date')"><span
											class="radio_label">指定</span></td>
										<td><input type="radio" id="release_date_radio2"name="release_date_radio"
											value="2" class="onOffRadio" <%=release_date_radio2%>onclick="change('release_date')"><span
											class="radio_label">指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value"><input type="date"id="release_date_from"
								name="release_date_from" value="<%=release_date_from%>" disabled> <br />
								<div class="left_padding1">≀</div> <input type="date" id="release_date_to"
								name="release_date_to" value="<%=release_date_to%>"disabled></td>
						</tr>
					</table>
				</div>
			</div>


			<div id="jouken_rating" class="jouken<%=rating_is_error%>">
				<div class="input_table">
					<table>
						<tr>
							<td class="label" rowspan=2>感動指数</td>

							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="rating_radio1"name="rating_radio" value="1"
											class="onOffRadio" <%=rating_radio1%> onclick="change('rating')"> <span
											class="radio_label">指定</span></td>
										<td><input type="radio" id="rating_radio2"name="rating_radio" value="2"
											class="onOffRadio" <%=rating_radio2%> onclick="change('rating')"> <span
											class="radio_label">指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value"><input type="text" id="rating_from"name="rating_from"
								maxlength="8" value="<%=rating_from%>"disabled> <br />
								<div class="left_padding2">≀</div> <input type="text"id="rating_to"
								name="rating_to" maxlength="8" value="<%=rating_to%>"disabled></td>
						</tr>
					</table>
				</div>
			</div>



			<!-- 平均感動指数 -->
			<div id="jouken_ratingAverage"
				class="jouken<%=rating_average_is_error%>">
				<div class="input_table">
					<table>
						<tr>
							<td class="label" rowspan=2>平均感動指数</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="rating_average_radio1"name="rating_average_radio"
											value="1" <%=rating_average_radio1%> class="onOffRadio"onclick="change(rating_average)">
											<span class="radio_label">指定</span></td>
										<td><input type="radio" id="rating_average_radio2"name="rating_average_radio"
											value="2" <%=rating_average_radio2%> class="onOffRadio" onclick="change(rating_average)"><span>指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value"><select name="rating_average_from"
								tabindex="1">

									<option value="1.0" <%=rating_average_from1%>>1.0</option>
									<option value="1.1" <%=rating_average_from2%>>1.1</option>
									<option value="1.2" <%=rating_average_from3%>>1.2</option>
									<option value="1.3" <%=rating_average_from4%>>1.3</option>
									<option value="1.4" <%=rating_average_from5%>>1.4</option>
									<option value="1.5" <%=rating_average_from6%>>1.5</option>
									<option value="1.6" <%=rating_average_from7%>>1.6</option>
									<option value="1.7" <%=rating_average_from8%>>1.7</option>
									<option value="1.8" <%=rating_average_from9%>>1.8</option>
									<option value="1.9" <%=rating_average_from10%>>1.9</option>
									<option value="2.0" <%=rating_average_from11%>>2.0</option>
									<option value="2.1" <%=rating_average_from12%>>2.1</option>
									<option value="2.2" <%=rating_average_from13%>>2.2</option>
									<option value="2.3" <%=rating_average_from14%>>2.3</option>
									<option value="2.4" <%=rating_average_from15%>>2.4</option>
									<option value="2.5" <%=rating_average_from16%>>2.5</option>
									<option value="2.6" <%=rating_average_from17%>>2.6</option>
									<option value="2.7" <%=rating_average_from18%>>2.7</option>
									<option value="2.8" <%=rating_average_from19%>>2.8</option>
									<option value="2.9" <%=rating_average_from20%>>2.9</option>
									<option value="3.0" <%=rating_average_from21%>>3.0</option>
									<option value="3.1" <%=rating_average_from22%>>3.1</option>
									<option value="3.2" <%=rating_average_from23%>>3.2</option>
									<option value="3.3" <%=rating_average_from24%>>3.3</option>
									<option value="3.4" <%=rating_average_from25%>>3.4</option>
									<option value="3.5" <%=rating_average_from26%>>3.5</option>
									<option value="3.6" <%=rating_average_from27%>>3.6</option>
									<option value="3.7" <%=rating_average_from28%>>3.7</option>
									<option value="3.8" <%=rating_average_from29%>>3.8</option>
									<option value="3.9" <%=rating_average_from30%>>3.9</option>
									<option value="4.0" <%=rating_average_from31%>>4.0</option>
									<option value="4.1" <%=rating_average_from32%>>4.1</option>
									<option value="4.2" <%=rating_average_from33%>>4.2</option>
									<option value="4.3" <%=rating_average_from34%>>4.3</option>
									<option value="4.4" <%=rating_average_from35%>>4.4</option>
									<option value="4.5" <%=rating_average_from36%>>4.5</option>
									<option value="4.6" <%=rating_average_from37%>>4.6</option>
									<option value="4.7" <%=rating_average_from38%>>4.7</option>
									<option value="4.8" <%=rating_average_from39%>>4.8</option>
									<option value="4.9" <%=rating_average_from40%>>4.9</option>
									<option value="5.0" <%=rating_average_from41%>>5.0</option>

							</select>
								<div class="left_padding3">≀</div> <select
								name="rating_average_to" tabindex="2" >

									<option value="1.0" <%=rating_average_to1%>>1.0</option>
									<option value="1.1" <%=rating_average_to2%>>1.1</option>
									<option value="1.2" <%=rating_average_to3%>>1.2</option>
									<option value="1.3" <%=rating_average_to4%>>1.3</option>
									<option value="1.4" <%=rating_average_to5%>>1.4</option>
									<option value="1.5" <%=rating_average_to6%>>1.5</option>
									<option value="1.6" <%=rating_average_to7%>>1.6</option>
									<option value="1.7" <%=rating_average_to8%>>1.7</option>
									<option value="1.8" <%=rating_average_to9%>>1.8</option>
									<option value="1.9" <%=rating_average_to10%>>1.9</option>
									<option value="2.0" <%=rating_average_to11%>>2.0</option>
									<option value="2.1" <%=rating_average_to12%>>2.1</option>
									<option value="2.2" <%=rating_average_to13%>>2.2</option>
									<option value="2.3" <%=rating_average_to14%>>2.3</option>
									<option value="2.4" <%=rating_average_to15%>>2.4</option>
									<option value="2.5" <%=rating_average_to16%>>2.5</option>
									<option value="2.6" <%=rating_average_to17%>>2.6</option>
									<option value="2.7" <%=rating_average_to18%>>2.7</option>
									<option value="2.8" <%=rating_average_to19%>>2.8</option>
									<option value="2.9" <%=rating_average_to20%>>2.9</option>
									<option value="3.0" <%=rating_average_to21%>>3.0</option>
									<option value="3.1" <%=rating_average_to22%>>3.1</option>
									<option value="3.2" <%=rating_average_to23%>>3.2</option>
									<option value="3.3" <%=rating_average_to24%>>3.3</option>
									<option value="3.4" <%=rating_average_to25%>>3.4</option>
									<option value="3.5" <%=rating_average_to26%>>3.5</option>
									<option value="3.6" <%=rating_average_to27%>>3.6</option>
									<option value="3.7" <%=rating_average_to28%>>3.7</option>
									<option value="3.8" <%=rating_average_to29%>>3.8</option>
									<option value="3.9" <%=rating_average_to30%>>3.9</option>
									<option value="4.0" <%=rating_average_to31%>>4.0</option>
									<option value="4.1" <%=rating_average_to32%>>4.1</option>
									<option value="4.2" <%=rating_average_to33%>>4.2</option>
									<option value="4.3" <%=rating_average_to34%>>4.3</option>
									<option value="4.4" <%=rating_average_to35%>>4.4</option>
									<option value="4.5" <%=rating_average_to36%>>4.5</option>
									<option value="4.6" <%=rating_average_to37%>>4.6</option>
									<option value="4.7" <%=rating_average_to38%>>4.7</option>
									<option value="4.8" <%=rating_average_to39%>>4.8</option>
									<option value="4.9" <%=rating_average_to40%>>4.9</option>
									<option value="5.0" <%=rating_average_to41%>>5.0</option>
							</select></td>
						</tr>
					</table>
				</div>
			</div>


			<!-- 再生回数 -->
			<div id="jouken_views" class="jouken<%=views_is_error%>">
				<div class="input_table">
					<table>
						<tr>
							<td class="label" rowspan=2>再生回数</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="views_radio1"name="views_radio" value="1"
											class="onOffRadio" <%=views_radio1%> onclick="change('views')"> <span
											class="radio_label">指定</span></td>
										<td><input type="radio" id="views_radio2"name="views_radio" value="2"
											class="onOffRadio" <%=views_radio2%> onclick="change('views')"> <span
											class="radio_label">指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value"><input type="text" id="views_from"name="views_from"
								maxlength="8" value="<%=views_from%>"disabled>
								<div class="left_padding2">≀</div> <input type="text"id="views_to"
								name="views_to" maxlength="8" value="<%=views_to%>"disabled></td>
						</tr>
					</table>
				</div>
			</div>


			<!-- 曲名 -->
			<div id="jouken_song_title" class="jouken<%=title_is_error%>">
				<div class="input_table">
					<table>
						<tr>
							<td class="label" rowspan=2>曲名</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="title_radio1"name="title_radio" value="1"
											class="onOffRadio" <%=title_radio1%> onclick="namechange('title')"> <span
											class="radio_label">指定</span></td>
										<td><input type="radio" id="title_radio2"name="title_radio" value="2"
											class="onOffRadio" <%=title_radio2%> onclick="namechange('title')"> <span
											class="radio_label">指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="title_type_radio1"name="title_type_radio" value="1"
											<%=title_type_radio1%>> <span class="radio_label">あいまい</span></td>
										<td><input type="radio" id="title_type_radio2"name="title_type_radio" value="2"
											<%=title_type_radio2%>> <span class="radio_label">完全一致</span></td>
									</tr>
								</table> <input type="text" id="title"name="title" maxlength="255"
								value="<%=title%>"disabled>
							</td>
						</tr>
					</table>
				</div>
			</div>


			<!-- 並び順 -->
			<div id="jouken_sort_order" class="jouken">
				<div class="input_table">
					<table>
						<tr>
							<td class="label">並び順</td>
							<td class="value"><select name="sort_order" tabindex="10">
									<option value="01" <%=sort_order1%>>新しい順</option>
									<option value="02" <%=sort_order2%>>古い順</option>
									<option value="03" <%=sort_order3%>>感動指数が多い順</option>
									<option value="04" <%=sort_order4%>>感動指数が少ない順</option>
									<option value="05" <%=sort_order5%>>平均感動指数が高い順</option>
									<option value="06" <%=sort_order6%>>平均感動指数が低い順</option>
									<option value="07" <%=sort_order7%>>再生回数が多い順</option>
									<option value="08" <%=sort_order8%>>再生回数が少ない順</option>
							</select></td>
						</tr>
					</table>
				</div>
			</div>


			<!-- メインボタン -->
			<div class="main_button">
				<input type="submit" value="検索">
			</div>

		</form>
	</div>











	<!-- ページトップへjavaScript -->
	<div id="pagetop" hidden>
		<img alt="ページトップ" src="/web/images/pagetop.png">
	</div>

	<!-- フッター -->
	<footer>
		Copyright <a href="https://www.excd.jp/">&copy; EXCEED Co., Ltd.</a>
		All Rights Reserved.
	</footer>

	<script>
	window.addEventListener('load',function(){
		namechange('title')
		change('release_date')
		change('rating')
		change('rating_average')
		change('views')
	});</script>
</body>

</HTML>

