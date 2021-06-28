$(function(){
	
	
	$.ajax({
		
		type:'post',
		url: '/jaju/freeboard/getFreeboardView', //한 사람거 가져와서 getBoard 가져옴
		data: {'board_seq': $('#board_seq').val()},
		dataType:'json',
		success:function(data){
			//console.log(data);
			//alert('abc')
			
			$('#subjectSpan').text(data.freeboardDTO.board_subject);
			$('#idSpan').text(data.freeboardDTO.board_id);
			$('#dateSpan').text(data.freeboardDTO.logtime);
			$('#hitSpan').text(data.freeboardDTO.board_hit);
			$('#contentSpan').text(data.freeboardDTO.board_content);
			
			//이미지 맵
			let map = new Map();
			
			map.set("image1", data.freeboardDTO.image1+'');
			map.set("image2", data.freeboardDTO.image2+'');
			map.set("image3", data.freeboardDTO.image3+''); //null값 방지
			
			for(var i=1; i<=3; i++){
				if(map.get('image'+i) != 'null'){
					$('#imageDiv').append($('<img/>',{
						width:"300",
						height:"300",
						class:"boardImg",
						src: '/jaju/storage/'+map.get('image'+i)
					}));
				}
			}
			
			//로그인 한다면
			//if(data.memId == data.boardDTO.id)
			//	$('#boardViewSpan').show();
			//else
			//	$('#boardViewSpan').hide();
		},
		error: function(err){
			console.log(err);
		}
	});
	
	//댓글 불러오기
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getFreeboardComment',
		data: {'board_seq': $('#board_seq').val(), 'pg': $('#pg').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('#commentTable tr:gt(0)').remove();
			
			$.each(data.list, function(index,items){
				
				$('<tr/>').append($('<td/>',{
					text : items.comment_seq
				})).append($('<td/>',{
					style : 'text-align: left;'
					
					}).append($('<a/>',{
						text : items.comment_content,
						id: 'subjectA',
						class: 'content_'+ items.comment_seq
					}))
				).append($('<td/>',{
					text : items.board_id
				})).append($('<td/>',{
					class: 'trLast',
					text : items.logtime
				})).appendTo($('#commentTable'));
				
				for(var i=1; i<items.comment_lev; i++){
					$('.content_'+items.comment_seq).before('&emsp;');
				}
				if(items.comment_pseq != 0 ){
					$('.content_'+items.comment_seq).before($('<img>',{
						src: '/jaju/jajuImage/reply.gif'
					}));
				}
				
			}); //each
			
			//나중에 세션에서 가져오기 로그인한 id
			//if(items.member_id == sessionId){
			//}  삭제와 수정 붙이기
			$('.trLast').append($('<img>',{
				src: '/jaju/jajuImage/delete.png',
				id: 'commentDeleteBtn',
				style: 'cursor: pointer; float: right;',
				width: '20px',
				height: '20px'
			})).append($('<img>',{
				src: '/jaju/jajuImage/modify.png',
				id: 'commentModifyBtn',
				style: 'cursor: pointer; float: right;',
				width: '20px',
				height: '20px'
			}));
			
			$('#pagingDiv').html(data.freeboardCommentPaging.pagingHTML);
			
			$(document).on('click', '#subjectA', function(){
					
					$('#reCommentTr').remove();
					$('#reCommentModifyTr').remove();
				
					$(this).parent().parent().after($('<tr/>',{
						id: 'reCommentTr'
					}).append($('<td/>',{
						style: 'background-color: #f5f5f5;'
					})).append($('<td/>',{
						colspan: 3,
						text: 're: ',
						style: 'background-color: #f5f5f5; text-align: left;'
					}).append($('<input/>',{
						type: 'text',
						id: 'reCommentText',
						width: '55%'
					})).append($('<input/>',{
						type: 'button',
						id: 'reCommentBtn',
						value: '   답글달기'
					}))
					));
					
			});
			
			
			
		},error: function(err){
			alert("댓글 불러오기 에러");
			console.log(err);
		}
	});
	
});


//댓글 달기 
$(document).on('click', '#reCommentBtn', function(){
	var reCommentText = $('#reCommentText').val(); //댓글 내용
	
	var comment_pseq = $(this).parent().parent().prev().find('td:first').text(); //원글번호
	
	//var loginId = 'sessionId'; //나중에 세션에서 받아오기
	
	if(reCommentText == ''){
		alert('댓글 내용을 입력해 주세요');
		$('#reCommentText').focus();
	} else{
		
		$.ajax({
			type: 'post',
			url: '/jaju/freeboard/writeReplyFreeboardComment',
			data: {'board_seq': $('#board_seq').val(),'board_id': loginId, 'comment_content': reCommentText, 'comment_pseq':comment_pseq},
			success: function(data){
				
				location.href='/jaju/freeboard/freeboardView?board_seq='+$('#board_seq').val()+'&pg=1';
				
			},error: function(err){
				console.log(err);
				alert("답글 생성 오류");
			}
			
		});
		
	}
	
	
});

$('#commentInputBtn').click(function(){
	
	//$('#sale_seq').val();
	//var member_id = 'sessionId'; // 나중에 세션에서 가져오기
	var comment_content = $('#commentText').val()
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/writeFreeboardComment',
		data: {'board_seq': $('#board_seq').val(), 'board_id': $('#board_id').val(), 'comment_content': comment_content},
		success: function(data){
			
			location.href='/jaju/freeboard/freeboardView?board_seq='+$('#board_seq').val()+'&pg=1';
			
		},error: function(err){
			alert("댓글 달기 에러");
			console.log(err);
		}
	});
	
});

//댓글 수정
$(document).on('click', '#commentModifyBtn', function(){
	$('#reCommentModifyTr').remove();
	$('#reCommentTr').remove();
	var comment_seq = $(this).parent().prev().prev().prev().text();
	//alert(comment_seq);
	
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getFreeboardCommentOne',
		context: this,
		data: {'comment_seq': comment_seq},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$(this).parent().parent().after($('<tr/>',{
				id: 'reCommentModifyTr'
			}).append($('<td/>',{
				style: 'background-color: #f5f5f5;'
			})).append($('<td/>',{
				colspan: 3,
				text: 'edit: ',
				style: 'background-color: #f5f5f5; text-align: left;'
			}).append($('<input/>',{
				type: 'text',
				id: 'reCommentModifyText',
				width: '55%',
				value: data.freeboardCommentDTO.comment_content
			})).append($('<input/>',{
				type: 'button',
				id: 'reCommentModifyWriteBtn',
				value: '   수정하기'
			}))
			));
			
		},error: function(err){
			alert("댓글 수정 불러오기 에러");
			console.log(err);
		}
	});
	
});
//댓글 수정
$(document).on('click', '#reCommentModifyWriteBtn', function(){
	var comment_content = $('#reCommentModifyText').val();
	var comment_seq = $(this).parent().parent().prev().find('td:first').text();
	//alert(comment_seq);
	
	if(comment_content == ''){
		alert('수정할 내용을 입력해 주세요');
		$('#reCommentModifyText').focus();
	} else{
		
		$.ajax({
			type: 'post',
			url: '/jaju/freeboard/commentModify',
			data: {'comment_content': comment_content, 'comment_seq': comment_seq},
			success: function(data){
				
				location.href='/jaju/freeboard/freeboardView?board_seq='+$('#board_seq').val()+'&pg=1';
				
			},error: function(err){
				alert("댓글 수정 에러");
				console.log(err);
			}
		});
	
	}
});

//댓글 삭제 
$(document).on('click', '#commentDeleteBtn', function(){
	var comment_seq = $(this).parent().parent().find('td:first').text();
	//alert(comment_seq);
	
	if(confirm('정말 삭제하시겠습니까?')){
		$.ajax({
			type: 'post',
			url: '/jaju/freeboard/commentDelete',
			data: {'comment_seq': comment_seq},
			success: function(data){
				
				location.href='/jaju/freeboard/freeboardView?board_seq='+$('#board_seq').val()+'&pg=1';
				
			},error: function(err){
				alert("댓글 삭제 에러");
				console.log(err);
			}
		});
	}
	
	
});

//이전글, 다음글
$(function(){
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getPage',
		data: {'board_seq' : $('#board_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('#prevSpan').text(data.freeboardDTO.prev_subject);
			if(data.freeboardDTO.prev_subject != '이전글이 없습니다'){
				$('#prevBtn').attr('href', '/jaju/freeboard/freeboardView?board_seq='+data.freeboardDTO.prev_num+'&pg=1');
			}
			
			$('#nextSpan').text(data.freeboardDTO.next_subject);
			if(data.freeboardDTO.next_subject != '다음글이 없습니다'){
				$('#nextBtn').attr('href', '/jaju/freeboard/freeboardView?board_seq='+data.freeboardDTO.next_num+'&pg=1');
			}
			
			
			
		},
		error: function(err){
			console.log(err);
		}
	});
});
