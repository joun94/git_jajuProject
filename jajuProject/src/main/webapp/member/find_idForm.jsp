<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/find_id.css" />
</head>
<body>
<div id="content">
<div class="section_login">
		<h3 class="tit_login">아이디 찾기</h3>
		<div class="write_form find_view">
			<form id="form" name="fm">
				<strong class="tit_label">이름</strong>
				<input type="text" name="srch_name" id="search_name" tabindex="2" size="29" required="required" placeholder="고객님의 이름을 입력해주세요">
				<div id="search_nameDiv"></div>
				<strong class="tit_label">이메일</strong>
				<input type="text" name="srch_mail" id="search_email" size="29" tabindex="5" required="required" placeholder="가입 시 등록하신 이메일 주소를 입력해주세요">
				<div id="search_emailDiv"></div>
				<button type="button" class="btn_type1" id="find_idBtn"><span class="txt_type">확인</span></button>
			</form>
			<div id="getIdDiv" style="display: none">
				<img class="thumb" src="/jaju/jajuImage/img_id_find_succsess_v2.png" alt="아이디찾기완료">
				<p class="desc"></p>
				<p class="info"></p>
				<a href="" class="btn_type1"><span class="txt_type" id="btnText"></span></a>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/jaju/js/find_id.js"></script>
</html>