//수정할 글 불러오기
$(function(){
	$('.image1Name').hide();
	$('.image2Name').hide();
	$('.image3Name').hide();
	
	var formData = new FormData($('#reportModifyForm')[0])
	$.ajax({
		type: 'post',
		url: '/jaju/serviceCenter/getReport',
		data: {'report_seq' : $('#report_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			$('#reportType').val(data.reportDTO.reportType).prop('select', true);
			$('#report_subject').val(data.reportDTO.report_subject);
			$('#sale_id').val(data.reportDTO.sale_id);
			$('#sale_subject').val(data.reportDTO.sale_subject);
			//신고 글 제목은 sale_seq 값을 가지고 와서 불러온다.
			$('#report_tal').val(data.reportDTO.report_tal);
			$('#report_content').text(data.reportDTO.report_content);
			
			if (data.reportDTO.report_image1 != null) {
				$('#image1Name').text(data.reportDTO.report_image1)
				$('#report_image1').val(data.reportDTO.report_image1)
				$('.image1Name').show();
	
			}
			if (data.reportDTO.report_image2 != null) {
				$('#image2Name').text(data.reportDTO.report_image2)
				$('#report_image2').val(data.reportDTO.report_image2)
				$('.image2Name').show();
	
			}

			if (data.reportDTO.report_image3 != null) {
				$('#image3Name').text(data.reportDTO.report_image3)
				$('#report_image3').val(data.reportDTO.report_image3)
				$('.image3Name').show();
				
			}

		},
		error: function(err){
	         console.log(err);
	    }
	
	});
});

//수정
$('#bhs_button').click(function(){
	$('#itemcdDiv').empty();
	$('#subjectDiv').empty();
	$('#mobileDiv').empty();
	$('#contentsDiv').empty();
	
	if($('#reportType').val() == ''){
		$('#itemcdDiv').text('신고 유형을 선택해주세요');
		$('#itemcdDiv').css('color', 'red');
        $('#itemcdDiv').css('font-size', '10pt');
        $('#itemcdDiv').css('font-weight', 'bold');
	}else if($('#report_subject').val() == ''){
        $('#subjectDiv').text('제목을 입력하세요');
        $('#subjectDiv').css('color', 'red');
        $('#subjectDiv').css('font-size', '10pt');
        $('#subjectDiv').css('font-weight', 'bold');
	}else if($('#report_tal').val() == ''){
		$('#mobileDiv').text('전화번호를 입력하세요');
		$('#mobileDiv').css('color', 'red');
		$('#mobileDiv').css('font-size', '10pt');
		$('#mobileDiv').css('font-weight', 'bold');
	}else if($('#report_content').val() == ''){
		$('#contentsDiv').text('내용을 입력하세요');
		$('#contentsDiv').css('color', 'red');
		$('#contentsDiv').css('font-size', '10pt');
		$('#contentsDiv').css('font-weight', 'bold');
	}else{
		
		var formData = new FormData($('#reportModifyForm')[0]);
		$.ajax({
			type:'post',
			url:'/jaju/serviceCenter/reportModify',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
		 	data: formData,
			
			success: function(data){
					alert('수정이 완료되었습니다');
					location.href='/jaju/serviceCenter/reportList';
			},
			error: function(err){
					console.log(err);
					alert('수정에 실패하였습니다');
			}
		});
	}
	
});

//휴대폰 번호 유효성 검사
$(function(){
	$(".read_only").on('keydown', function(e){
	
	//숫자만 입력받기
	var pattern = $(this).val().replace(/-/gi,'');
	var num = e.keyCode;
				
	if(pattern.length >= 11 && ((num >= 48 && num <=126) || (num >= 12592 && num <= 12687 || num==32 || num==229 || (num>=45032 && num<=55203)) )){
  	    e.preventDefault();
	}
	
	}).on('blur', function(){ //포커스 아웃일때 실행
        if($(this).val() == '') return;

        //기존 번호에서 -를 삭제
        var pattern = $(this).val().replace(/-/gi,'');
      
        //입력값이 있을때만 실행
        if(pattern != null && pattern != ''){
        	
            //총 핸드폰 자리수는 11글자이거나, 10자여야 한다.
            if(pattern.length==11 || pattern.length==10){   
                
            	//유효성 체크
                var phone_num = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
                if(phone_num.test(pattern)){
					//유효성 체크에 성공하면 -을 넣고 값을 바꿔준다.
					pattern = pattern.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/, "$1-$2-$3");
					$(this).val(pattern);
                }else{
                    alert("유효하지 않은 전화번호 입니다");
                    $(this).val("");
                    $(this).focus();
                }
            }else {
                alert("유효하지 않은 전화번호 입니다");
                $(this).val("");
                $(this).focus();
            }//else
            	
		}//if
	});  
});