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
			<h3 class="tit_login">비밀번호 찾기</h3>
			<div class="write_form find_view">
				<form method="post" id="form" name="fm" action="" onsubmit="return chkForm( this );">
					<strong class="tit_label">이름</strong>
					<input name="srch_name" id="search_name" type="text" size="29" required="" label="이름" tabindex="2" placeholder="고객님의 이름을 입력해주세요">
					<div id="search_nameDiv"></div>
					<strong class="tit_label">아이디</strong>
					<input name="srch_id" id="search_id" value="" type="text" size="29" required="" label="아이디" tabindex="2" placeholder="고객님의 아이디를 입력해주세요"><p></p>
					<div id="search_idDiv"></div>
					<strong class="tit_label">이메일</strong>
					<input name="srch_mail" id="search_email" type="text" size="29" required="" label="메일주소" tabindex="5" placeholder="가입 시 등록하신 이메일 주소를 입력해주세요"><p></p>
					<div id="search_emailDiv"></div>
					<button type="button" class="btn_type1" id="find_pwdBtn"><span class="txt_type">찾기</span></button>
				</form>
				
				<div id="codeNum" class="code_num" style="display: none">
					<input type="text" name="auth_code" id="auth_code" placeholder="인증번호를 입력하세요" value="" size="6" maxlength="6" pattern="[0-9]*" label="인증번호 확인" class="inp_confirm" oninput="if(value.length>6)value=value.slice(0,6);$(this).val($(this).val().replace(/[^0-9]/g,''));">
					<button id="btn_cert_confirm" class="btn default" type="button">인증번호 확인</button>
					<p id="countdown" class="count_down"></p>
					<div id="emailDiv" class="checkDiv"></div>
				</div>
				
				<div id="getPwdDiv" style="display: none">
					<img class="thumb" src="/jaju/jajuImage/img_id_find_succsess_v2.png" alt="비밀번호찾기완료">
					<p class="desc"></p>
					<p class="info"></p>
					<a href="#" class="btn_type1"><span class="txt_type" id="btnText"></span></a>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/jaju/js/find_pwd.js"></script>
</html>