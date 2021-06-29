//불러오기
$(function(){

	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/getSaleboardView',
		data: {'sale_seq': $('#sale_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('#sale_subject').val(data.saleboardDTO.sale_subject);
			
			$('#sale_price').val(data.saleboardDTO.sale_price);
			
			if(data.saleboardDTO.sale_nego == 'on') {
				$("#sale_nego").attr("checked", true); 
			}
			
			$('#sale_category').val(data.saleboardDTO.sale_category);
			
			if(data.saleboardDTO.sale_condition == '새상품') {
				$('#new').attr("checked", true);
				
			}else if(data.saleboardDTO.sale_condition == '거의 새것') {
				$('#almostNew').attr("checked", true);
				
			}else if(data.saleboardDTO.sale_condition == '사용감있음') {
				$('#used').attr("checked", true);
				
			}else if(data.saleboardDTO.sale_condition == '사용감많음') {
				$('#veryused').attr("checked", true);
				
			}			
			
			var howTrade;
			if(data.saleboardDTO.sale_direct == 'on') {
				$("#sale_direct").attr("checked", true); 
			}
			if(data.saleboardDTO.sale_delivery == 'on') {
				$("#sale_delivery").attr("checked", true); 
			}
			if(data.saleboardDTO.sale_onlineTran == 'on') {
				$("#sale_onlineTran").attr("checked", true); 
			}
			$('#howTrade .desc').val(howTrade);
			
			$('#sale_content').val(data.saleboardDTO.sale_content);
			
			
			let map = new Map();
			
			map.set("image1", data.saleboardDTO.sale_image1);
			map.set("image2", data.saleboardDTO.sale_image2);
			map.set("image3", data.saleboardDTO.sale_image3+''); //null값 방지
			map.set("image4", data.saleboardDTO.sale_image4+'');
			map.set("image5", data.saleboardDTO.sale_image5+'');
			map.set("image6", data.saleboardDTO.sale_image6+'');
			map.set("image7", data.saleboardDTO.sale_image7+'');
			map.set("image8", data.saleboardDTO.sale_image8+'');
			map.set("image9", data.saleboardDTO.sale_image9+'');
			map.set("image10", data.saleboardDTO.sale_image10+'');
			
			for(var i=1; i<=10; i++){
				if(map.get('image'+i) != 'null'){
					$('#imageTd').append($('<img/>',{
						width:"80",
						height:"80",
						class:"miniImg",
						src: '/jaju/storage/'+map.get('image'+i)
					}));
				}
			}
		},
		error: function(err){
			console.log(err);
		}
		
	});
	
});


$('#saleboardModifyBtn').click(function(){
	
	var formData = new FormData($('#saleboardModifyForm')[0]); //form 안에 있는 모든 것 다 넘김
	
	//유효성 검사
	$('#sale_subjectDiv').empty();
	$('#sale_priceDiv').empty();
	$('#sale_categoryDiv').empty();
	$('#sale_conditionDiv').empty();
	$('#sale_directDiv').empty();
	$('#sale_deliveryDiv').empty();
	$('#sale_onlineTranDiv').empty();
	$('#sale_contentDiv').empty();
	
	var whatcondition = document.getElementsByName('sale_condition'); //라디오박스
	
	var howdelivery = document.getElementsByName('howDelivery'); //체크박스
	var num = 0;
	
	//배송방법
	for(var i = 0; i<howdelivery.length; i++) {
		if(howdelivery[i].checked) {
			num++;
		}
	}
	if(!num) { //처음부터 나옴, 먹히긴 먹힌다
		$('#sale_directDiv').text('배송 방법을 선택해주세요');
		$('#sale_directDiv').css('color', 'red');
		$('#sale_directDiv').css('font-size', '10pt');
		$('#sale_directDiv').css('font-weight', 'bold');
	}
	
	if($('#sale_subject').val() == ''){
        $('#sale_subjectDiv').text('제목을 입력하세요');
        $('#sale_subjectDiv').css('color', 'red');
        $('#sale_subjectDiv').css('font-size', '10pt');
        $('#sale_subjectDiv').css('font-weight', 'bold');
	}
	else if($('#sale_price').val() == ''){
        $('#sale_priceDiv').text('가격을 입력하세요');
        $('#sale_priceDiv').css('color', 'red');
        $('#sale_priceDiv').css('font-size', '10pt');
        $('#sale_priceDiv').css('font-weight', 'bold');
	}
	else if($('#sale_category').val() == ''){
		$('#sale_categoryDiv').text('카테고리를 선택해주세요');
		$('#sale_categoryDiv').css('color', 'red');
        $('#sale_categoryDiv').css('font-size', '10pt');
        $('#sale_categoryDiv').css('font-weight', 'bold');
	}else if($('input[name=sale_condition]:radio:checked').length==0){ //선택해도 안 없어짐
		$('#sale_conditionDiv').text('상품 상태를 선택해주세요');
		$('#sale_conditionDiv').css('color', 'red');
        $('#sale_conditionDiv').css('font-size', '10pt');
        $('#sale_conditionDiv').css('font-weight', 'bold');
	}else if($('#sale_content').val() == ''){
		$('#sale_contentDiv').text('내용을 입력하세요');
		$('#sale_contentDiv').css('color', 'red');
		$('#sale_contentDiv').css('font-size', '10pt');
		$('#sale_contentDiv').css('font-weight', 'bold');
	}else{
	
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/saleboardModify',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data) {
			alert("게시글수정 완료");
			location.href='/jaju/saleboard/saleboardList?pg=1&sortinSelect=sale_date';
		},
		error: function(err){
			console.log(err);
		}
	});
	}
});