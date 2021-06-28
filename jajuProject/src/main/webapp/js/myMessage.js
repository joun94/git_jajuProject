$(document).ready(function() {

//쪽지 list 불러오기
	$.ajax({
		url:'/jaju/mypage/getMyMessageList',
		type:'post',
		data:{
			'id':$('#id').val(),
			'pg':$('#pg').val()
			},
		dataType:'json',
		success:function(data){
			
			console.log(JSON.stringify(data));
		
			
			//리스트가 null이라면, 
			if(JSON.stringify(data.list)=='[]' && data.getMyMessageListPage != null){
				
				$('.board-list').remove();

				$('<tr/>').append($('<td/>',{
					align:'center',
					text:'받은 쪽지가 없습니다.',
					style:'height:300px;'
				})).appendTo($('#messageListTable'));
			}
			
			else {
				
			$.each(data.list, function(index, items){
			
				//alert("items.message_seq = "+items.message_seq);

				var text;
			
				if(items.message_change=='0'){
					text ="읽지않음";
				}else if(items.message_change=='1'){
					text ="읽음";
				}else if(items.message_change=='3'){
					text ="삭제된 메세지";
				}
						//받은쪽지없습니다 그럼 테이블치워버려!!!!
					
					//그리고 붙여!!!! 체크박스, 보낸사람, 내용, 작성일, 답변상태, 
					$('<tr/>',{
						id:'tr_row',
						align:'center'

						}).append($('<td/>',{
						align:'center'
						
						}).append($('<input>',{
							type:'checkbox',
							name:'check',
							value:items.message_seq
							
					}))).append($('<td/>',{
						align:'center',
						text:items.message_writer
					})).append($('<td/>',{
						align:'center',
						text:items.message_subject,
						id : 'subjectA',
						class : 'subject_'+items.message_seq,
						style:"width: 200px;"
						
					})).append($('<td/>',{
						align:'center',
						text:items.message_date
					})).append($('<td/>',{
						align:'center',
						text:text
					})).appendTo($('#messageListTable'));
					
					$('.subject_'+items.message_seq).click(function(){
						var seq = items.message_seq; 
						//alert(seq);
						location.href="/jaju/mypage/myMessageView?seq="+seq+"&pg="+$('#pg').val();
					});
					
					//전체 선택 또는 해제
					$('#all').click(function(){
						//alter($('#all').attr('checked'));//checked 속성이 없어서 undefind으로 나온다.
						//alert($('#all').prop('checked'));//attr이 아니라 값으로 가져와야한다. 

						if($('#all').prop('checked')){
							$('input[name=check]').prop('checked',true);
						}else {
							$('input[name=check]').prop('checked',false);
						}
					});
					
			});//each
			
			$('#myMessagePagingDiv').html(data.getMyMessageListPage.pagingHTML);
			
			}//else
			
		},
		error:function(err){
			console.log("err발생"+err)
		}
	});
});

function myRecodePaging(pg){
	location.href="myMessage?pg="+pg;
}

//선택삭제
$('#deleteBtn').click(function(){
	/*var count = $('input[name=check]:checked').length;
	alert($('input[name=check]:checked').val());
	if(count==0){
		alert("삭제할 항목을 선택 하세요");
	}else {
		if(confirm("정말로 삭제 하시겠습니까?")){
			$('#messageForm').submit();
			alert('삭제 완료 되었습니다.');
		}else{
			alert('삭제에 실패했습니다. 다시 선택해주세요.');
		}
	}*/
	
	//db에서 삭제하면 안되고, 그냥 숨기기만 하기 !
	var count = $('input[name=check]:checked').length;
	//alert($('input[name=check]:checked').val());
	
	//alert($('input[name=check]:checked').parent().prop('tagName'));
	
	if(count==0){
		alert("삭제할 항목을 선택 하세요");
	}else {
		if(confirm("정말로 삭제 하시겠습니까?")){
			//삭제가 아니라 상태를 3으로 바꿔서 아예 가져오지 못하도록 하기.
			//$('input[name=check]:checked').parent().parent().hide();
			$('#messageForm').submit();
			alert('삭제 완료 되었습니다.');
			return false;
			
		}else{
			alert('삭제에 실패했습니다. 다시 선택해주세요.');
		}
	}
});
