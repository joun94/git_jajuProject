$('#saleboardDeleteBtn').click(function(){
	
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/getSaleboardView',
		data: {'sale_seq': $('#sale_seq').val()},
		dataType: 'json',
		success: function(data){
				if(confirm('정말로 삭제하시겠습니까?')){ 
					$.ajax({
	                type: 'post',
	                url: '/jaju/saleboard/saleboardDelete',
	                data: {'sale_seq': $('#sale_seq').val()},
	                success: function(){
	                   alert('게시글 삭제 완료');
	                   //location.href='/jaju/saleboard/saleboardList';
	                   location.href='/jaju/saleboard/index';
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

