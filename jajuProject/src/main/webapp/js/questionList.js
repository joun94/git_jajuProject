//목록 
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/jaju/serviceCenter/getQuestionList',
		data: 'pg='+$('#pg').val(),
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data))
	        $.each(data.list, function(index, items){
	            //console.log(data);
	            $('<div/>').append($('<table/>',{
	            	style : 'width : 100%;',
	            	class : 'table_faq',
	            	id : 'faq_'+items.question_seq
            	}).append($('<tr/>',{
            	
	            	}).append($('<td/>',{
	            		style : 'width: 70px; text-align: center;',
	            		text : items.question_seq
	            	})).append($('<td/>',{
	            		style : 'width: 150px; text-align: center;',
	            		text : items.questionType
	            	})).append($('<td/>',{
	            		class : 'subject'
	            		}).append($('<a/>',{
			            	href : 'javascript:void(0)',
			            	text : items.question_subject
			    }))))
			    
	            ).append($('<div/>',{
	            	style : 'display: none; border-top: 1px; solid rgb(230,230,230);'	
	            	}).append($('<table/>',{
	            		style : 'cellpadding: 0; cellspacing: 0; border: 0;'
	            		}).append($('<tr/>',{
	            			style : 'valign : top;'
	            			}).append($('<th/>',{
	            				width: '70px;',
	            				class: 'thQuestion',
	            				}).append($('<img/>',{
	            					style:'margin-top: 12px;',
	            					src : '/jaju/jajuImage/a.png',
	            					width: '22px',
	            					height:'22px',
	            				}))
	            			).append($('<td/>',{
	            				}).append($('<pre/>',{
	            					style : 'margin-bottom:15px;',
	            					class: 'viewPre',
	            					text : items.question_content
	            			})))))
	            ).appendTo('#sub_table')
	        
	        }); //each
	      
	        $('.table_faq .subject a').click(function(){
            	$(this).toggleClass("selected");
            	$(".table_faq .subject a").not(this).removeClass('selected');
            	
            	var target = $(this).parents(".table_faq").next();
            	
            	var other = $('.table_faq .subject a').not(this).parents(".table_faq").next();
            	
            	target.slideToggle(300);
        		other.slideUp(300);
        		
        		target.show();
        		other.hide();
            });	
	        //페이징 처리
	        $('.pagediv').html(data.questionPaging.pagingHTML);
         
		},
      	error: function(err){
      		console.log(err);
      	}
      
	});
});

//검색
$('#searchBtn').click(function(){
	
	 if($('#keyword').val() == ''){
		   alert("검색어를 입력하세요")
	 }else{
		   $('.table_faq tr:gt(0)').remove();
		   
		   $.ajax({
			   type: 'post',  
			   url: '/jaju/serviceCenter/getQuestionSearchList',
			   data: {'pg':$('input[name=pg]').val(),
					  'itemcd':$('#itemcd').val(),
				   	  'keyword':$('#keyword').val()},
			   dataType: 'json',
			   success: function(data){
				   
				   $.each(data.list, function(index, items){
			            //console.log(data);
			            
					   $('<div/>').append($('<table/>',{
			            	style : 'width : 100%;',
			            	class : 'table_faq',
			            	id : 'faq_'+items.question_seq
		            	}).append($('<tr/>',{
		            	
			            	}).append($('<td/>',{
			            		style : 'width: 70px; text-align: center;',
			            		text : items.question_seq
			            	})).append($('<td/>',{
			            		style : 'width: 150px; text-align: center;',
			            		text : items.questionType
			            	})).append($('<td/>',{
			            		class : 'subject'
			            		}).append($('<a/>',{
					            	href : 'javascript:void(0)',
					            	text : items.question_subject
					    }))))
					    
			            ).append($('<div/>',{
			            	style : 'display: none; border-top: 1px; solid rgb(230,230,230);'	
			            	}).append($('<table/>',{
			            		style : 'cellpadding: 0; cellspacing: 0; border: 0;'
			            		}).append($('<tr/>',{
			            			style : 'valign : top;'
			            			}).append($('<th/>',{
			            				width: '70px;',
			            				class: 'thQuestion',
			            				}).append($('<img/>',{
			            					style:'margin-top: 12px;',
			            					src : '/jaju/jajuImage/a.png',
			            					width: '22px',
			            					height:'22px',
			            				}))
			            			).append($('<td/>',{
			            				}).append($('<pre/>',{
			            					style : 'margin-bottom:15px;',
			            					class: 'viewPre',
			            					text : items.question_content
			            			})))))
			            ).appendTo('#sub_table')

						   $('.subject'+items.notice_seq).click(function(){
							   location.href = '/jaju/serviceCenter/noticeView?notice_seq='+items.notice_seq+'&pg='+$('#pg').val();
			
						   }); 
		   
					   }); //each
				   
				   $('.table_faq .subject a').click(function(){
		            	$(this).toggleClass("selected");
		            	$(".table_faq .subject a").not(this).removeClass('selected');
		            	
		            	var target = $(this).parents(".table_faq").next();
		            	
		            	var other = $('.table_faq .subject a').not(this).parents(".table_faq").next();
		            	
		            	target.slideToggle(300);
		        		other.slideUp(300);
		        		
		        		target.show();
		        		other.hide();
		            });	
			      
			        //페이징 처리
				   $('.pagediv').html(data.questionPaging.pagingHTML);
		
			   },
			   error: function(err){
		              console.log(err);
		       }
		   });
	 }
});


//페이징 이동
function questionPaging(pg){
	var keyword = document.getElementById('keyword').value;
	
	if(keyword==''){//keyword가 없을 때
	
		location.href = "/jaju/serviceCenter/questionList?pg="+pg;
	}else{
		
		$('input[name=pg]').val(pg);
		
		$('#searchBtn').get(0).click();
		
		$('input[name=pg]').val(1);
		
	}
}