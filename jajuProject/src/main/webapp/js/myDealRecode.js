$(function(){
	//alert($('#id').val());

	$.ajax({
		type: 'post',
		url : '/jaju/mypage/myDealRecodeList',
		// data: 'pg=${pg}', //컨트롤러부터 넘어온 값이고
		data: 
		{
			'pg':$('#pg').val(),
			'id':$('#id').val()
					
		}, // --->hidden의 값을 쓸때
		dataType: 'json',
		success: function(data){
			
			console.log(JSON.stringify(data));
			
			if(JSON.stringify(data.list)=='[]' && data.myRecodePaging3 != null){
				$('.title').remove();
				
				$('<tr/>').append($('<td/>',{
					align:'center',
					text:'판매중인 내역이 없습니다.',
					style:'height:250px;'
				})).appendTo($('#myDealRecordTable'));
			}
			
			else {
			$.each(data.list, function(index, items){
	
				/*
				 * $('<div/>').append($('<div/>',{ class: 'RecodeMain', style:
				 * 'text-align:left'
				 * 
				 * }).append($('<a/>',{ href:'#',
				 * 
				 * }).append($('<label>',{ style: 'cursor:pointer;padding: 20px
				 * 0 13px; border-bottom: 1px solid #dddfe1; width: 100%;',
				 * text: items.sale_subject })))
				 * 
				 * ).append($('<div/>',{ class: 'order_info' }).append($('<div/>',{
				 * class: 'desc' }).append($('<div/>',{ class: 'thumb'
				 * }).append($('<img/>',{ src:
				 * '/jaju/storage/'+items.sale_image1, alt: '해당 주문 대표 상품 이미지'
				 * })) ).append($('<dl/>',{
				 * 
				 * }).append($('<dt/>',{ text:'가격' })).append($('<dd>',{
				 * text: items.sale_price.toLocaleString() })) ).append($('<dl/>',{
				 * 
				 * }).append($('<dt/>',{ text:'상태' })).append($('<dd>',{
				 * class: 'status end', text: items.sale_state })) ).append($('<dl/>',{
				 * 
				 * }).append($('<dt/>',{ text:'날짜' })).append($('<dd>',{
				 * text: items.sale_date }))) ) ).appendTo($('#tabTable'));
				 */
				
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
									text:items.sale_price.toLocaleString()
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
								})).append($('<button/>',{
									class:'state_'+items.sale_seq,
									id:"change_state_btn",
									text:' 상태변경',
									//style:'height: 30px;width: 90px; background-color: #617aad;color: #fff;border: none;border-radius: 3px; float:right;'
								}))))))).appendTo($('#tabTable'));
				
					$('.state_'+items.sale_seq).click(function(){
						var popupWidth=560;
						var popupHeight=800;

						var popupX = (window.screen.width/2)-(popupWidth/2);
						// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

						var popupY= (window.screen.height/2)-(popupHeight/2);
						// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
						
						window.open("/jaju/saleboard/saleStateModifyForm?sale_seq="+items.sale_seq, 
									"saleStateModifyForm", 
									'status=no, scroll=no,  height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY); 
					 
					});
				
			});// each
			
			// 페이징처리
			$('#myDealRecodePagingDiv').html(data.myRecodePaging3.pagingHTML);
			
			}//else

		},
		error: function(err){
			console.log('dealList에러발생'+err);
		}
	});
});

function myRecodePaging(pg){
	location.href="myDealRecode?pg="+pg;
}
