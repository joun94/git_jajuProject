//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식
var idJ = /^[a-z0-9]{6,16}$/;
// 비밀번호 정규식
var pwdJ = /^[A-Za-z0-9]{8,16}$/; 
// 이름 정규식
var nameJ = /^[가-힣]{1,6}$/;
// 이메일 검사 정규식
var emailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
// 휴대폰 번호 정규식
var phoneJ = /^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$/;

var idC = false;
var pwdC = false;
var nameC = false;
var emailC = false;
var telC = false;

$("#member_id").blur(function() {
	if (idJ.test($(this).val())) {
			$("#member_idDiv").empty();
			idC = true;
	} else {
		$('#member_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_idDiv').html('아이디 형식을 확인 하세요.(6자 이상의 영문 혹은 영문과 숫자를 조합)');
		idC = false;
	}
});

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

$(function(){
	$('.sign_up_item a').css('color', '#064acb')
	
	$('.check_view .btn_link').click(function(){
		var layer1 = $(this).is('.btn_agreement');
		var layer2 = $(this).is('.btn_essential');
		var layer3 = $(this).is('.btn_choice');
		
		var bgDim = $('.bg_dim');
		var target = $(this).parents('.reg_agree').find('.layer');
		
		var agreement = $(this).parents('.reg_agree').find('.layer_agreement');
	    var essential = $(this).parents('.reg_agree').find('.layer_essential');
	    var choice = $(this).parents('.reg_agree').find('.layer_choice');
	    var topResult = null;
	    
	    if(layer1 == true){
	        bgDim.show();
	        agreement.show();
	        essential.hide();
	        choice.hide();
	        topResult = agreement;
	        
	      }else if(layer2 == true){
	        bgDim.show();
	        essential.show();
	        agreement.hide();
	        choice.hide();
	        topResult = essential;
	      }else if(layer3 == true){
	        bgDim.show();
	        choice.show();
	        essential.hide();
	        agreement.hide();
	        topResult = choice;
	      }
	      topResult.css({
	        'margin-top':-topResult.height() / 2
	      });
	});
	
	$('.reg_agree .btn_ok,.reg_agree .btn_close').click(function(){
			$(this).parents('.layer').prev().find('input[type=checkbox]').prop('checked', true);
		$(this).parents('.layer').hide();
	    $('.bg_dim').hide();
	});
	
	$('.check_agree input[name=agree_allcheck]').change(function(){
		if($(this).is(':checked') == true){
			$('.check_agree').addClass('checked');
			
			$('.reg_agree').find('input[type=checkbox]').prop('checked', true);
			$('.reg_agree').find('input[type=checkbox]').val('y');
		}else {
			$('.check_agree').removeClass('checked');
			
			$('.reg_agree').find('input[type=checkbox]').prop('checked', '');
			$('.reg_agree').find('input[type=checkbox]').val('n');
		}
	});
	
	$('#addressSearch').click(function(){
		new daum.Postcode({
	        oncomplete: function(data) {
	            $('#addressSearch').addClass('re_search');
	            $('#addressNo').html('재검색');
	            $('#selectAddress').show();
	            $('#selectAddressSub').show();
	           
	            $('#addr').val(data.address);
	            
	            $('#sido').val(data.sido);
	            $('#sigungu').val(data.sigungu);
	        }
	    }).open();
	});

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

$('#chk_idBtn').click(function(){
	$('#member_idDiv').empty();
	$('#chk_idDiv').empty();
	
	if($('#member_id').val() == ''){	
		$('#member_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_idDiv').html('아이디를 입력하세요.');
		$('#member_id').focus();
	}else if(!idC) {
		$('#member_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_idDiv').html('아이디 형식을 확인 하세요.(6자 이상의 영문 혹은 영문과 숫자를 조합)');
		idC = false;
	}else {
		$.ajax({
			url: '/jaju/member/checkId',
			type: 'post',
			data: {
				'member_id': $('#member_id').val()
			},
			dataType: 'text',
			success: function(data){
				if(data == 'exist'){
					$('#chk_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
					$('#chk_idDiv').html('사용 불가능한 아이디입니다.');
					$('#chk_id').val('');
				}else {
					$('#chk_idDiv').css({'color': '#064acb', 'font-size': '8pt', 'font-weight': 'bold'});
					$('#chk_idDiv').html('사용 가능한 아이디입니다.');
					$('#chk_id').val($('#member_id').val());
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

$('#writeBtn').click(function(){
	$('#member_idDiv').empty();
	$('#member_pwdDiv').empty();
	$('#member_repwdDiv').empty();
	$('#member_nameDiv').empty();
	$('#member_emailDiv').empty();
	$('#member_telephoneDiv').empty();
	$('#member_addrDiv').empty();
	$('#checkDiv').empty();
	
	if($('#member_id').val() == ''){	
		$('#member_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_idDiv').html('아이디를 입력하세요.');
		$('#member_id').focus();
	}else if($('#member_id').val().length < 6){
		$('#member_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_idDiv').html('아이디를 6글자 이상 입력하세요.');
		$('#member_id').focus();
	}else if($('#member_id').val() != $('#chk_id').val()){
		$('#member_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#member_idDiv').html('아이디 중복 체크하세요.');
		$('#member_id').focus();
	}else if($('#member_pwd').val() == ''){
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
	}else if(!$('#agree').is(':checked') || !$('#private1').is(':checked') || !$('#fourteen_chk').is(':checked')){
		$('#checkDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#checkDiv').html('필수 이용약관을 확인 하세요.');
	}else if(!idC || !pwdC || !nameC || !emailC || !telC){
		$('#checkDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#checkDiv').html('형식을 확인 하세요.');
	}else {
		$.ajax({
			url: '/jaju/member/write',
			type: 'post',
			data: $('#form').serialize(),
			success: function(){
				alert('회원가입 성공');
				location.href="/jaju/";
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});