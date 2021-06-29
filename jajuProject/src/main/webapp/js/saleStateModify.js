//불러오기
$(function(){
	$('#change_state_complete').hide();
	$('#saleboardModifyTable tr:eq(1)').hide();
	
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/getSaleboardView',
		data: 
		{
			'sale_seq': $('#sale_seq').val()
			
		},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			//판매상태, 제목, 판매가격, 가격제한허용, 카테고리, 상품상태(새상품, 거의 새것, 사용감 있음, 사용감많음), 배송방법(직거래, 택배거래, 온라인거래), 내용, 이미지)
			if(data.saleboardDTO.sale_state=='판매완료'){
				$('#change_state').hide();
				$("#sale_state").attr("disabled","disabled");
				$('#saleboardModifyTable tr:eq(1)').show();
				$('#change_state').hide();
				$('#sale_category').hide();
				$('#saleBuyerBtn').hide();
				$('.click_to_change_inform').hide();
			}
			
			//판매상태 가져오기 sale_state
			$('#sale_state').val(data.saleboardDTO.sale_state);
			
			$('#sale_subject').html(data.saleboardDTO.sale_subject);

			$('#sale_price').html(data.saleboardDTO.sale_price.toLocaleString()+" 원");
			
			if(data.saleboardDTO.sale_nego == 'on') {
				$("#sale_nego").html("/ 가격제안 허용"); 
			}else{
				$("#sale_nego").html("/ 가격제안 허용불가"); 
			}
			
			$('#sale_category').html(data.saleboardDTO.sale_category);

			$('#sale_condition').html(data.saleboardDTO.sale_condition);

			var howTrade;
			if(data.saleboardDTO.sale_direct == 'on') {
				$('#sale_method').html("직거래");
			}
			if(data.saleboardDTO.sale_delivery == 'on') {
				$('#sale_method').html("택배 거래");
			}
			if(data.saleboardDTO.sale_onlineTran == 'on') {
				$('#sale_method').html("온라인 거래");
			}
			$('#howTrade .desc').val(howTrade);
			
			$('#sale_content').html(data.saleboardDTO.sale_content);
			
		},
		error: function(err){
			console.log(err);
		}
		
	});
	
});
$('#saleBuyer').click(function(){
	$('#saleBuyer').hide();
	$('#sale_category').show();
	$('#saleBuyerBtn').show();
});
$('#change_state').click(function(){
	$.ajax({
		url:'/jaju/saleboard/saleStateModify',
		type:'post',
		data:
		{
			'sale_seq': $('#sale_seq').val(),
			'sale_state':$('#sale_state').val()
		},	
		success:function(data){
			
			alert("판매 상태가 수정 되었습니다.");
			
		},error:function(err){
			console.log("saleStateModify.js error 발생 ");
		}
	});//ajax
});//change_state click function