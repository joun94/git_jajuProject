$(function(){
	$.ajax({
		url: '/jaju/member/getMember',
		type: 'post',
		dataType: 'json',
		success: function(data){
			//console.log(JSON.stringify(data));
			$('#chk_emailBtn').hide();
			$('#confirm_id').val(data.memberDTO.member_id);

			$('#modifyBtn').click(function(){
				$('#pwdDiv').empty();
				
				if($('#confirm_password').val() == ''){
					$('#confirmPwdDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
					$('#confirmPwdDiv').html('비밀번호를 입력하세요');
				}else if($('#confirm_password').val() != data.memberDTO.member_pwd){
					$('#confirmPwdDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
					$('#confirmPwdDiv').html('잘못된 비밀번호입니다');
				}else {
					$('#field_pw').hide();
					$('#modify').show();
					
					$('#member_id').val(data.memberDTO.member_id);
				}
			});
			
			$('#member_id').val(data.memberDTO.member_id);
			$('#member_name').val(data.memberDTO.member_name);
			$('#member_email').val(data.memberDTO.member_email);
			$('#chk_email').val(data.memberDTO.member_email);
			$('#member_telephone').val(data.memberDTO.member_telephone);
			$('#addr').val(data.memberDTO.member_address);
			$('#sido').val(data.memberDTO.member_sido);
			$('#sigungu').val(data.memberDTO.member_sigungu);
			$('#member_detailAddr').val(data.memberDTO.member_detailAddr);
			
			if(data.memberDTO.member_gender == 'm'){
				$('#m').prop('checked', true);
			}else if(data.memberDTO.member_gender == 'w'){
				$('#w').prop('checked', true);
			}else if(data.memberDTO.member_gender == 'n'){
				$('#n').prop('checked', true);
			}
			
			$('#member_email').blur(function(){
				if($('#member_email').val() != data.memberDTO.member_email){
					$('#chk_emailBtn').show();
				}else {
					$('#chk_emailBtn').hide();
				}
			});
		},
		error: function(err){
			console.log(err);
		}
	});
});

//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식
var pwdJ = /^[A-Za-z0-9]{8,16}$/; 
// 이름 정규식
var nameJ = /^[가-힣]{1,6}$/;
// 이메일 검사 정규식
var emailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
// 휴대폰 번호 정규식
var phoneJ = /^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$/;

var pwdC = false;
var nameC = true;
var emailC = true;
var telC = true;

$("#member_pwd").blur(function() {
	if (pwdJ.test($(this).val())) {
			$("#member_pwdDiv").empty();
			pwdC = true;
	} else {
		$('#member_pwdDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_pwdDiv').html('비밀번호 형식을 확인 하세요.(8자 이상의 영문 혹은 숫자)');
		pwdC = false;
	}
});

$("#member_name").blur(function() {
	if (nameJ.test($(this).val())) {
		$("#member_nameDiv").empty();
		nameC = true;
	} else {
		$('#member_nameDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_nameDiv').html('이름 형식을 확인 하세요.');
		nameC = false;
	}
});

$("#member_email").blur(function() {
	if (emailJ.test($(this).val())) {
			$("#member_emailDiv").empty();
			emailC = true;
	} else {
		$('#member_emailDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_emailDiv').html('이메일 형식을 확인 하세요.');
		emailC = false;
	}
});

$("#member_telephone").blur(function() {
	if (phoneJ.test($(this).val())) {
			$("#member_telephoneDiv").empty();
			telC = true;
	} else {
		$('#member_telephoneDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_telephoneDiv').html('전화번호 형식을 확인 하세요.(예:010-1234-5678)');
		telC = false;
	}
});

$('#addressSearch').click(function(){
	new daum.Postcode({
        oncomplete: function(data) {
            $('#addressNo').html('재검색');
           
            $('#addr').val(data.address);
            
            $('#sido').val(data.sido);
            $('#sigungu').val(data.sigungu);
            $('#member_detailAddr').val('');
        }
    }).open();
});

function startTimer(count, display) {
    
	var minutes, seconds;
    timer = setInterval(function () {
	    minutes = parseInt(count / 60, 10);
	    seconds = parseInt(count % 60, 10);
	
	    minutes = minutes < 10 ? "0" + minutes : minutes;
	    seconds = seconds < 10 ? "0" + seconds : seconds;
	
	    display.html(minutes + ":" + seconds);

	    // 타이머 끝
	    if (--count < 0) {
	     clearInterval(timer);
	     display.html("시간초과");
	     $('.btn_chk').attr("disabled","disabled");
	     isRunning = false;
	    }
    }, 1000);
    
    isRunning = true;
}

$('#chk_emailBtn').click(function(){
	var timer = null;
	var isRunning = false;
	
	if($('#member_email').val() == '' || !emailC){
		$('#member_emailDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_emailDiv').html('이메일을 확인하세요.');
		$('#member_email').focus();
	}else{
		$.ajax({
			url: '/jaju/member/checkMail',
			type: 'post',
			data: {
				'email': $('#member_email').val()
			},
			success: function(){
				$('#codeNum').show();
				
				var display = $('#countdown');
		    	var leftSec = 180;
		    	// 남은 시간
		    	// 이미 타이머가 작동중이면 중지
		    	if (isRunning){
		    		clearInterval(timer);
		    		display.html("");
		    		startTimer(leftSec, display);
		    	}else{
		    		startTimer(leftSec, display);	
		    	}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

$('#btn_cert_confirm').click(function(){
	$.ajax({
		url: '/jaju/member/checkCode',
		type: 'post',
		data: {
			'authKey': $('#auth_code').val()
		},
		dataType: 'text',
		success: function(data){
			if(data == 'success'){
				$('#chk_email').val($('#member_email').val());
				$('#codeNum').hide();
			}else{
				$('#member_emailDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
				$('#member_emailDiv').html('인증번호를 다시 확인 하세요.')
				$('#chk_email').val('');
			}
		},
		error: function(err){
			console.log(err);
		}
	});
});

$('#writeBtn').click(function(){
	$('#member_idDiv').empty();
	$('#member_pwdDiv').empty();
	$('#member_repwdDiv').empty();
	$('#member_nameDiv').empty();
	$('#member_emailDiv').empty();
	$('#member_telephoneDiv').empty();
	$('#member_addrDiv').empty();
	
	if($('#member_pwd').val() == ''){
		$('#member_pwdDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_pwdDiv').html('비밀번호를 입력하세요.');
		$('#member_pwd').focus();
	}else if($('#member_pwd').val().length < 8){
		$('#member_pwdDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_pwdDiv').html('비밀번호를 8글자 이상 입력하세요.');
		$('#member_pwd').focus();
	}else if($('#member_pwd').val() != $('#member_repwd').val()){
		$('#member_repwdDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_repwdDiv').html('비밀번호를 확인 하세요.');
		$('#member_repwd').focus();
	}else if($('#member_name').val() == ''){
		$('#member_nameDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_nameDiv').html('이름을 입력하세요.');
		$('#member_name').focus();
	}else if($('#chk_email').val() != $('#member_email').val()){
		$('#member_emailDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_emailDiv').html('이메일을 인증하세요.');
		$('#member_email').focus();
	}else if($('#member_telephone').val() == ''){
		$('#member_telephoneDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_telephoneDiv').html('전화번호를 입력하세요.');
		$('#member_telephone').focus();
	}else if($('#member_detailAddr').val() == ''){
		$('#member_addrDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_addrDiv').html('주소를 입력하세요.');
		$('#member_detailAddr').focus();
	}else if(!pwdC || !nameC || !emailC || !telC){
		$('#checkDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#checkDiv').html('형식을 확인 하세요.');
	}else {
		$.ajax({
			url: '/jaju/member/modify',
			type: 'post',
			data: $('#modifyForm').serialize(),
			success: function(){
				alert('회원정보 수정 완료(다시 로그인 해주세요.)');
				
				$.ajax({
					url: '/jaju/member/logout',
					type: 'post',
					success: function(){
						alert('로그아웃 되었습니다.')
						
						location.href = '/jaju/'
					},
					error: function(err){
						console.log(err)
					}
				});
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});