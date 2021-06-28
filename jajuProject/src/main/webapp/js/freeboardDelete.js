$('#freeboardDeleteBtn').click(function(){
	
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getFreeboardView',
		data: {'board_seq': $('#board_seq').val()},
		dataType: 'json',
		success: function(data){
				if(confirm('정말로 삭제하시겠습니까?')){ 
					$.ajax({
	                type: 'post',
	                url: '/jaju/freeboard/freeboardDelete',
	                data: {'board_seq': $('#board_seq').val()},
	                success: function(){
	                   alert('게시글 삭제 완료');
	                   location.href='/jaju/freeboard/index';
	                },
	                error:function(err){
	        			console.log(err);
	        		}
	        	});
			} //정말로 삭제하시겠습니까?
		}, error:function(err){
			console.log(err);
		}
	             
	});
	
});
