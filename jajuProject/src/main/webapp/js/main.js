function logout(){
	if (Kakao.Auth.getAccessToken()) {
		Kakao.Auth.logout(function() {
			console.log(Kakao.Auth.getAccessToken());
		});
	}
	
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
}