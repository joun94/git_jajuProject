$(document).on('click','.file',function(){
	
	//alert($("input[name=file]").eq(1).val());

});

$('#saleboardWriteBtn').click(function(){
	var formData = new FormData($('#saleboardWriteForm')[0]); //form 안에 있는 모든 것 다 넘김

	//유효성검사
	$('#sale_subjectDiv').empty();
	$('#sale_priceDiv').empty();
	$('#sale_categoryDiv').empty();
	$('#sale_conditionDiv').empty();
	$('#sale_directDiv').empty();
	$('#sale_deliveryDiv').empty();
	$('#sale_onlineTranDiv').empty();
	$('#sale_contentDiv').empty();
	$('#sale_imageDiv').empty();
	
	var whatcondition = document.getElementsByName('sale_condition'); //라디오박스
	
	if($('#sale_subject').val() == ''){
        $('#sale_subjectDiv').text('제목을 입력하세요');
        $('#sale_subjectDiv').css('color', 'red');
        $('#sale_subjectDiv').css('font-size', '10pt');
        $('#sale_subjectDiv').css('font-weight', 'bold');
	}else if($('#sale_price').val() == ''){
        $('#sale_priceDiv').text('가격을 입력하세요');
        $('#sale_priceDiv').css('color', 'red');
        $('#sale_priceDiv').css('font-size', '10pt');
        $('#sale_priceDiv').css('font-weight', 'bold');
	}else if($('#sale_category').val() == ''){
		$('#sale_categoryDiv').text('카테고리를 선택해주세요');
		$('#sale_categoryDiv').css('color', 'red');
        $('#sale_categoryDiv').css('font-size', '10pt');
        $('#sale_categoryDiv').css('font-weight', 'bold');
	}else if($('input[name=sale_condition]:radio:checked').length==0){
		$('#sale_conditionDiv').text('상품 상태를 선택해주세요');
		$('#sale_conditionDiv').css('color', 'red');
        $('#sale_conditionDiv').css('font-size', '10pt');
        $('#sale_conditionDiv').css('font-weight', 'bold');   
	}else if(!$("input:checked[id='sale_direct']").is(":checked") && !$("input:checked[id='sale_delivery']").is(":checked") && !$("input:checked[id='sale_onlineTran']").is(":checked")){
    		$('#sale_directDiv').text('배송방법을 선택해주세요');
    		$('#sale_directDiv').css('color', 'red');
            $('#sale_directDiv').css('font-size', '10pt');
            $('#sale_directDiv').css('font-weight', 'bold');
	}else if($('#sale_content').val() == ''){
            $('#sale_contentDiv').text('내용을 입력하세요');
            $('#sale_contentDiv').css('color', 'red');
            $('#sale_contentDiv').css('font-size', '10pt');
            $('#sale_contentDiv').css('font-weight', 'bold');
	}else if($("input[name=file]").eq(0).val() == ''){
		$('#sale_imageDiv').text('이미지를 삽입해주세요');
		$('#sale_imageDiv').css('color', 'red');
		$('#sale_imageDiv').css('font-size', '10pt');
		$('#sale_imageDiv').css('font-weight', 'bold');
	}else if($("input[name=file]").eq(1).val() == ''){
		$('#sale_imageDiv').text('이미지를 2장 이상 삽입해주세요');
		$('#sale_imageDiv').css('color', 'red');
		$('#sale_imageDiv').css('font-size', '10pt');
		$('#sale_imageDiv').css('font-weight', 'bold');
	}else{
		
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/saleboardWrite',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data) {
			alert("게시글 등록 완료");
			location.href='/jaju/saleboard/index';
		},
		error: function(err){
			console.log(err);
		}
	});
	}
});