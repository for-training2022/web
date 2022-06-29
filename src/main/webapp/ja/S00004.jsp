<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%><%@ page import="jp.excd.bean.ComposerBean"%><%@ page import="jp.excd.bean.SongBean"%>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ" />
  <meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
  <title>作曲家紹介</title>
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
      <p class="page_title">作曲家紹介</p>
      <a href="#" id="menu_open">
        <img alt="メニュー" src="/web/images/menu.png" class="menu-icon" />
      </a>
    </div>

    <!-- メニューの起点 -->
    <div id="menu_marker">
    </div>

    <!-- 基本情報 -->
    <% ComposerBean composerBean = (ComposerBean)request.getAttribute( "composerBean" ); %>
    <div class="double_rows_table">
      <table>
        <tr>
          <td class="label">ID</td>
          <td class="value"><%=composerBean.getUniqueCode() %></td>
        </tr>
        <tr>
          <td class="label">ニックネーム</td>
          <td class="value"><%=composerBean.getNickname() %></td>
        </tr>
      </table>
    </div>
    
    <!-- メッセージ -->
    <% if(composerBean.getMessage() != null &&  !"".equals(composerBean.getMessage())){ %>
    <div class="single_row_table">
      <table>
        <tr>
          <td class="label">メッセージ</td>
        </tr>
        <tr>
          <td class="value"><%=composerBean.getMessage() %></td>
        </tr>
      </table>
    </div>
    <% } %>

    <!-- プロフィール -->
    <div class="single_row_table">
      <table>
        <tr>
          <td class="label">プロフィール</td>
        </tr>
        <tr>
          <td class="value">
          <% if(composerBean.getGenderResult() != null && !"".equals(composerBean.getGenderResult())){ %>
            <span class="label_top">性別：</span>
            <span class="value"><%=composerBean.getGenderResult() %></span>
          <% } %>
          <% if(composerBean.getBirthday_formated() != null && !"".equals(composerBean.getBirthday_formated())){ %>
            <span class="label">誕生日：</span>
            <span class="value"><%=composerBean.getBirthday_formated() %></span>
            <br/>
          <% } %>
          <% if((composerBean.getGenderResult() == null || "".equals(composerBean.getGenderResult())) &&(composerBean.getBirthday_formated() == null || "".equals(composerBean.getBirthday_formated()))){ %>
          <% }else if((composerBean.getBirthday_formated() == null || "".equals(composerBean.getBirthday_formated())) && (composerBean.getFbLink() != null && !"".equals(composerBean.getFbLink()))){ %>
          	<br/>
          <% } %>
          <% if(composerBean.getFbLink() != null && !"".equals(composerBean.getFbLink())){ %>
            <span class="label_top">FB：</span>
            <span class="value"><a href="<%= composerBean.getFbLink()%>"><%=composerBean.getFbLink() %></a></span>
            <br/>
          <% } %>
          <% if((composerBean.getGenderResult() == null || "".equals(composerBean.getGenderResult())) &&(composerBean.getBirthday_formated() == null || "".equals(composerBean.getBirthday_formated())) && (composerBean.getFbLink() == null || "".equals(composerBean.getFbLink())) && (composerBean.getTwLink() != null && !"".equals(composerBean.getTwLink()))){ %>
          <% }else if((composerBean.getBirthday_formated() == null || "".equals(composerBean.getBirthday_formated())) && (composerBean.getFbLink() == null || "".equals(composerBean.getFbLink())) && (composerBean.getTwLink() != null && !"".equals(composerBean.getTwLink()))){ %>
           <br/>
          <% } %>
          <% if(composerBean.getTwLink() != null && !"".equals(composerBean.getTwLink())){ %>
            <span class="label_top">Twitter：</span>
            <span class="value"><a href="<%= composerBean.getTwLink()%>"><%=composerBean.getTwLink() %></a></span>
          <% } %>
          </td>
        </tr>
      </table>
    </div>

    <!-- 情報 -->
    <div class="single_row_table">
      <table>
        <tr>
          <td class="label">情報</td>
        </tr>
        <tr>
          <td class="value">
            <span class="label_top">登録：</span>
            <span class="value"><%=composerBean.getJoined_date_formated() %></span>
            <br/>

            <span class="label_top">作品数：</span>
            <span class="value"><%=composerBean.getSongTotal() %></span>
            <br/>

            <span class="label_top">総感動指数：</span>
            <span class="value"><%=composerBean.getTotalRatingFormated() %></span>
            <br/>

            <span class="label_top">平均感動指数：</span>
            <span class="value"><%=composerBean.getAverageRatingFormated() %></span>
            <br/>

            <span class="label_top">総再生回数：</span>
            <span class="value"><%=composerBean.getTotalListenFormated() %></span>
            <br/>

          </td>
        </tr>
      </table>
    </div>

    <!-- 関連リンク -->
    <% if(composerBean.getOtherLinkDecription() != null &&  !"".equals(composerBean.getOtherLinkDecription())){ %>
    <div class="single_row_table">
      <table>
        <tr>
          <td class="label">関連リンク</td>
        </tr>
        <tr>
          <td class="value">
            <a href="<%= composerBean.getOtherLinkUrl() %>"><%=composerBean.getOtherLinkDecription() %></a>
          </td>
        </tr>
      </table>
    </div>
    <% } %>

    <!-- 公開曲一覧のヘッダー -->
    <div class="sub_header">
      <p>公開曲一覧</p>
    </div>

    <!-- ソングテーブル -->
    <% ArrayList<SongBean> songList = (ArrayList<SongBean>)request.getAttribute( "songList" ); 
			for(SongBean sb: songList){  %>
    <div class="song_list">
      <ul>

        <li>
          <div class="cell">
            <div class="song_title"><%=sb.getTitle() %></div>
            <div class="image_base">
              <a href="http://localhost:8080/web/ja/S00003/<%= sb.getSong_id() %>">
                <div class="image">
                	<%if(sb.getImage_file_name() != null && !"".equals(sb.getImage_file_name())){ %>
                 		<img alt="<%= sb.getSong_id() %>" src="/web/images/<%= sb.getImage_file_name() %>" 
                  		style= "height: <%= sb.getFormatHeight() %>px !important;
                  		top:-<%= sb.getCutLength() %>px !important;
                  		position:relative !important;">
                 	<% }else{ %>
                 		<img alt="<%= sb.getSong_id() %>" src="/web/images/noimage.png" 
                 		style= "height:220px !important;
                  		top:-30px !important;
                  		position:relative !important;">
                 	<% } %>
                 
                 <img alt="play" class="play" src="/web/images/play.png">
                </div>
              </a>
            </div>
            <div class="detail">
              <span class="label_top">総感動指数：</span>
              <span class="value"><%=sb.getRating_total_formated() %></span>
              <span class="label">平均感動指数：</span>
              <span class="value"><%=sb.getRating_average_formated() %></span>
              <span class="label">再生回数：</span>
              <span class="value"><%=sb.getTotal_listen_count_formated() %></span>
              <span class="label">公開：</span>
              <span class="value"><%=sb.getRelease_datetime_formated() %></span>
            </div>
          </div>
        </li>
        <% } %>

      </ul>
    </div>


    <!-- ページトップへjavaScript -->
    <div id="pagetop" hidden>
      <img alt="ページトップ" src="/web/images/pagetop.png">
    </div>

    <!-- フッター -->
    <footer>
      Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All Rights Reserved.
    </footer>

  </div>
</body>
</html>