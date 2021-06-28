//veiw에 한사람 글 불러오기
$(function(){
	$.ajax({
		type: 'post',
		url: '/jaju/serviceCenter/getNotice',
		data: {'notice_seq' : $('#notice_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			
			$('#subjectSpan').text(data.noticeDTO.notice_subject);
			$('#dateSpan').text(data.noticeDTO.logtime);
			$('#contentSpan').text(data.noticeDTO.notice_content);
			
			if(data.noticeDTO.notice_image0 != null){
				$('#imageSpan0').attr('src','/jaju/storage/'+data.noticeDTO.notice_image0);
				$('#imageSpan0').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image1 != null){
				$('#imageSpan1').attr('src','/jaju/storage/'+data.noticeDTO.notice_image1);
				$('#imageSpan1').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image2 != null){
				$('#imageSpan2').attr('src','/jaju/storage/'+data.noticeDTO.notice_image2);
				$('#imageSpan2').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image3 != null){
				$('#imageSpan3').attr('src','/jaju/storage/'+data.noticeDTO.notice_image3);
				$('#imageSpan3').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image3 != null){
				$('#imageSpan4').attr('src','/jaju/storage/'+data.noticeDTO.notice_image4);
				$('#imageSpan4').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image3 != null){
				$('#imageSpan5').attr('src','/jaju/storage/'+data.noticeDTO.notice_image5);
				$('#imageSpan5').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image3 != null){
				$('#imageSpan6').attr('src','/jaju/storage/'+data.noticeDTO.notice_image6);
				$('#imageSpan6').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image3 != null){
				$('#imageSpan7').attr('src','/jaju/storage/'+data.noticeDTO.notice_image7);
				$('#imageSpan7').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image3 != null){
				$('#imageSpan8').attr('src','/jaju/storage/'+data.noticeDTO.notice_image8);
				$('#imageSpan8').css({'width': '150px', 'height': '150px'});
			}
			if(data.noticeDTO.notice_image3 != null){
				$('#imageSpan9').attr('src','/jaju/storage/'+data.noticeDTO.notice_image9);
				$('#imageSpan9').css({'width': '150px', 'height': '150px'});
			}
			
		},
		error: function(err){
			console.log(err);
		}
	});
});


//이전글, 다음글
$(function(){
	$.ajax({
		type: 'post',
		url: '/jaju/serviceCenter/getPage',
		data: {'notice_seq' : $('#notice_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('#prevSpan').text(data.noticeDTO.prev_subject);
			if(data.noticeDTO.prev_subject != '이전글이 없습니다'){
				$('#prevBtn').attr('href', '/jaju/serviceCenter/noticeView?notice_seq='+data.noticeDTO.prev_num+'&pg=1');
			}
			
			$('#nextSpan').text(data.noticeDTO.next_subject);
			if(data.noticeDTO.next_subject != '다음글이 없습니다'){
				$('#nextBtn').attr('href', '/jaju/serviceCenter/noticeView?notice_seq='+data.noticeDTO.next_num+'&pg=1');
			}
			
		},
		error: function(err){
			console.log(err);
		}
	});
});
