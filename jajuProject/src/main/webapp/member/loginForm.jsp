<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/login.css" />
</head>
<body>
<div id="content">
	<div class="section_login">
		<h3 class="tit_login">로그인</h3>
		<div class="write_form">
			<div class="write_view login_view">
				<form method="post" name="form" id="form">
					<input type="text" name="member_id" id="member_id" size="20" tabindex="1" value="" placeholder="아이디를 입력해주세요">
					<div id="idDiv"></div>
					<input type="password" name="member_pwd" id="member_pwd" size="20" tabindex="2" placeholder="비밀번호를 입력해주세요">
					<div id="pwdDiv"></div>
					<div class="checkbox_save">
						<label class="label_checkbox">
							<input type="checkbox" id="chk_save" name="chk_save" value="y" onchange="if( this.checked){$(this).parent().addClass('checked')}else{$(this).parent().removeClass('checked')} ">
							아이디 저장
						</label>
						
						<div class="login_search">
							<a href="/jaju/member/find_id" class="link">아이디 찾기</a>
							<span class="bar"></span>
							<a href="/jaju/member/find_pwd" class="link">비밀번호 찾기</a>
						</div>
					</div>
					<button type="button" class="btn_type1" id="loginBtn"><span class="txt_type">로그인</span></button>
				</form>
				<a href="/jaju/member/writeForm" class="btn_type2 btn_member"><span class="txt_type">회원가입</span></a>
				<a id="custom-login-btn" href="javascript:loginWithKakao()">
				  <img src="/jaju/jajuImage/kakao_login_medium_wide.png" width="222" style="margin-top: 10px;"/>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
	Kakao.init('eb076581cabcf4b94c737d45185362db');
	Kakao.isInitialized();	
</script>
<script type="text/javascript" src="/jaju/js/login.js"></script>
</html>