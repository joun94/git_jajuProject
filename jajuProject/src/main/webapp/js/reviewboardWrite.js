//화면 뜨면 구매한 물건에 sale_subject가 떠야한다 (가능하다면 sale_image1도)
$(function(){
   //alert($('#sale_seq').val())
   $.ajax({
      type: 'post',
      url: '/jaju/saleboard/getSaleboardViewReview',
      data: {'sale_seq': $('#sale_seq').val()},
      dataType: 'json',
      success: function(data){
         //alert(JSON.stringify(data));
         $('#buy_product').text(data.saleboardDTO.sale_subject);
         $('#member_id').val(data.saleboardDTO.member_id);
         $('#sale_image1').val(data.saleboardDTO.sale_image1);         
         $('#sale_subject').val(data.saleboardDTO.sale_subject)
      },error: function(err){
         alert("판매제목 띄우기 에러");
         console.log(err);
      }
      
      
   });
   
});

$('#reviewboardWriteBtn').click(function(){
   var formData = new FormData($('#reviewboardWriteForm')[0]); //form 안에 있는 모든 것 다 넘김
   
   //유효성검사
   $('#review_subjectDiv').empty();
   $('#review_mannerDiv').empty();
   $('#review_contentDiv').empty();
   $('#review_imageDiv').empty();
   
   if($('#review_subject').val() == ''){
      $('#review_subjectDiv').text('제목을 입력하세요');
      $('#review_subjectDiv').css('color', 'red');
      $('#review_subjectDiv').css('font-size', '10pt');
      $('#review_subjectDiv').css('font-weight', 'bold');
   }else if($('#review_manner').val() == ''){
        $('#review_mannerDiv').text('평점을 선택하세요');
        $('#review_mannerDiv').css('color', 'red');
        $('#review_mannerDiv').css('font-size', '10pt');
        $('#review_mannerDiv').css('font-weight', 'bold');
   }else if($('#review_content').val() == ''){
        $('#review_contentDiv').text('내용을 입력하세요');
        $('#review_contentDiv').css('color', 'red');
        $('#review_contentDiv').css('font-size', '10pt');
        $('#review_contentDiv').css('font-weight', 'bold');
   }else{

	   $.ajax({
			type: 'post',
			url: '/jaju/reviewboard/reviewboardWrite',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
				
				alert("게시글 등록 완료");
				location.href='/jaju/reviewboard/index';
				
				
				$.ajax({
					type: 'post',
					url: '/jaju/reviewboard/getAllReviewboardList',
					data: {'member_id':$('#member_id').val()},
					dataType: 'json',
					success: function(data){
						//alert(JSON.stringify(data));
						
						var review_manner = 36.5
						var count = 1;
						
						$.each(data.list, function(index,items){
							review_manner = review_manner + parseFloat(items.review_manner);
							count = count + 1;
						}); //each
						
						$.ajax({
							type: 'post',
							url: '/jaju/reviewboard/review_mannerModify',
							data: {'member_id':$('#member_id').val(), 'member_manner': review_manner/count},
							dataType: 'json',
							success: function(data){
								
								
							}, error: function(err){
								console.log(err);
								alert('매너점수 변경 에러')
							}
						});
						
					}, error: function(err){
						console.log(err);
						alert('매너점수 변경 에러')
					}
				});
				
				
			},
			error: function(err){
				console.log(err);
			}
		});
	}
		
		//alert($('#member_id').val())
      
});