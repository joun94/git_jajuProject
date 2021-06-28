//리스트
$(function(){
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getFreeboardList',
		data: 'pg='+$('#pg').val(),
		dataType: 'json',
		success: function(data){
			//console.log(JSON.stringify(data));
		
			  $.each(data.list, function(index, items){
		            $('<tr/>').append($('<td/>',{
		               align:'center',
		               text: items.board_seq
		               
		            })).append($('<td/>',{
			               
		               }).append($('<a/>',{
		            	   style: "cursor:pointer;",
			               text: items.board_subject,
			               id: 'item',
		                  
		               })) 
		            ).append($('<td/>',{
		               align:'center',
		               text: items.board_id
		               
		            })).append($('<td/>',{
		               align:'center',
		               text: items.logtime
		               
		            })).append($('<td/>',{
		               align:'center',
		               text: items.board_hit
		               
		            })).appendTo($('#freeboardListTable'));
	
			}); //each
			  
			  $(document).on('click', '#item', function(){ //a태그에 아이디 준거 subjectA 그거의 부모 찾고 그 부모의 앞에거를 찾고

		               var seq = $(this).parent().prev().text();
		               location.href = '/jaju/freeboard/freeboardView?board_seq='+seq+'&pg=1';
		               //location.href = '/jaju/freeboard/freeboardView?board_seq='+seq+'&pg='+$('#pg').val();

		         });

			  //페이징 처리
			$('#freeboardpagingDiv').html(data.freeboardPaging.pagingHTML);		   
		}, 
			error:function(err){
			console.log(err);
			alert('error');
		}
	});
});

function freeboardPaging(pg){
	location.href="freeboardList?pg="+pg;
}

//검색
$('#boardSearchBtn').click(function(){
	//alert($('input[name=pg]').val());
	//alert($('#pg').val());
	//alert('이미지클릭')
	if($('#searchText').val() == ""){
		alert("검색어를 입력해 주세요");
		$('#searchText').focus();
	}  else{
	
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getFreeboardSearchList',
		data: $('#boardSearchList').serialize(),
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('#freeboardListTable tr:gt(0)').remove();
			
			  $.each(data.list, function(index, items){
		            $('<tr/>').append($('<td/>',{
		               align:'center',
		               text: items.board_seq
		               
		            })).append($('<td/>',{
			               
		               }).append($('<a/>',{
		            	   href: '#',
			               text: items.board_subject,
			               id: 'item',
		                  
		               })) 
		            ).append($('<td/>',{
		               align:'center',
		               text: items.board_id
		               
		            })).append($('<td/>',{
		               align:'center',
		               text: items.logtime
		               
		            })).append($('<td/>',{
		               align:'center',
		               text: items.board_hit
		               
		            })).appendTo($('#freeboardListTable'));
	
			}); //each
			  
			  $(document).on('click', '#item', function(){

		               var seq = $(this).parent().prev().text();
		               location.href = '/jaju/freeboard/freeboardView?board_seq='+seq;

		   });
			
			$('#freeboardpagingDiv').html(data.freeboardPaging.pagingHTML);		
			
		}, error: function(err){
			console.log(err);
			alert('검색 리스트 생성 오류');
			
		}
	});

	return false;
	}
});

