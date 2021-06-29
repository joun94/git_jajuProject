//불러오기
$(function(){
	$('#change_state_complete').hide();
	$('#saleboardModifyTable tr:eq(1)').hide();
	
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/getSaleboardView',
		async : false,
		data: 
		{
			'sale_seq': $('#sale_seq').val()
			
		},  
		dataType: 'json',
		
		success: function(data){
			
		//	alert(JSON.stringify(data));
			//판매상태, 제목, 판매가격, 가격제한허용, 카테고리, 상품상태(새상품, 거의 새것, 사용감 있음, 사용감많음), 배송방법(직거래, 택배거래, 온라인거래), 내용, 이미지)
			if(data.saleboardDTO.sale_state=='판매완료'){
				$('#change_state').hide();
				$("#sale_state").attr("disabled","disabled");
				$('#saleboardModifyTable tr:eq(1)').show();
				$('#change_state').hide();
				$('#select_buyer').hide();
				$('#saleBuyerBtn').hide();
				$('.click_to_change_inform').hide();
			}
			
			//판매상태 가져오기 sale_state
			$('#sale_state').val(data.saleboardDTO.sale_state);
			
			$('#sale_subject').html(data.saleboardDTO.sale_subject);

			$('#sale_price').html(data.saleboardDTO.sale_price.toLocaleString()+" 원");
			
			//$("#sale_category").val("구매자선택");

			//$("#sale_category > option[value=구매자선택]").attr("selected", "true");

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
			
			$('#sale_image').attr('src','/jaju/storage/'+data.saleboardDTO.sale_image1);
			$('#sale_content').html(data.saleboardDTO.sale_content);
			
		},
		error: function(err){
			console.log(err);
		}
		
	});
	
	
	
	
	//구매자 불러오기
	
		$('#saleBuyer').hide(); //구매자 불러오기 버튼 숨김
		//$('#sale_tr').hide();
		$('#saleBuyer').show(); // 판매상태가  '판매완료'로 update 성공한경우에만 활성화시키기

		$('#saleBuyer').click(function() {

			$.ajax({
				type : 'post',
				url : '/jaju/saleboard/salebuyerFindMessage',
				data : {
					'sale_seq' : $('#sale_seq').val()
				},
				dataType : 'json',
				async : false,
				success : function(data) {
					console.log(JSON.stringify(data));
					//$('#sale_category').append()

					$.each(data.list, function(index, items) {

						$('#select_buyer').append($('<option/>', {
							value : items.MESSAGE_WRITER,
							text : items.MESSAGE_WRITER,
							id : 'sale_buyer'
						})).appendTo($('#buyer_list'));

					});//each

				},
				error : function() {
					console.log("salebuyer1 error발생+err")
				}
			});//ajax

			$.ajax({
				type : 'post',
				url : '/jaju/saleboard/salebuyerFindComment',
				data : {
					'sale_seq' : $('#sale_seq').val()
				},
				dataType : 'json',
				async : false,
				success : function(data) {
					console.log(JSON.stringify(data));
					//$('#sale_category').append()

					$.each(data.list, function(index, items) {

						$('#select_buyer').append($('<option/>', {
							id : 'sale_buyer',
							value : items.MEMBER_ID,
							text : items.MEMBER_ID

						})).appendTo($('#buyer_list'));

					});//each
					alert("구매하신 분의 ID를 선택하고 완료버튼을 누르세요.")
					$('#select_buyer').show();
					$('#saleBuyerBtn').show(); //구매자 확정버튼 보이기 

				},
				error : function() {
					console.log("salebuyer2 error발생+err")
				}
			});//ajax

		});

		$('#saleBuyerBtn').click(function() {

			console.log($("select[name=select_buyer]").val());
			console.log($('#sale_seq').val());
			
			if($("#select_buyer option:checked").text()=='구매자선택'){
				alert("구매자 ID를 선택해주세요.");
				return false;
			}else{
				

			$.ajax({
				type : 'post',
				url : '/jaju/saleboard/salebuyerConfirmation',
				data : {
					'sale_seq' : $('#sale_seq').val(),
					'sale_buyer' : $("select[name=select_buyer]").val()

				},
				//dataType: 'json',
				async : false,
				success : function() {
					//console.log(JSON.stringify(data));
					alert("구매자아이디 등록완료");
					$('#saleBuyerBtn').hide();
				},
				error : function() {
					console.log("salebuyer3 error발생+err")
				}
			});//ajax
			
			

			}//else
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
		async : false,
		data:
		{
			'sale_seq': $('#sale_seq').val(),
			'sale_state':$('#sale_state').val()
		},	
		success:function(data){
			//alert($('#sale_seq').val())
			//alert($('#sale_state').val())
			 alert("판매 상태가 수정 되었습니다.");
	         window.opener.parent.location.reload(); 
	         //window.self.close(); 
			
		},error:function(err){
			console.log("saleStateModify.js error 발생 ");
		}
	});//ajax
});//change_state click function