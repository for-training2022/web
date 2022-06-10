<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.excd.bean.ComposerRecord" %>
<!DOCTYPE html>
<html>
<%
	List<ComposerRecord> ComposerList = (List<ComposerRecord>)request.getAttribute("list");
	%>
<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
 <meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
 <meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
 <title>作曲家検索</title>
 <link rel="stylesheet" href="/web/css/main.css" />
 <script src="/web/js/jquery-3.3.0.min.js"></script>
 <script src="/web/js/util.js"></script>
</head>
<body>
  <!-- メニューのキャンセルレイヤの起点 -->
  <div id="layer_marker">
  </div>
 
  <div class="wrapper">

    <!-- タイトルバー -->
    <div class="title_bar">
      <p class="page_title">作曲家検索</p>
      <a href="javascript:formBack.submit()" class="back">&lt;&nbsp;戻る</a> 
      <a href="#" id="menu_open"> 
        <img alt="メニュー" src="/web/images/menu.png" class="menu-icon" />
      </a>
    </div>

    <!-- メニューの起点 -->
    <div id="menu_marker">
    </div>

      

    <!-- 検索結果表示 -->
    <div class="message_with_right_button">
     <p><%=request.getAttribute("hits")%></p>
      <div class="right_button">
        <a href="javascript:formChange.submit()">条件変更</a>
      </div>
    </div>


    <!-- 作曲家テーブル -->
    <div class="person_list">
      <ul>
      <%
					for (ComposerRecord record : ComposerList) {
				%>
        <li>
          <div class="list">
          <hr>
            <div class="person_name"><a href="/web/ja/S00004/<%=record.getUniqueCode()%>"><%=record.getNickname() %></a>
            </div>

            <div class="detail">
              <span class="label">登録日:　</span>
              <span class="value"><%=record.getJoined_date() %></span><br>
              <span class="label">性別:　　</span> 
              <span class="value"><%=record.getGender() %></span><br>
              <span class="label">誕生日:　</span> 
              <span class="value"><%=record.getBirthday() %></span><br>
              <span class="label">リスナー数:</span> 
              <span class="value"><%=record.getListener_count() %></span><br>
              <span class="label">言語:　　</span> 
              <span class="value"><%=record.getLanguage_type() %></span>
            </div>
          </div>
        </li>
		
        <%
					}
				%>

      </ul>
    </div>

    <!-- メインボタン -->
    <div class="main_button">
      <a href="javascript:formChange.submit()">条件変更</a>
    </div>
    
    
    <!-- フォーム -->
	<form id="formBack" method="POST" action="/web/ja/S00008/back">
		<input name="nickname_radio" type="hidden" value="<%= request.getAttribute("nickname_radio") %>">
		<input name="nickname_type_radio" type="hidden" value="<%= request.getAttribute("nickname_type_radio") %>">
		<input name="nickname" type="hidden" value="<%= request.getAttribute("nickname") %>">
		<input name="joined_date_radio" type="hidden" value="<%= request.getAttribute("joined_date_radio") %>">
		<input name="joined_date_from" type="hidden" value="<%= request.getAttribute("joined_date_from") %>">
		<input name="joined_date_to" type="hidden" value="<%= request.getAttribute("joined_date_to") %>">
		<input name="gender_radio" type="hidden" value="<%= request.getAttribute("gender_radio") %>">
		<input name="gender" type="hidden" value="<%= request.getAttribute("gender") %>">
		<input name="birthday_radio" type="hidden" value="<%= request.getAttribute("birthday_radio") %>">
		<input name="birthday_from" type="hidden" value="<%= request.getAttribute("birthday_from") %>">
		<input name="birthday_to" type="hidden" value="<%= request.getAttribute("birthday_to") %>">
		<input name="listener_count_radio" type="hidden" value="<%= request.getAttribute("listener_count_radio") %>">
		<input name="listener_count_from" type="hidden" value="<%= request.getAttribute("listener_count_from") %>">
		<input name="listener_count_to" type="hidden" value="<%= request.getAttribute("listener_count_to") %>">
		<input name="language_type_jp" type="hidden" value="<%= request.getAttribute("language_type_jp") %>">
		<input name="language_type_en" type="hidden" value="<%= request.getAttribute("language_type_en") %>">
		<input name="sort_order" type="hidden" value="<%= request.getAttribute("sort_order") %>">
	</form>
	<form id="formChange" method="POST" action="/web/ja/S00008/change">
		<input name="nickname_radio" type="hidden" value="<%= request.getAttribute("nickname_radio") %>">
		<input name="nickname_type_radio" type="hidden" value="<%= request.getAttribute("nickname_type_radio") %>">
		<input name="nickname" type="hidden" value="<%= request.getAttribute("nickname") %>">
		<input name="joined_date_radio" type="hidden" value="<%= request.getAttribute("joined_date_radio") %>">
		<input name="joined_date_from" type="hidden" value="<%= request.getAttribute("joined_date_from") %>">
		<input name="joined_date_to" type="hidden" value="<%= request.getAttribute("joined_date_to") %>">
		<input name="gender_radio" type="hidden" value="<%= request.getAttribute("gender_radio") %>">
		<input name="gender" type="hidden" value="<%= request.getAttribute("gender") %>">
		<input name="birthday_radio" type="hidden" value="<%= request.getAttribute("birthday_radio") %>">
		<input name="birthday_from" type="hidden" value="<%= request.getAttribute("birthday_from") %>">
		<input name="birthday_to" type="hidden" value="<%= request.getAttribute("birthday_to") %>">
		<input name="listener_count_radio" type="hidden" value="<%= request.getAttribute("listener_count_radio") %>">
		<input name="listener_count_from" type="hidden" value="<%= request.getAttribute("listener_count_from") %>">
		<input name="listener_count_to" type="hidden" value="<%= request.getAttribute("listener_count_to") %>">
		<input name="language_type_jp" type="hidden" value="<%= request.getAttribute("language_type_jp") %>">
		<input name="language_type_en" type="hidden" value="<%= request.getAttribute("language_type_en") %>">
		<input name="sort_order" type="hidden" value="<%= request.getAttribute("sort_order") %>">
	</form>
	
	
	
	
	
    <!-- ページトップへjavaScript -->
    <div id="pagetop" hidden>
      <img alt="ページトップ" src="/web/images/pagetop.png">
    </div>

    <!-- フッター -->
    <footer>
      Copyright <a href="https://www.excd.jp/">&copy; EXCEED Co., Ltd.</a> All Rights Reserved.
    </footer>

  </div>
</body>
</html>