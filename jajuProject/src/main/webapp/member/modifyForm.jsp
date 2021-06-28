<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/modify.css">
</head>
<body>
	<jsp:include page="/mypage/mypageMain.jsp" />
	
	<div class="page_section section_myinfo">
	<div class="head_aticle">
		<h2 class="tit">개인 정보 수정</h2>
	</div>
	
	<div class="type_form member_join member_pw">
		<div class="field_pw" id="field_pw">
			<h3 class="tit">비밀번호 재확인</h3>
			<p class="sub">회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요.</p>
			<form name="confirmForm" class="confirmForm">
				<table class="tbl_comm">
					<tr class="fst">
						<th>아이디</th>
						<td>
							<input type="text" id="confirm_id" readonly="" class="inp_read">
						</td>
					</tr>
					<tr>
						<th>비밀번호<span class="ico">*<span class="screen_out">필수항목</span></span></th>
						<td>
							<input type="password" name="confirm_password" id="confirm_password" class="inp_pw">
							<div id="confirmPwdDiv"></div>
						</td>
					</tr>
				</table>
				
				<input type="button" id="modifyBtn" class="btn active" value="확인"></button>
				
			</form>
		</div>
		
		<div id="modify" style="display: none;">
			<div class="field_pw">
				<form name="confirmForm" id="modifyForm">
					<table class="tbl_comm">
				<tr class="fst">
					<th>아이디<span class="ico">*<span class="screen_out">필수항목</span></span></th>
					<td>
						<input type="text" name="member_id" id="member_id" readonly>
					</td>
				</tr>
				<tr>
					<th>비밀번호<span class="ico">*<span class="screen_out">필수항목</span></span></th>
					<td>
						<input type="password" name="member_pwd" id="member_pwd" required="" fld_esssential="" option="regPass" label="비밀번호" maxlength="16" class="reg_pw" placeholder="비밀번호를 입력해주세요">
						<div id="member_pwdDiv" class="checkDiv"></div>
					</td>
				</tr>
				<tr class="member_pwd">
					<th>비밀번호확인<span class="ico">*<span class="screen_out">필수항목</span></span></th>
					<td>
						<input type="password" name="member_repwd" id="member_repwd" required="" fld_esssential="" option="regPass" label="비밀번호" maxlength="16" class="confirm_pw" placeholder="비밀번호를 한번 더 입력해주세요">
						<div id="member_repwdDiv" class="checkDiv"></div>
					</td>
				</tr>
				<tr>
					<th>이름<span class="ico">*<span class="screen_out">필수항목</span></span></th>
					<td>
						<input type="text" name="member_name" id="member_name" value="" required="" fld_esssential="" label="이름" placeholder="이름을 입력해주세요">
						<div id="member_nameDiv" class="checkDiv"></div>
					</td>
				</tr>
				<tr>
					<th>이메일<span class="ico">*<span class="screen_out">필수항목</span></span></th>
					<td>
						<input type="text" name="member_email" id="member_email" value="" data-email="" size="30" required="" fld_esssential="" option="regEmail" label="이메일" placeholder="예: jaju123@jaju123.com">
						<input type="hidden" name="chk_email" id="chk_email" required="" fld_esssential="" label="이메일중복체크">
						<input type="button" id="chk_emailBtn" class="btn default" value="이메일 인증">
						<div id="codeNum" class="code_num">
							<input type="text" name="auth_code" id="auth_code" value="" size="6" maxlength="6" pattern="[0-9]*" label="인증번호 확인" class="inp_confirm" oninput="if(value.length>6)value=value.slice(0,6);$(this).val($(this).val().replace(/[^0-9]/g,''));">
							<button id="btn_cert_confirm" class="btn default" type="button">인증번호 확인</button>
							<p id="countdown" class="count_down"></p>
						</div>
						<div id="member_emailDiv" class="checkDiv"></div>
					</td>
				</tr>
				<tr class="field_phone">
					<th>휴대폰<span class="ico">*<span class="screen_out">필수항목</span></span></th>
					<td>
						<div class="phone_num">
							<input type="text" value="" pattern="[0-9]*" name="member_telephone" id="member_telephone" maxlength="13" placeholder="예:010-1234-5678" class="inp">
							<div id="member_telephoneDiv" class="checkDiv"></div>
						</div>
					</td>
				</tr>
				<tr>
					<th>주소<span class="ico">*<span class="screen_out">필수항목</span></span></th>
					<td class="field_address">
						<input type="hidden" name="zonecode" id="zonecode" size="5" value="">
						<div id="selectAddress">
							<input type="text" name="member_address" id="addr" value="" readonly="readonly" label="주소">
							<input type="hidden" name="member_sido" id="sido">
							<input type="hidden" name="member_sigungu" id="sigungu">
						</div>
						<a href="javascript:void(0)" id="addressSearch" class="search">
							<span id="addressNo" class="address_no" data-text="재검색">주소 검색</span>
						</a>
						<div id="selectAddressSub">
							<input type="text" name="member_detailAddr" id="member_detailAddr" value="" class="byteTotext" placeholder="나머지 주소를 입력해주세요">
						</div>
						<div id="member_addrDiv" class="checkDiv"></div>
					</td>
				</tr>
				<tr class="select_gender">
					<th>성별</th>
					<td>
						<label class="">
							<input type="radio" name="member_gender" id="m" value="m">
							<span class="ico"></span>남자
						</label>
						<label class="">
							<input type="radio" name="member_gender" id="w" value="w">
							<span class="ico"></span>여자
						</label>
						<label class="checked">
							<input type="radio" name="member_gender" id="n" value="n">
							<span class="ico"></span>선택안함
						</label>
					</td>
				</tr>
			</table>
			
			<div id="formSubmit" class="form_footer">
				<button type="button" class="btn active btn_join" id="writeBtn">수정하기</button>
			</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/jaju/js/modify.js"></script>
</html>