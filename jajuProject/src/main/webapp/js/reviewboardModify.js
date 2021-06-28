

//$(function(){
//   alert($('#sale_seq').val())
//   $.ajax({
//      type: 'post',
//      url: '/jaju/saleboard/getSaleboardViewReview',
//      data: {'sale_seq': $('#sale_seq').val()},
//      dataType: 'json',
//      success: function(data){
//         //alert(JSON.stringify(data));
//         $('#buy_product').text(data.saleboardDTO.sale_subject);
//         $('#member_id').val(data.saleboardDTO.member_id);
//         $('#sale_image1').val(data.saleboardDTO.sale_image1);         
//         $('#sale_subject').val(data.saleboardDTO.sale_subject)
//      },error: function(err){
//         alert("판매제목 띄우기 에러2");
//         console.log(err);
//      }
//      
//      
//   });
   
//});

//불러오기
$(function(){

	$.ajax({
		type: 'post',
		url: '/jaju/reviewboard/getReviewboardView',
		data: {'review_seq': $('#review_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data)); //자료가 오긴 온다

			$('#sale_seq').val(data.reviewboardDTO.sale_seq);
			$('#review_subject').val(data.reviewboardDTO.review_subject);
			$('#review_manner').val(data.reviewboardDTO.review_manner);
			$('#review_content').val(data.reviewboardDTO.review_content);
			
			let map = new Map();
			
			map.set("image1", data.reviewboardDTO.review_image1+'');
			map.set("image2", data.reviewboardDTO.review_image2+'');
			map.set("image3", data.reviewboardDTO.review_image3+''); //null값 방지
			
			for(var i=1; i<=3; i++){
				if(map.get('image'+i) != 'null'){
					$('#imageTd').append($('<img/>',{
						width:"300",
						height:"300",
						class:"review_boardImg",
						src: '/jaju/storage/'+map.get('image'+i)
					}));
				}
			}
		},
		error: function(err){
			alert("수정 불러오기 에러");
			console.log(err);
		}
		
	});
	
});

$('#reviewboardModifyBtn').click(function(){
	var formData = new FormData($('#reviewboardModifyForm')[0]);
	
	//유효성 검사
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
		url: '/jaju/reviewboard/reviewboardModify',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data) {
			//alert(JSON.stringify(data));
			alert("게시글수정 완료");
			location.href='/jaju/reviewboard/reviewboardList?pg=1';
			
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
	   } //else의 괄호
	
});
