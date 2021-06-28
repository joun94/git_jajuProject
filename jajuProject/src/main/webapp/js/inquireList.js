//1:1 문의리스트 
$(function(){
	$.ajax({
		type: 'post',
		url : '/jaju/serviceCenter/getInquireList',
		data : 'pg='+ $('#pg').val(),
		dataType : 'json',
		success : function(data){
			//console.log(JSON.stringify(data));
			//alert(JSON.stringify(data));
			//alert(data.list.length);
			 
			if(data.list.length != 0){
				$('.no_data').hide();
				
				$.each(data.list, function(index, items){
					$('<tr/>').append($('<td/>',{
						align : 'center',
						width :'5%',
						text : items.inquiry_seq
					})).append($('<td/>',{
						align : 'center',
						width : '15%',
						text :items.inquiryType
					})).append($('<td/>',{
						}).append($('<a/>',{
							text : items.inquiry_subject,
							style : 'cursor : pointer',
							class : 'a'+ items.inquiry_seq
						}))
					).append($('<td/>',{
						align : 'center',
						width : '12%',
						text : items.inquiry_id
					})).append($('<td/>',{
						align : 'center',
						width : '12%',
						text : items.logtime
					})).append($('<td/>',{
						align : 'center',
						width : '12%',
						text : items.inquiry_state,
						class: 'state',
					})).appendTo($('.inquireTable'));
					
					for(var i =1; i<items.inquiry_lev; i++){
						$('.a'+items.inquiry_seq).before('&emsp;')//답글을 안쪽으로 한칸씩 밀어줌
					}
					if(items.inquiry_pseq != 0){//답글에 이미지 붙이기
						$('.a'+items.inquiry_seq).before($('<img/>',{
							src :'/jaju/jajuImage/arrow-icon.jpg',
							width : '12px',
							height : '12px',
						}));
					}//if
					
					$('.a'+items.inquiry_seq).click(function(){
						location.href = '/jaju/serviceCenter/inquireView?seq='+items.inquiry_seq+'&pg=' + $('#pg').val();
					});
					
		        	//처리중 처리완료 색 변경
		        	$("td.state:contains('처리중')").css({color:"red"});
		        	$("td.state:contains('처리완료')").css({color:"blue"});
				});//each
			}else if(data.list.length == 0){
				$('.no_data').show();
			}
			 //페이징 처리  도망가자
	        $('.pagediv').html(data.inquirePaging.pagingHTML);
		},
		error : function(err){
			console.log(err);
		}
	});
});

//페이징 이동
function inquirePaging(pg){
	location.href = "/jaju/serviceCenter/inquireList?pg="+pg;
}
