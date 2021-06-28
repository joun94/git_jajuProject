$(document).ready(function(){
		// 만약 받아온 값 null이면 팔로우한 계정이 없습니다.
		$.ajax({
			  url : '/jaju/mypage/getFollowList',
			  type : 'post',
			  // data : 'id='+$('#id').val(),
			  data : 
			  {
			     'id': $('#id').val(),
			     'pg': $('#pg').val()
			     // 'pg':'1'
			  },
			  dataType : 'json',
			  success:function(data){
 
         console.log("보고있나2"+JSON.stringify(data));// JSON형식 그대로 보여줭
         // 이미지, 아이디 붙이기.
 
         if(JSON.stringify(data.list)=='[]' && data.mypagePaging != null){
            $('.title').remove();
            $('#deleteBtn').remove();
            
            $('<tr/>').append($('<td/>',{
               align:'center',
               text:'팔로우 리스트가 없습니다.',
               style:'height:250px;'
            })).appendTo($('#followListTable'));
         }
         else {
         	  $.each(data.list, function(index, items){
        	    $('<tr/>').append($('<td/>',{
                    align:'center',
                    }).prepend($('<input>',{
                       type:'checkbox',
                       name:'check',
                       value:items.member_warning,
                    }))
                    ).append($('<td/>',{
                       align:'center',
                       style:'width:60px; height:60px; ',

                       }).append($('<img/>',{
                       // src:'/jaju/storage/'+'이미지이름',//items.image1,
                       src:'/jaju/storage/'+items.member_image,
                       style:'width:50px; height:50px;',
                       class:"img_"+'3'// items.follow_seq
                    }))   
                 ).append($('<td/>',{
                    align:'center',
                    style:'cursor:pointer;',
                    id:'follwSaleListA',
                    text:items.member_name
                 })).append($('<td/>',{
                    align:'center',
                    // style:'cursor:pointer;',
                    id:'member_manner',
                    text:items.member_manner
                 })).appendTo($('#followListTable'));
              
              // 프로필을 누르면 해당 사람의 정보 띄워주기(이미지 or 아이디)
              $('.img_'+items.seq).click(function(){// seq, pg
                 location.href='/jaju/mypage/followingView'
              });
           });// each
         
         $('#followPagingDiv').html(data.mypagePaging.pagingHTML);
         
         }// else
      },
      error:function(err){
         console.log(err);
      }
   });   
});

// 전체 선택 또는 해제
$('#all').click(function(){
   // alter($('#all').attr('checked'));//checked 속성이 없어서 undefind으로 나온다.
   // alert($('#all').prop('checked'));//attr이 아니라 값으로 가져와야한다.

   if($('#all').prop('checked')){
      $('input[name=check]').prop('checked',true);
   }else {
      $('input[name=check]').prop('checked',false);
   }
});

// 선택삭제
$('#deleteBtn').click(function(){
   var count = $('input[name=check]:checked').length;
   
   // alert($('input[name=check]:checked').val());
   
   if(count==0){
      alert("삭제할 항목을 선택 하세요");
   }else {
      if(confirm("정말로 삭제 하시겠습니까?")){
         $('#myFollowForm').submit();
         alert('삭제 완료 되었습니다.');
      }else{
         alert('삭제에 실패했습니다. 다시 선택해주세요.');
      }
   }
});


// 클릭시 팔로우 판매목록(쪽지양식)
$(document).on("click","#follwSaleListA",function(){
// $('#follwSaleList').click(function(){
   
         var click_val = $(this).text();
         // alert(click_val);
      /*
		 * var _width = '500'; var _height = '500';
		 *  // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기 var _left = Math.ceil((
		 * window.screen.width - _width )/2); var _top = Math.ceil((
		 * window.screen.height - _height )/2);
		 * 
		 * window.open('/jaju/mypage/myFollowSaleList?followId='+click_val,
		 * 'my_Follow', 'resizable=no,status=no, width='+ _width +', height='+
		 * _height +', left=' + _left + ', top='+ _top );
		 */
      
   window.location.href = '/jaju/mypage/myFollowSaleList?followId='+click_val

         
});

