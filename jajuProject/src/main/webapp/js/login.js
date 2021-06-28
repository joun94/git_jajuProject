$(function(){
	$('.sign_in_item a').css('color', '#064acb')
	
	var cookieId = getCookie('cookieId');
	$('#member_id').val(cookieId);
	
	if($('#member_id').val() != ''){
		$('.label_checkbox').addClass('checked');
	}
	
	$('#chk_save').change(function(){
		if($(this).is(':checked')){
			setCookie('cookieId', $('#member_id').val(), 15);
		}else{
			deleteCookie('cookieId');
		}
	});
	
	$('#member_id').change(function(){
		if($('#chk_save').is(':checked')){
			setCookie('cookieId', $('#member_id').val(), 15);
		}
	});
});

function setCookie(cookieName, value, exdays){
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	
	var cookieValue = escape(value) + ((exdays == null) ? '':'; expires=' + exdate.toGMTString());
	document.cookie = cookieName + '=' + cookieValue;
}

function deleteCookie(cookieName){
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + '=' + '; expires=' + expireDate.toGMTString();
}

function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}

$('#loginBtn').click(function(){
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	
	if($('#member_id').val() == ''){
		$('#idDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#idDiv').html('아이디를 입력해주세요');
	}else if($('#member_pwd').val() == ''){
		$('#pwdDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#pwdDiv').html('비밀번호를 입력해주세요');
	}else{
		$.ajax({
			url: '/jaju/member/login',
			type: 'post',
			data: $('#form').serialize(),
			dataType: 'text',
			success: function(data){
				if(data == 'success'){
					location.href="/jaju/";
				}else{
					alert('아이디 또는 비밀번호를 확인해주세요')
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

function loginWithKakao() {
	var lat = 0;
	var lng = 0;
	
	if (navigator.geolocation) {
		// GeoLocation을 이용해서 접속 위치를 얻어옵니다
		navigator.geolocation.getCurrentPosition(function(position) {
		
		lat = position.coords.latitude, // 위도
		lng = position.coords.longitude; // 경도
		
		});
	}
	 
	Kakao.Auth.login({
		scope: 'account_email',	
		success: function (response) {
			Kakao.API.request({
				url: '/v2/user/me',
				success: function (response) {
					//console.log(response)
					
					$.ajax({
						url: '/jaju/member/kakao_login',
						type: 'post',
						data: {
							'member_id': response.id,
							'member_email': response.kakao_account.email,
							'member_name': response.kakao_account.profile.nickname,
							'lat': lat,
							'lng': lng
						},
						success: function(){
							location.href='/jaju/'
						},
						error: function(err){
							console.log(err)
						}
					});
				},
				fail: function (error) {
					console.log(error)
				},
			})
		},
		fail: function (error) {
			console.log(error)
		},
	})
}
