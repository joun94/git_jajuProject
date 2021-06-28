$(function(){
   $('#message_reader_span').text($('#message_reader').val());
   $('#message_writer_Span').text($('#message_writer').val());
   
});

$('#sendBtn').click(function(){
   
   if($('#message_subject').val() == ""){
      alert('제목을 작성해 주세요');
   } else if($('#message_content_Span').val() == ""){
      alert('내용을 작성해 주세요');
   } else{
      
      $.ajax({
         type: 'post',
         url: '/jaju/mypage/writeMyMessage',
         data: $('#myMessageFormForm').serialize(),
         success: function(data){
            
            alert('메세지가 보내졌습니다');
            location.href='/jaju/mypage/myMessage?pg=1';
            
         },error: function(err){
            console.log(err);
            alert('메세지 보내기 에러');
         }
      });
      
   }
});

//myMessageSend