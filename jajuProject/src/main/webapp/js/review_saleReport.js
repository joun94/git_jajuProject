$(function(){
	
	$.ajax({
		type: 'post',
		url : '/jaju/reviewboard/review_saleReportList',
		//data: 'pg=${pg}', //컨트롤러부터 넘어온 값이고 
		data: 
		{
			'pg':$('#pg').val(),
			'id':$('#id').val()
		},  // --->hidden의 값을 쓸때
		dataType: 'json',
		success: function(data){
			//console.log(JSON.stringify(data));
			
//			if(JSON.stringify(data.list)=='[]' && data.myRecodePaging2 != null){
//				
//				$('.title').remove();
//				
//				$('<tr/>',{style:'height:250px;'}).append($('<td/>',{
//					align:'center',
//					text:'구매한 내역이 없습니다.',
//					style:'height:250px;'
//				})).appendTo($('#myBuyRecordTable'));
//			}
//			else{
			
			$.each(data.list, function(index, items){
				
				$('<ul/>',{class:'list_order'}).
						append($('<li/>').
						append($('<div/>',{class:'date',})).
						append($('<div/>',{class:'order_goods'}).
						append($('<div/>',{class:'deal_name',}).append($('<a/>',{href:'#',text:items.sale_subject}))).
						append($('<div/>',{class:'order_info'}).
						append($('<div>',{class:'thumb'}).append($('<img/>',{src:'/jaju/storage/'+items.sale_image1, alt:'img',style:'text_align:left;'}))).
						append($('<div/>',{class:'desc'}).
						append($('<dl/>').append($('<dt/>',{text:'가격'})).append($('<dd/>',{text:items.sale_price.toLocaleString()}))).
						append($('<dl/>').append($('<dt/>',{text:'날짜'})).append($('<dd/>',{text:items.sale_date}))).
						append($('<dl/>').append($('<dt/>',{text:'주문상태'})).append($('<dd/>',{text:items.sale_state,class:'status end'})).
								append($('<button/>',{class:'state_'+items.sale_seq,id:"change_state_btn",text:' 리뷰작성', style:'height: 30px;width: 90px; background-color: #617aad;color: #fff;border: none;border-radius: 3px; float:right;'
								}))))))).
						appendTo($('#tabTable'));
				
				//리뷰 작성 이라는 버튼 누르면 리뷰작성 폼으로 이동한다.. click 이벤트
				$('.state_'+items.sale_seq).click(function(){
					
					location.href='/jaju/reviewboard/reviewboardWriteForm?sale_seq='+items.sale_seq;
				});
			});//each

			
			//페이징처리
			$('#myBuyRecodePagingDiv').html(data.reviewboardPaging.pagingHTML);
//		}//else
	
		},
		error: function(err){
			console.log('buyList에러발생'+err);
		}
	});
});

//function myRecodePaging(pg){
//	location.href="myBuyRecode?pg="+pg;
//}
