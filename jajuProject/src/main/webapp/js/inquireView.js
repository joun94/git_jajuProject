function mode(num){
	if(num == 1){
		document.inquireView.method="post";
		document.inquireView.action="/jaju/serviceCenter/inquireModifyForm";
		document.inquireView.submit();
	}else if(num == 2){
		if(confirm("정말로 삭제하시겠습니까?")){
		document.inquireView.method="post";
		document.inquireView.action="/jaju/serviceCenter/inquireDelete";
		document.inquireView.submit();
		}
	}
}

//1:1문의 view띄우기
$(function(){
	$.ajax({
		type : 'post',
		url : '/jaju/serviceCenter/getInquireView',
		data : 'seq='+$('#inquiry_seq').val(),
		dataType : 'json',
		success : function(data){
			console.log(data);
			
			$('#inquiry_subject').text(data.inquireDTO.inquiry_subject);
			$('#inquiry_id').text(data.inquireDTO.inquiry_id);
			$('#logtime').text(data.inquireDTO.logtime);
			$('#inquiry_telephone').text(data.inquireDTO.inquiry_telephone);
			$('#inquiry_contents').text(data.inquireDTO.inquiry_contents);
			if(data.inquireDTO.inquiry_image1 != null){
				$('#inquiry_image1').attr('src','/jaju/storage/'+data.inquireDTO.inquiry_image1);
				$('#inquiry_image1').css({'width': '150px', 'height': '150px'});
			}
			if(data.inquireDTO.inquiry_image2 != null){
				$('#inquiry_image2').attr('src','/jaju/storage/'+data.inquireDTO.inquiry_image2);
				$('#inquiry_image2').css({'width': '150px', 'height': '150px'});
			}
			if(data.inquireDTO.inquiry_image3 != null){
				$('#inquiry_image3').attr('src','/jaju/storage/'+data.inquireDTO.inquiry_image3);
				$('#inquiry_image3').css({'width': '150px', 'height': '150px'});
			}
		},
		error : function(err){
			console.log(err);
		}
	});
});
