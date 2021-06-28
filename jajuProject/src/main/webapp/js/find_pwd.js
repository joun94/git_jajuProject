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

var pwd = '바보';

$('#find_pwdBtn').click(function(){
	var timer = null;
	var isRunning = false;
	
	if($('#search_email').val() == ''){
		$('#search_emailDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#search_emailDiv').html('이메일을 입력하세요.');
		$('#search_emailDiv').focus();
	}else if($('#search_name').val() == ''){
		$('#search_nameDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#search_nameDiv').html('이름을 입력하세요.');
		$('#search_nameDiv').focus();
	}else if($('#search_id').val() == ''){
		$('#search_idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#search_idDiv').html('아이디를 입력하세요.');
		$('#search_idDiv').focus();
	}else{
		$.ajax({
			url: '/jaju/member/getPwd',
			type: 'post',
			data: {
				'search_name': $('#search_name').val(),
				'search_id': $('#search_id').val(),
				'search_email': $('#search_email').val()
			},
			datyType: 'text',
			success: function(data){
				if(data == 'fail'){
					$('#form').hide();
					$('.desc').html('고객님께서 입력하신 정보가<br>정확한지 확인 후 다시 시도해주세요.');
					$('#btnText').html('비밀번호 다시 찾기');
					$('a.btn_type1').prop('href', '/jaju/member/find_pwd');
					$('#getPwdDiv').show();
				}else{
					pwd = data;
					
					sendMail();
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

function sendMail(){
	var timer = null;
	var isRunning = false;
	
	$('#form').hide();
	
	$.ajax({
		url: '/jaju/member/checkMail',
		type: 'post',
		data: {
			'email': $('#search_email').val()
		},
		dataType: 'text',
		success: function(data){
			$('#codeNum').show();
			
			var display = $('#countdown');
			var leftSec = 180;
			
			if(isRunning){
				clearInterval(timer);
				display.html('');
				startTimer(leftSec, display);
			}else {
				startTimer(leftSec, display);
			}
		},
		error: function(err){
			console.log(err);
		}
	});
}

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
				$('#codeNum').hide();
				
				$('.desc').html('고객님의<br>비밀번호 찾기가 완료되었습니다!');
				$('.info').html('비밀번호: ' + pwd);
				$('#btnText').html('로그인 하기');
				$('a.btn_type1').prop('href', '/jaju/member/loginForm');
				$('#getPwdDiv').show();
			}else{
				$('#emailDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
				$('#emailDiv').html('인증번호를 다시 확인 하세요.');
			}
		},
		error: function(err){
			console.log(err);
		}
	});
});
