$(function(){
	
	
		
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/getSaleboardView',
		data: {'sale_seq': $('#sale_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('.goods_name .name').html(data.saleboardDTO.sale_subject);
			$('.dc .dc_price').html(data.saleboardDTO.sale_price+"원");
			if(data.saleboardDTO.sale_nego == 'on'){
				$('#sale_nego').text('네고가능');				
			}
			
			var member_id = $('#memId').val(); 
			
//			$.ajax({
//				type: 'post',
//				url: '/jaju/member/getMember',
//				data: {'member_id': data.saleboardDTO.member_id},
//				dataType: 'json',
//				success: function(data){
//					//alert(JSON.stringify(data));
//					
//				},error: function(err){
//					alert("회원 데이터 불러오기 에러");
//					console.log(err);
//				}
//			});
			
			//ajax 써서 거래 가능 지역 가져오기
			
			
			$.ajax({
				type: 'post',
				url: '/jaju/mypage/getMyScrap',
				data: {'member_id': member_id, 'sale_seq': $('#sale_seq').val()},
				dataType: 'text',
				success: function(data){
					//alert(data)
					if(data == 'hart.png'){
						$('#scrapBtn').text("찜♥");					
					} else{
						$('#scrapBtn').text("찜♡");
					}
				},error: function(err){
					alert("찜 상품 불러오기 에러");
					console.log(err);
				}
			});
			
			
			$('#member_sido .desc').text(data.saleboardDTO.member_sido+" "+data.saleboardDTO.member_sigungu);
			
			$('#sale_condition .desc').text(data.saleboardDTO.sale_condition);
			
			var howTrade = "";
			if(data.saleboardDTO.sale_direct == 'on'){
				howTrade = howTrade + '직거래  ';
			}
			if(data.saleboardDTO.sale_delivery == 'on'){
				howTrade = howTrade + '택배거래  ';
			}
			if(data.saleboardDTO.sale_onlineTran == 'on'){
				howTrade = howTrade + '온라인거래  ';
			}
			$('#howTrade .desc').text(howTrade);
			
			$('#sale_date .desc').text(data.saleboardDTO.sale_date);
			
			$('#sale_state .desc').text(data.saleboardDTO.sale_state);
			
			$('.thumb img').attr('src', '/jaju/storage/'+data.saleboardDTO.sale_image1);
			
			let map = new Map();
			
			map.set("image1", data.saleboardDTO.sale_image1);
			map.set("image2", data.saleboardDTO.sale_image2);
			map.set("image3", data.saleboardDTO.sale_image3+'');
			map.set("image4", data.saleboardDTO.sale_image4+'');
			map.set("image5", data.saleboardDTO.sale_image5+'');
			map.set("image6", data.saleboardDTO.sale_image6+'');
			map.set("image7", data.saleboardDTO.sale_image7+'');
			map.set("image8", data.saleboardDTO.sale_image8+'');
			map.set("image9", data.saleboardDTO.sale_image9+'');
			map.set("image10", data.saleboardDTO.sale_image10+'');
			
			
			for(var i=1; i<=10; i++){
				if(map.get('image'+i) != 'null'){
					$('#imageDiv').append($('<img/>',{
						width:"80",
						height:"80",
						class:"miniImg",
						src: '/jaju/storage/'+map.get('image'+i),
						id: map.get('image'+i)
					}));
				}
			}
			$(document).on('click', '#imageDiv img', function(){				
				$('.thumb img').attr('src', $(this).attr('src'));
			});
			
			$('#contentSpan').text(data.saleboardDTO.sale_content);
			
			
			$('#member_id .tit').html('<br>'+data.saleboardDTO.member_id);
			$('#member_id .tit').prepend($('<img/>',{
				src : '/jaju/storage/'+ data.saleboardDTO.member_image,
				width: '50px',
				height: '50px'
			}));
			$('#member_id .desc').html('&nbsp;'+data.saleboardDTO.member_manner+'°C');
			if(data.saleboardDTO.member_manner < 30){
				$('#member_id .desc').css('font-size', '20pt');
				$('#member_id .desc').css('color', '#00d0ff');
				$('#member_id .desc').prepend($('<img/>',{
					src : '/jaju/jajuImage/매너낮음.png',
					width: '200px',
					height: '50px'
				}));
			} else if(data.saleboardDTO.member_manner > 30 && data.saleboardDTO.member_manner < 40){
				$('#member_id .desc').css('font-size', '20pt');
				$('#member_id .desc').css('color', '#fedb35');
				$('#member_id .desc').prepend($('<img/>',{
					src : '/jaju/jajuImage/매너보통.png',
					width: '200px',
					height: '50px'
				}));
			} else{
				$('#member_id .desc').css('font-size', '20pt');
				$('#member_id .desc').css('color', '#ff0056');
				$('#member_id .desc').prepend($('<img/>',{
					src : '/jaju/jajuImage/매너높음.png',
					width: '200px',
					height: '50px'
				}));
			}
			
		},error: function(err){
			alert("뷰 생성 에러");
			console.log(err);
		}
	});
	
	
	//댓글 불러오기
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/getSaleboardComment',
		data: {'sale_seq': $('#sale_seq').val(), 'pg': $('#pg').val()},
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
					text : items.member_id
				})).append($('<td/>',{
					class: 'trLast'+items.comment_seq,
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
				
				if(items.member_id == $('#memId').val()){
					$('.trLast'+items.comment_seq).append($('<img>',{
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
				}
			}); //each
			
			
			$('#pagingDiv').html(data.saleboardCommentPaging.pagingHTML);
			
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

// 댓글 달기 
$(document).on('click', '#reCommentBtn', function(){
	var reCommentText = $('#reCommentText').val(); //댓글 내용
	
	var comment_pseq = $(this).parent().parent().prev().find('td:first').text(); //원글번호
	
	var loginId = $('#memId').val(); 
	
	if(reCommentText == ''){
		alert('댓글 내용을 입력해 주세요');
		$('#reCommentText').focus();
	} else{
		
		$.ajax({
			type: 'post',
			url: '/jaju/saleboard/writeReplySaleboardComment',
			data: {'sale_seq': $('#sale_seq').val(),'member_id': loginId, 'comment_content': reCommentText, 'comment_pseq':comment_pseq},
			success: function(data){
				
				location.href='/jaju/saleboard/saleboardView?sale_seq='+$('#sale_seq').val()+'&pg=1';
				
			},error: function(err){
				console.log(err);
				alert("답글 생성 오류");
			}
			
		});
		
	}
	
	
});


$('#scrapBtn').click(function(){
	var member_id = $('#memId').val(); //나중에 세션에서 아이디 주기
//	$('#sale_seq').val(); // 판매글의 seq값
//	$('#sale_state .desc').text(); // 판매글의 판매 상태
//	$('.goods_name .name').text(); //판매글의 제목
//	$('#imageDiv img:first').attr('id'); //이미지 파일 이름
//	alert("제목 =" + $('.goods_name .name').text());
//	alert("이미지 이름 =" + $('#imageDiv img:first').attr('id'));
	
	
	//if($('#scrapBtn img').attr('src') == '/jaju/jajuImage/blackhart.png' ){
	if($('#scrapBtn').text() == '찜♡'){	
		$.ajax({
			type: 'post',
			url: '/jaju/mypage/writeMyScrap',
			data: {'member_id': member_id, 'sale_seq': $('#sale_seq').val(),
				   'sale_state': $('#sale_state .desc').text(), 'sale_subject': $('.goods_name .name').text(),
				   'sale_image1': $('#imageDiv img:first').attr('id')
				   },
			success: function(date){
				//$('#scrapBtn img').attr('src', '/jaju/jajuImage/hart.png')
				$('#scrapBtn').text("찜♥");
				
			},error: function(err){
				alert("찜 하기 에러");
				console.log(err);
			}
			
		});
	} else{
		$.ajax({
			type: 'post',
			url: '/jaju/mypage/deleteMyScrapView',
			data: {'member_id': member_id, 'sale_seq': $('#sale_seq').val()},
			success: function(date){
				//$('#scrapBtn img').attr('src', '/jaju/jajuImage/blackhart.png')
				$('#scrapBtn').text("찜♡");
			},error: function(err){
				alert("찜 삭제 에러");
				console.log(err);
			}
			
		});
		
	}
		
});
//팔로우 클릭 (1.뷰 불러올 때 팔로우 상태 확인 후 팔로잉 으로 체크 )
$('.txt_type_follow').click(function(){
	
	var member_id = $('#memId').val(); //나중에 세션에서 아이디 주기
	
	if($('#member_id .tit').text()==member_id){
		alert("본인의 판매글입니다.");
		return false;
	}else{
			//팔로우라면, 팔로잉 으로 변경 ==write
			if($('.txt_type_follow').text()=='팔로우'){
				
				$.ajax({
					type:'post',
					url: '/jaju/mypage/writeMyFollow',
					data:{
						'member_id': member_id, 
						'follow_id':$('#member_id .tit').text(),
					},
					success: function(date){
						$('.txt_type_follow').text("팔로잉 중");
					},error: function(err){
						alert("팔로잉에 실패했습니다.");
						console.log(err);
					}
				});//if ajax
				
			}else {
				
				$.ajax({
					type:'post',
					url: '/jaju/mypage/deleteMyFollow',
					data:{
						'member_id': member_id, 
						'follow_id':$('#member_id .tit').text(),
					},
					success: function(date){
						//alert("민지야 deleteMyFollow 성공해따아");
						$('.txt_type_follow').text("팔로우");
					},error: function(err){
						alert("팔로잉에 실패했습니다.");
						console.log(err);
					}
				});//ajax
				
			}
	}//if     <-이거 추가
});

$('.txt_type').click(function(){
	
	//판매자 id 세션 id 판매 글번호
//	$('#sale_seq').val(); // 판매글의 seq값
//	$('#member_id .tit').text(); // 판매글의 id
	var message_writer = $('#memId').val();
	
	 var popupWidth=500;
	 var popupHeight=500;

	 var popupX = (window.screen.width/2)-(popupWidth/2);
	 // 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

	 var popupY= (window.screen.height/2)-(popupHeight/2);
	 // 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

	
	window.open("/jaju/mypage/myMessageForm?sale_seq=" +$('#sale_seq').val()+"&message_writer="+ message_writer +"&message_reader=" + $('#member_id .tit').text()+"&pg=1", 
			 "sale_my_Message", 
			 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
	
});


$('#commentInputBtn').click(function(){
	
	//$('#sale_seq').val();
	var member_id = $('#memId').val();
	var comment_content = $('#commentText').val()
	
	
	
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/writeSaleboardComment',
		data: {'sale_seq': $('#sale_seq').val(), 'member_id': member_id, 'comment_content': comment_content},
		success: function(data){
			
			location.href='/jaju/saleboard/saleboardView?sale_seq='+$('#sale_seq').val()+'&pg=1';
			
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
		url: '/jaju/saleboard/getSaleboardCommentOne',
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
				value: data.saleboardCommentDTO.comment_content
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
			url: '/jaju/saleboard/commentModify',
			data: {'comment_content': comment_content, 'comment_seq': comment_seq},
			success: function(data){
				
				location.href='/jaju/saleboard/saleboardView?sale_seq='+$('#sale_seq').val()+'&pg=1';
				
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
			url: '/jaju/saleboard/commentDelete',
			data: {'comment_seq': comment_seq},
			success: function(data){
				
				location.href='/jaju/saleboard/saleboardView?sale_seq='+$('#sale_seq').val()+'&pg=1';
				
			},error: function(err){
				alert("댓글 삭제 에러");
				console.log(err);
			}
		});
	}
	
});


$('#memberreportA').click(function(){
	
	location.href= '/jaju/serviceCenter/reportWriteForm?sale_seq='+ $('#sale_seq').val() +'&sale_id='+$('#member_id .tit').text();
	
});













