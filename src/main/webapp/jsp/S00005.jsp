<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ja">
<%
	// (1) 「エラー情報(error)」が設定されている場合は、画面に「エラー情報(error)」を表示する。
	String errorMessage = (String)request.getAttribute("error");
    if (errorMessage == null) errorMessage = "";

	// (2) 「公開日_エラー状態(release_date_is_error)」= "1"の場合
	String release_date_is_error = "";
	if ("1".equals(request.getAttribute("release_date_is_error"))) {
		release_date_is_error = ", error";
	}

	// (3) 以下の項目を元にニックネームの入力状態を再現する。
	String release_date_Radio1 = "";
    if ("1".equals(request.getAttribute("release_date_is_radio"))) {
    	release_date_Radio1 = "checked=\"checked\"";
    };
	String release_date_Radio2 = "";
	if ("0".equals(request.getAttribute("release_date_is_radio"))) {
		release_date_Radio2 = "checked=\"checked\"";
	}
	String release_date_is_from = (String)request.getAttribute("release_date_is_from");
	String release_date_is_to = (String)request.getAttribute("release_date_is_to");

	// (4) 「感動指数_エラー状態(rating_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String rating_is_error = "";
	if ("1".equals(request.getAttribute("rating_is_error"))) {
		rating_is_error = ", error";
	}

	// (5) 以下の項目を元に感動指数の入力状態を再現する。
	String rating_radio1 = "";
	if ("1".equals(request.getAttribute("rating_radio"))) {
		rating_radio1 = "checked=\"checked\"";
	}
	String rating_radio2 = "";
	if ("0".equals(request.getAttribute("rating_radio"))) {
		rating_radio2 = "checked=\"checked\"";
	}
	String rating_from = (String)request.getAttribute("rating_from");
	if (rating_from == null) rating_from = "";
	String rating_to = (String)request.getAttribute("rating_to");
	if (rating_to == null) rating_to = "";

	// (6) 「平均感動指数_エラー状態(rating_average_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String rating_average_is_error = "";
	if ("1".equals(request.getAttribute("rating_average_is_error"))) {
		rating_is_error = ", error";
	}
	
	// (7) 以下の項目を元に平均感動指数の入力状態を再現する。
	String rating_average_radio1 = "";
	if ("1".equals(request.getAttribute("rating_average_radio"))) {
		rating_radio1 = "checked=\"checked\"";
	}
	String rating_average_radio2 = "";
	if ("0".equals(request.getAttribute("rating_average_radio"))) {
		rating_radio2 = "checked=\"checked\"";
	}
	String rating_average_from = (String)request.getAttribute("rating_average_from");
	if (rating_average_from == null) rating_average_from="1.0";
	String rating_average_to = (String)request.getAttribute("rating_average_to");
	if (rating_average_to == null) rating_average_to ="1.0";

	// (8) 「再生回数_エラー状態(views_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String views_is_error = "";
	if ("1".equals(request.getAttribute("views_is_error"))) {
		views_is_error = ", error";
	}
	
	// (9) 以下の項目を元に再生回数の入力状態を再現する。
	String views_radio1 = "";
	if ("1".equals(request.getAttribute("views_radio"))) {
		views_radio1 = "";
	}
	String views_radio2 = "";
	if ("0".equals(request.getAttribute("views_radio"))) {
		views_radio2 = "";
	}
	String views_from = (String)request.getAttribute("views_from");
	if (views_from == null) views_from = "";
	String views_to = (String)request.getAttribute("views_to");
	if (views_to == null) views_to = "";

	// (10) 「曲名_エラー状態(title_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String title_is_error = "";
	if ("1".equals(request.getAttribute("title_is_error"))) {
		title_is_error = ", error";
	}

	// (11) 以下の項目を元に曲名の入力状態を再現する。
	String title_radio1 = "";
	if ("1".equals(request.getAttribute("title_radio"))) {
		title_radio1 = "checked=\"checked\"";
	}
	String title_radio2 = "";
	if ("0".equals(request.getAttribute("title_radio"))) {
		title_radio2 = "checked=\"checked\"";
	}
	String title_type_radio1 = "";
	if ("3".equals(request.getAttribute("title_type_radio"))) {
		title_type_radio1 = "checked=\"checked\"";
	}
	String title_type_radio2 = "";
	if ("4".equals(request.getAttribute("title_type_radio"))) {
		title_type_radio2 = "checked=\"checked\"";
	}
	String title = (String)request.getAttribute("title");
	if (title == null) title = "";

	// (12) 以下の項目を元に並び順の入力状態を再現する。
	String sort_order = (String)request.getAttribute("sort_order");
	if (sort_order == null) sort_order="01";
%>
<head>
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
	<meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">

	<link rel="stylesheet" type="text/css" href="/web/css/main.css">
	<script type="text/javascript" src="/web/js/jquery-3.3.0.min.js"></script>
	<script type="text/javascript" src="/web/js/pageTop.js"></script>
	<script type="text/javascript" src="/web/js/menu.js"></script>
	<script type="text/javascript" src="/web/js/search_song.js"></script>
	
	<script>
	$(function(){
		$("#id_rating_average_from").val("<%= rating_average_from %>");
		$("#id_rating_average_to").val("<%= rating_average_to %>");
		$("#id_sort_order").val("<%= sort_order %>");
	});
	</script>
	
	<title>作品検索</title>

</head>

<body>

	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker">
	</div>
	
	<div class="wrapper">
	
	<!-- タイトルバー -->
    <div class="title_bar">
      <p class="page_title">作品検索</p>
      <a href="#" id="menu_open">
        <img alt="メニュー" src="../images/menu.png" class="menu-icon" />
           	<%@ include file = "Menuin.jsp" %>
      </a>
    </div>
    
    
    <% if ("".equals(errorMessage) == false) { %>
				<div class="errormesage">
					<%= errorMessage %>
				</div>
			<% } %>
			
    <!-- フォーム -->
	<form method="post" name="main" action="/web/ja/S00005/search">

					



	<!-- 条件タイトル、フォームをまとめるdiv-->
	<div id="jouken_date" class="<%= release_date_is_error %>">

					
					<!-- 公開日 -->
      <div class="input_table">
        <table>
          <tr>
            <td class="label" rowspan=2 >公開日</td>
            <td class="value">
              <table class="radio_base">
                <tr>
                  <td>
                    <input type="radio" name="release_date_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
                  </td>
                  <td>
                    <input type="radio" name="release_date_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td class="value">
              <input type="date" name="release_date_from" value="2012-10-29">
              <br/>
              ～
              <br/>
              <input type="date" name="release_date_to" value="2012-02-16">
            </td>
          </tr>
        </table>
      </div>
				

				<div id="jouken_rating" class="jouken<%= rating_is_error %>">
					<div class="input_table">
					  <table>
          <tr>
            <td class="label" rowspan=2 >感動指数</td>
						
					 <td class="value">
					 <table class="radio_base">
                <tr>
                  <td>
                    <input type="radio" name="rating_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
                  </td>
                  <td>
                    <input type="radio" name="rating_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td class="value">
              <input type="text" name="rating_from" value="">
              <br/>
              ～
              <br/>
              <input type="text" name="rating_to" value="">
            </td>
          </tr>
        </table>
				</div>
				
				
				
  <!-- 平均感動指数 -->
				<div id="jouken_ratingAverage" class="jouken<%= rating_average_is_error %>">
					<div class="input_table">
			 <table>
          <tr>
            <td class="label" rowspan=2 >平均感動指数</td>
            <td class="value">
              <table class="radio_base">
                <tr>
                  <td>
                    <input type="radio" name="rating_average_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
                  </td>
                  <td>
                    <input type="radio" name="rating_average_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td class="value">
				 <select name="rating_average_from" tabindex="1">	
					
						
						
								<option value="1.0">1.0</option>
								<option value="1.1">1.1</option>
								<option value="1.2">1.2</option>
								<option value="1.3">1.3</option>
								<option value="1.4">1.4</option>
								<option value="1.5">1.5</option>
								<option value="1.6">1.6</option>
								<option value="1.7">1.7</option>
								<option value="1.8">1.8</option>
								<option value="1.9">1.9</option>
								<option value="2.0">2.0</option>
								<option value="2.1">2.1</option>
								<option value="2.2">2.2</option>
								<option value="2.3">2.3</option>
								<option value="2.4">2.4</option>
								<option value="2.5">2.5</option>
								<option value="2.6">2.6</option>
								<option value="2.7">2.7</option>
								<option value="2.8">2.8</option>
								<option value="2.9">2.9</option>
								<option value="3.0">3.0</option>
								<option value="3.1">3.1</option>
								<option value="3.2">3.2</option>
								<option value="3.3">3.3</option>
								<option value="3.4">3.4</option>
								<option value="3.5">3.5</option>
								<option value="3.6">3.6</option>
								<option value="3.7">3.7</option>
								<option value="3.8">3.8</option>
								<option value="3.9">3.9</option>
								<option value="4.0">4.0</option>
								<option value="4.1">4.1</option>
								<option value="4.2">4.2</option>
								<option value="4.3">4.3</option>
								<option value="4.4">4.4</option>
								<option value="4.5">4.5</option>
								<option value="4.6">4.6</option>
								<option value="4.7">4.7</option>
								<option value="4.8">4.8</option>
								<option value="4.9">4.9</option>
								<option value="5.0">5.0</option>
							</select>
							  <br/>
             					 ～
              					<br/>
							 <select name="rating_average_to" tabindex="2">
								<option value="1.0">1.0</option>
								<option value="1.1">1.1</option>
								<option value="1.2">1.2</option>
								<option value="1.3">1.3</option>
								<option value="1.4">1.4</option>
								<option value="1.5">1.5</option>
								<option value="1.6">1.6</option>
								<option value="1.7">1.7</option>
								<option value="1.8">1.8</option>
								<option value="1.9">1.9</option>
								<option value="2.0">2.0</option>
								<option value="2.1">2.1</option>
								<option value="2.2">2.2</option>
								<option value="2.3">2.3</option>
								<option value="2.4">2.4</option>
								<option value="2.5">2.5</option>
								<option value="2.6">2.6</option>
								<option value="2.7">2.7</option>
								<option value="2.8">2.8</option>
								<option value="2.9">2.9</option>
								<option value="3.0">3.0</option>
								<option value="3.1">3.1</option>
								<option value="3.2">3.2</option>
								<option value="3.3">3.3</option>
								<option value="3.4">3.4</option>
								<option value="3.5">3.5</option>
								<option value="3.6">3.6</option>
								<option value="3.7">3.7</option>
								<option value="3.8">3.8</option>
								<option value="3.9">3.9</option>
								<option value="4.0">4.0</option>
								<option value="4.1">4.1</option>
								<option value="4.2">4.2</option>
								<option value="4.3">4.3</option>
								<option value="4.4">4.4</option>
								<option value="4.5">4.5</option>
								<option value="4.6">4.6</option>
								<option value="4.7">4.7</option>
								<option value="4.8">4.8</option>
								<option value="4.9">4.9</option>
								<option value="5.0">5.0</option>
							</select>
							</td>
							</tr>
							</table>
						</div>
					</div>
				</div>

				<!-- 再生回数 -->
				<div id="jouken_views" class="jouken<%= views_is_error %>">
					<div class="input_table">
					 <table>
          <tr>
            <td class="label" rowspan=2 >再生回数</td>
						<td class="value">
              <table class="radio_base">
                <tr>
                  <td>
                    <input type="radio" name="views_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
                  </td>
                  <td>
                    <input type="radio" name="views_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
          <td class="value">
              <input type="text" name="views_from" value="">
              <br/>
              ～
              <br/>
              <input type="text" name="views_to" value="">
            </td>
          </tr>
        </table>
      </div>
					

				<!-- 曲名 -->
				<div id="jouken_song_title" class="jouken<%= title_is_error %>">
					<div class="input_table">
						<table>
         					 <tr>
					            <td class="label" rowspan=2 >曲名</td>
					            <td class="value">
					              <table class="radio_base">
					                <tr>
					                  <td>
			                    <input type="radio" name="title_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
			                  </td>
			                  <td>
			                    <input type="radio" name="title_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
			                  </td>
			                </tr>
			              </table>
			            </td>
			          </tr>
			          <tr>
			            <td class="value">
			              <table class="radio_base">
			                <tr>
			                  <td>
			                    <input type="radio" name="title_type_radio" value="1"><span class="radio_label">あいまい</span>
			                  </td>
			                  <td>
			                    <input type="radio" name="title_type_radio" value="2" checked="checked"><span class="radio_label">完全一致</span>
			                  </td>
			                </tr>
			              </table>
			              <input type="text" name="title" value="" maxlength = '255'>
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
            <td class="value">
              <select name="order" tabindex="10">
                <option value="" selected="selected">新しい順</option>
                <option value="">新しい順</option>
                <option value="">古い順</option>
                <option value="">感動指数が多い順</option>
                <option value="">感動指数が少ない順</option>
                <option value="">平均感動指数が高い順</option>
                <option value="">平均感動指数が低い順</option>
                <option value="">再生回数が多い順</option>
                <option value="">再生回数が少ない順</option>
              </select>
            </td>
          </tr>
        </table>
      </div>
	</div>

		<!-- メインボタン -->
		<div class="main_button">
		<a href="S00006.html">検索</a>
		</div>
		
	</form>
	
	<!-- ページトップへjavaScript -->
	<!--<nav id="menu-conts" class="hidden">
		<div>
			<a>メニュー</a>
			<button type="button" id="close-btn">×CLOSE</button>
		</div>
		<ul>
			<li><a href="https://www.excd.jp/web/ja/S00001">HOME</a></li>
			<li><a href="https://www.excd.jp/web/ja/S00005">作品検索</a></li>
			<li><a href="https://www.excd.jp/web/ja/S00007">作曲家検索</a></li>
			<li><a href="https://www.excd.jp/web/ja/S00008">専用アプリダウンロード</a></li>
			<li><a href="https://excd.jp/">運営会社</a><a
				href="https://excd.jp/"><img src="../images/return.png"
					id="return" alt="運営会社"></a></li>
		</ul>
	</nav>
			-->

	
	</div>
		

		<!-- ページトップへjavaScript -->
    <div id="pagetop" hidden>
      <img alt="ページトップ" src="../images/pagetop.png">
    </div>
	
	 <!-- フッター -->
    <footer>
      Copyright <a href="https://www.excd.jp/">&copy; EXCEED Co., Ltd.</a> All Rights Reserved.
    </footer>
	

</body>

</HTML>





