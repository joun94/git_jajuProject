$('#find_idBtn').click(function(){
	$('#search_nameDiv').empty();
	$('#search_emailDiv').empty();
	
	if($('#search_name').val() == ''){
		$('#search_nameDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#search_nameDiv').html('이름을 입력해주세요');
	}else if($('#search_email').val() == ''){
		$('#search_emailDiv').css({'color': '#b3130b', 'font-size': '8pt', 'font-weight': 'bold'});
		$('#search_emailDiv').html('이메일을 입력해주세요');
	}else{
		$.ajax({
			url: '/jaju/member/getId',
			type: 'post',
			data: {
				'search_name': $('#search_name').val(),
				'search_email': $('#search_email').val()
			},
			dataType: 'text',
			success: function(data){
				//alert(data)
				if(data == 'fail'){
					$('#form').hide();
					$('.desc').html('고객님께서 입력하신 정보가<br>정확한지 확인 후 다시 시도해주세요.');
					$('#btnText').html('아이디 다시 찾기');
					$('a.btn_type1').prop('href', '/jaju/member/find_id');
					$('#getIdDiv').show();
				}else {
					$('#form').hide();
					$('.desc').html('고객님의<br>아이디 찾기가 완료되었습니다!');
					$('.info').html('아이디: ' + data);
					$('#btnText').html('로그인 하기');
					$('a.btn_type1').prop('href', '/jaju/member/loginForm');
					$('#getIdDiv').show();
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});