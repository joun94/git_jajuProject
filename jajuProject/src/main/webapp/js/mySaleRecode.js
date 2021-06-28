$(function(){
	$.ajax({
		type: 'post',
		url : '/jaju/mypage/mySaleRecodeList',
		//data: 'pg=${pg}', //컨트롤러부터 넘어온 값이고 
		data: 		
		{
			'pg':$('#pg').val(),
			'id':$('#id').val()
		},   // --->hidden의 값을 쓸때
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			
			if(JSON.stringify(data.list)=='[]' && data.myRecodePaging != null){
				
				$('.title').remove();
				
				$('<tr/>').append($('<td/>',{
					align:'center',
					text:'판매 완료 된 내역이 없습니다.',
					style:'height:250px;'
				})).appendTo($('#mySaleRecordTable'));
			}
			else{
				
			
			$.each(data.list, function(index, items){

				$('<ul/>',{
					class:'list_order'
					}).append($('<li/>')
						.append($('<div/>',{
							class:'date',
						})).append($('<div/>',{
								class:'order_goods'
							}).append($('<div/>',{
								class:'deal_name',
							}).append($('<a/>',{
								href:'#',text:items.sale_subject
							}))
							).append($('<div/>',{
								class:'order_info'
								
							}).append($('<div>',{
								class:'thumb'
							}).append($('<img/>',{
								src:'/jaju/storage/'+items.sale_image1, alt:'img',
								style:'text_align:left;'
							}))).append($('<div/>',{
								class:'desc'
							}).append($('<dl/>')
							.append($('<dt/>',{
								text:'가격'
								})).append($('<dd/>',{
									text:items.sale_price.toLocaleString()+" 원"
								}))).append($('<dl/>')
								.append($('<dt/>',{
									text:'날짜'
								})).append($('<dd/>',{
									text:items.sale_date
								}))).append($('<dl/>')
								.append($('<dt/>',{
									text:'주문상태'
								})).append($('<dd/>',{
									text:items.sale_state,
									class:'status end'	
						}))))))).appendTo($('#tabTable'));
				
			});//each
			
			//페이징처리
			$('#mySaleRecodePagingDiv').html(data.myRecodePaging.pagingHTML);
			
			}//else

		},
		error: function(err){
			console.log('saleList에러발생'+err);
		}
	});
});

function myRecodePaging(pg){
	location.href="mySaleRecode?pg="+pg;
}
