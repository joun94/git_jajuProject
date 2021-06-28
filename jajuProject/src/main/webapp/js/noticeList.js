//목록
//alert($('#pg').val());
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/jaju/serviceCenter/getNoticeList',
		data: 'pg='+$('#pg').val(),
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>',{
					width: '80',
	                align: 'center',
	                text: items.notice_seq,
				})).append($('<td/>',{
					color: '#333'	
	            	}).append($('<a/>',{
	            		href: '#',
	            		text: items.notice_subject,
	            		class: 'subject'+items.notice_seq
	            	}))
            	).append($('<td/>',{
            		width: '150',
	                align: 'center',
	                class: 'eng',
	                text: items.logtime,
            	})).appendTo('#noticeListTable');
				
				$('.subject'+items.notice_seq).click(function(){
				location.href = '/jaju/serviceCenter/noticeView?notice_seq='+items.notice_seq+'&pg='+$('#pg').val();
				
				}); 
			
			});//each
			
			//페이징 처리
	        $('.pagediv').html(data.noticePaging.pagingHTML);
		},
		error: function(err){
      		console.log(err);
		}
	});
});

//검색
$('#searchBtn').click(function(){
	//alert("--------");
	 if($('#keyword').val() == ''){
		   alert("검색어를 입력하세요")
	   }else{
		   $('#noticeListTable tr:gt(0)').remove();
		   
		   $.ajax({
			   type: 'post',  
			   url: '/jaju/serviceCenter/getNoticeSearchList',
			   data: {'pg':$('input[name=pg]').val(),
				   	  'itemcd':$('#itemcd').val(),
				   	  'keyword':$('#keyword').val()},
			   dataType: 'json',
			   success: function(data){
				   //alert(JSON.stringify(data));
	   
				   $.each(data.list, function(index, items){
					   $('<tr/>').append($('<td/>',{
						   width: '80',
						   align: 'center',
						   text: items.notice_seq,
					   })).append($('<td/>',{
						   color: '#333'	
					   		}).append($('<a/>',{
					   			href: '#',
					   			text: items.notice_subject,
					   			class: 'subject'+items.notice_seq
					   		}))
					   ).append($('<td/>',{
						   width: '150',
						   align: 'center',
						   class: 'eng',
						   text: items.logtime,
					   })).appendTo('#noticeListTable');

					   $('.subject'+items.notice_seq).click(function(){
						   location.href = '/jaju/serviceCenter/noticeView?notice_seq='+items.notice_seq+'&pg='+$('#pg').val();
		
					   }); 
	   
				   });//each
   
				   //페이징 처리
				   $('.pagediv').html(data.noticePaging.pagingHTML);
			   },
			   error: function(err){
		              console.log(err);
		       }
		   });
	   }
});

//페이징 이동
function noticePaging(pg){
	var keyword = document.getElementById('keyword').value;
	//alert(keyword);
	if(keyword==''){//keyword가 없을 때
		//alert($('.pg').val());
		location.href = "/jaju/serviceCenter/noticeList?pg="+pg;	
	}else{
		//alert(pg);
		
		$('input[name=pg]').val(pg);
		
		$('#searchBtn').get(0).click();
		
		$('input[name=pg]').val(1);
		//alert($('input[name=pg]').val());
	}
	
}