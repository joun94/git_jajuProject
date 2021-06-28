//목록 
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/jaju/serviceCenter/getReportList',
		data: {'pg' : $('#pg').val(),
			   'report_id' : $('#report_id').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			//alert(data.list.length);
			
		if(data.report_id == ""){
			alert('먼저 로그인 하세요');
			location.href='/jaju/member/loginForm';
		}else{
	
			if(data.list.length != 0){
				$('.no_data').hide();
				
		        $.each(data.list, function(index, items){
		            //console.log(data);
		            
		        	$('<tr/>').append($('<td/>',{
		        		width: '8%',
		                align: 'center',
		                text: items.report_seq
		            })).append($('<td/>',{
		            	width: '20%',
		            	align: 'center',
		            	text: items.reportType
		            })).append($('<td/>',{
		            	
		            	}).append($('<a/>',{
		            		href: '#',
		            		text: items.report_subject,
		            		class: 'subject'+items.report_seq
		            	}))
		            ).append($('<td/>',{
		            	width: '12%',
		                align: 'center',
		                text: items.report_id
		            })).append($('<td/>',{
		            	width: '12%',
		            	align: 'center',
		            	text: items.logtime
		            })).append($('<td/>',{
		            	width: '12%',
		            	align: 'center',
		            	text: items.report_state,
		            	class: 'state',
		            })).appendTo('.reportListTable');
		        	
		        	$('.subject'+items.report_seq).click(function(){
						location.href = '/jaju/serviceCenter/reportView?report_seq='+items.report_seq+'&pg='+$('#pg').val();
					});
		        	
		        	//처리중 처리완료 색 변경
		        	$("td.state:contains('처리중')").css({color:"red"});
		        	$("td.state:contains('처리완료')").css({color:"blue"});
		        }); //each
		        
		        
			}else if(data.list.length == 0){
				$('.no_data').show();
			}
	        
	        //페이징 처리
	        $('.pagediv').html(data.reportPaging.pagingHTML);
	        
			}
		},
      	error: function(err){
      		console.log(err);
      	}
      
	});
});

//페이징 이동
function reportPaging(pg){
	location.href = "/jaju/serviceCenter/reportList?pg="+pg;
}