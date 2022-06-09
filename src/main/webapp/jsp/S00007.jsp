<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ja">
<%
	// (1) 「エラー情報(error)」が設定されている場合は、画面に「エラー情報(error)」を表示する。
	String errorMessage = (String)request.getAttribute("error");
    if (errorMessage == null) errorMessage = "";
    
    // (2) 「ニックネーム_エラー情報(nickname_is_error)」= "1"の場合
    String nickname_is_error ="";
    if ("1".equals(request.getAttribute("nickname_is_error"))) {
    	nickname_is_error = ", error";
    }

    // (3) 以下の項目を元にニックネーム名の入力状態を再現する。
    String nickname_radio1 = "";
    if ("1".equals(request.getAttribute("nickname_radio"))) {
    	nickname_radio1 = "checked=\"checked\"";
	}
    String nickname_radio2 = "";
	if ("0".equals(request.getAttribute("nickname_radio"))) {
		nickname_radio2 = "checked=\"checked\"";
	}		
	String nickname_type_radio1 = "";
	if ("3".equals(request.getAttribute("nickname_type_radio"))) {
		nickname_type_radio1 = "checked=\"checked\"";
	}
	String nickname_type_radio2 = "";
	if ("4".equals(request.getAttribute("nickname_type_radio"))) {
		nickname_type_radio2 = "checked=\"checked\"";
	}
	String nickname = (String)request.getAttribute("nickname");
	if (nickname == null) nickname = "";		
	
	// (4) 「登録日_エラー状態(joined_date_is_error)」= "1"の場合
	String joined_date_is_error = "";
	if ("1".equals(request.getAttribute("joined_date_is_error"))) {
		joined_date_is_error = ", error";
	}

	// (5) 以下の項目を元に登録日の入力状態を再現する。
    String 	joined_date_radio1 = "";
    if ("1".equals(request.getAttribute("joined_date_radio"))) {
    	joined_date_radio1 = "checked=\"checked\"";
    }
    String joined_date_radio2 = "";
	if ("0".equals(request.getAttribute("joined_date_radio"))) {
		joined_date_radio2 = "checked=\"checked\"";
	}
	String joined_date_from = (String)request.getAttribute("joined_date_from");
	String joined_date_to = (String)request.getAttribute("joined_date_to");		
    
	// (6) 以下の項目を元に性別の選択状態を再現する。
	String gender_radio = "";
	if ("1".equals(request.getAttribute("gender_radio"))){
		gender_radio = "checked=\"checked\"";
	}
	String gender_radio2 = "";
	if ("0".equals(request.getAttribute("gender_radio"))) {
		gender_radio2 = "checked=\"checked\"";
	}
	String gender = (String)request.getAttribute("gender");

	// (7) 「誕生日_エラー状態(birthday_is_error)」= "1"の場合
	String birthday_is_error = "";
	if ("1".equals(request.getAttribute("birthday_is_error"))) {
		birthday_is_error = ", error";
	}
	
	// (8) 以下の項目を元に誕生日の入力状態を再現する。
	String 	birthday_radio1 = "";
    if ("1".equals(request.getAttribute("birthday_radio"))) {
    	birthday_radio1 = "checked=\"checked\"";
    }
    String birthday_radio2 = "";
	if ("0".equals(request.getAttribute("birthday_radio"))) {
		birthday_radio2 = "checked=\"checked\"";
	}
	
	String birthday_from = (String)request.getAttribute("birthday_from");
	String birthday_to = (String)request.getAttribute("birthday_to");
	
	// (9) 「リスナー数_エラー状態(listener_count_is_error)」= "1"の場合
	String listener_count_is_error = "";
	if ("1".equals(request.getAttribute("listener_count_is_error"))) {
		listener_count_is_error = ", error";
	}
	
	// (10) 以下の項目を元にリスナー数の入力状態を再現する。
	String 	listener_count_radio1 = "";
    if ("1".equals(request.getAttribute("listener_count_radio"))) {
    	listener_count_radio1 = "checked=\"checked\"";
    }
    String listener_count_radio2 = "";
	if ("0".equals(request.getAttribute("listener_count_radio"))) {
		listener_count_radio2 = "checked=\"checked\"";
	}
	
	String listener_count_from = (String)request.getAttribute("listener_count_from");
	String listener_count_to = (String)request.getAttribute("listener_count_to");
	
	// (11) 「言語_エラー状態 (language_type_is_error)」= "1"の場合
	String language_type_is_error = "";
	if ("1".equals(request.getAttribute("language_type_is_error"))) {
		language_type_is_error = ", error";
	}
	
	// (12) 以下の項目を元に言語の入力状態を再現する。
	String language_type_jp = "";
	String language_type_en = "";
	
    if ("1".equals(request.getAttribute("language_type_jp"))) {
    	listener_count_radio1 = "checked=\"checked\"";
    }
    if ("1".equals(request.getAttribute("language_type_en"))) {
    	listener_count_radio1 = "checked=\"checked\"";
    }
        
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
	<script type="text/javascript" src="/web/js/util.js"></script>
	<script type="text/javascript" src="/web/js/input.js"></script>
	
	<script>
	$(function(){
		$("#id_language_type_jp").val("<%= language_type_jp %>");
		$("#id_language_type_en").val("<%= language_type_en %>");
		$("#id_sort_order").val("<%= sort_order %>");
	});
	</script>
	
	<title>作曲家検索</title>

</head>

<body>

	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker">
	</div>
	
	<div class="wrapper">
	
		<!-- タイトルバー -->
	    <div class="title_bar">
	      <p class="page_title">作曲家検索</p>
	      <a href="#" id="menu_open">
	        <img alt="メニュー" src="../images/menu.png" class="menu-icon" />
	        	<%@ include file = "Menuin.jsp" %>
	      </a>
	    </div>
    

		<!-- エラーメッセージ -->    
	    <% if ("".equals(errorMessage) == false) { %>
			<div class="errormesage">
				<%= errorMessage %>
			</div>
		<% } %>
			
    <!-- フォーム -->
	<form method="post" name="main" action="/web/ja/S00007/search">

							
	<!-- 条件タイトル、フォームをまとめるdiv-->
	<div id="jouken_date" class="jouken<%= nickname_is_error %>">
	
	
		<!-- ニックネーム -->
			<div class="input_table">
				<table>
					<tr>
						<td class="label" rowspan=2 >ニックネーム</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" name="nickname_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
										</td>
											<td>
												<input type="radio" name="nickname_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
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
													<input type="radio" name="nickname_type_radio" value="1"><span class="radio_label">あいまい</span>
												</td>
											<td>
												<input type="radio" name="nickname_type_radio" value="2" checked="checked"><span class="radio_label">完全一致</span>
											</td>
										</tr>
									</table>
										<input type="text" name="nickname" value="">
									</td>
								</tr>
							</table>
						</div>
		

					
		<!--登録日-->
	      <div class="input_table">
	        <table>
	          <tr>
	            <td class="label" rowspan=2 >登録日</td>
	            <td class="value">
	              <table class="radio_base">
	                <tr>
	                  <td>
	                    <input type="radio" name="joined_date_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
	                  </td>
	                  <td>
	                    <input type="radio" name="joined_date_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
	                  </td>
	                </tr>
	              </table>
	            </td>
	          </tr>
	          <tr>
	            <td class="value">
	              <input type="date" name="joined_date_from" value="2012-10-29">
	              <br/>
	              <div class="left_padding1">
	              ≀
	              </div>
	              <input type="date" name="joined_date_from" value="2012-02-16">
	            </td>
	          </tr>
	        </table>
	      </div>
				

		<!-- 性別 -->
		<div class="input_table">
			<table>
				<tr>
					<td class="label" rowspan=2 >性別</td>
						<td class="value">
							<table class="radio_base">
								<tr>
									<td>
										<input type="radio" name="gender_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
									</td>
									<td>
										<input type="radio" name="gender_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
						<tr>
							<td class="value">
								<select name="gender" tabindex="10">
									<option value="1" selected="selected">男</option>
									<option value="2">女</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				
				
					
	 	 <!-- 誕生日 -->
			<div class="input_table">
				<table>
					<tr>
						<td class="label" rowspan=2 >誕生日</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" name="birthday_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
										</td>
											<td>
												<input type="radio" name="birthday_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
											</td>
										</tr>
									</table>
								</td>
							</tr>
								<tr>
									<td class="value">
										<input type="date" name="birthday_from" value="2012-10-29">
									<br/>
										<div class="left_padding1">
											≀
										</div>
											<input type="date" name="birthday_to" value="2012-02-16">
										</td>
									</tr>
								</table>
							</div>

		<!-- リスナー数 -->
		<div class="input_table">
			<table>
				<tr>
					<td class="label" rowspan=2 >リスナー数</td>
						<td class="value">
							<table class="radio_base">
								<tr>
									<td>
										<input type="radio" name="listener_count_radio" value="1" class="onOffRadio"><span class="radio_label">指定</span>
									</td>
										<td>
											<input type="radio" name="listener_count_radio" value="2" class="onOffRadio" checked="checked"><span class="radio_label">指定なし</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
							<tr>
								<td class="value">
									<input type="text" name="listener_count_from" value="">
								<br/>
									<div class="left_padding2">
										≀
									</div>
										<input type="text" name="listener_count_to" value="">
									</td>
								</tr>
							</table>
						</div>

					<!-- 言語 -->
			<div class="input_table">
				<table>
					<tr>
						<td class="label" rowspan=2 >言語</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="checkbox" name="language_type_jp"  value="002" class="onOffRadio" checked="checked"><span class="radio_label">日本語</span>
												<br>
											<input type="checkbox" name="language_type_en"  value="001" class="onOffRadio" ><span class="radio_label">英語</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
     
					

					<!-- 並び順 -->
			<div class="input_table">
				<table>
					<tr>
						<td class="label">並び順</td>
							<td class="value">
								<select name="sort_order" tabindex="1">
									<option value="01" selected="selected">新しい順</option>
									<option value="02">古い順</option>
									<option value="03">リスナー数が多い順</option>
									<option value="04">リスナー数が少ない順</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
	
	
			<!-- メインボタン -->
			<div class="main_button">
			<a><input type="submit" value="検索"></a>
			</div>
		
	</div>
	</form>	
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





