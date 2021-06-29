//managerMember.js 에서 일어나는 일 : 회원정보 가져오기(아이디+이메일,가입일, 게시글 수, 판매내역수, 구매내역수)

//선택한 회원 --> 활동중지, 강제탈퇴

//닉네임, 이메일로 검색 
//정렬 --최신순, 이름순, 

//신고누적 횟수 5이상은 블랙리스트등록

$(function(){
	//창이 열리자 마자 , 모든 회원 정보 가져오기. 
	$.ajax({
		url: '/jaju/manager/getManagerMember',
		type:'post',
		dataType:'json',
		success:function(data){
			$.each(data.list, function(index, items){
				//console.log(JSON.stringify(data));
				$('<tr/>').append($('<td/>',{
					class:'chk',
				}).append($('<input/>',{
					type:"checkbox",
					name:"check",
					id:"check_id",
					value:items.member_id,
					class:"checkradio_styled"
				}))).append($('<td/>').append($('<div/>',{
					class:'table_data'
				}).append($('<a/>',{
					href:'#',
					class:'user_info',
					text:items.member_id
				})))).append($('<td/>',{
					class:'member_email',
					text:items.member_email,
					style:'text-align:center;'
				})).append($('<td/>').append($('<div/>',{
					class:'table_data',
					text:'회원',
					style:'text-align:center;'
				}))).append($('<td/>',{
					class:'signin_date_span',
					text:items.member_date,
					style:'text-align:center;'	
				})).append($('<td/>',{
					class:'member_manner_span',
					text:items.member_manner+'℃',
					style:'text-align:center;'					
				})).append($('<td/>',{
					class:'member_sell_span',
					text:'항번 더 아작스 갔다와아함 sell'	
				})).append($('<td/>',{
					class:'num_buy_span',
					text:'항번 더 아작스 갔다와아함 buy'	
				})).appendTo($('#memberListBody'));
				
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

			});
			
			
		},error:function(err){
			console.log("관리자-회원 쪽 에러발생"+err);
		}		
	});//ajax
});
//활동중지 눌렀을 경우--> 상태를 1로 변경? default : 0 .그리고 신고 누적횟수 update.
$('#break_member_id').click(function(){
	console.log($('input[name=check]:checked').val());
	
	
});

//강제탈퇴 눌렀을 경우.--> 삭제처리하고, 해당 아이디는 다시 등록 할 수 없도록 하기.새로운 table파서 거기 등록? + 블랙리스트로.
$('#delete_member_id').click(function(){
	console.log($('input[name=check]').val());
	var count = $('input[name=check]:checked').length;
	var count = $('input[name=check]:checked').length;
	//유효성검사
	if(count==0){
		alert("강제탈퇴 처리할 아이디를 선택 하세요");
	}
	else {
		if(confirm("정말로 삭제 하시겠습니까?")){
			$('#myScrapForm').submit();
			alert('탈퇴 처리 완료 되었습니다.');
			//location.reload();
		}else{
			alert('탈퇴 처리에 실패했습니다. 다시 선택해주세요.');
		}
	}
});

/*
$('#btnGetChilds').click(function () {  
    var childCheckBoxes = $("ul.check ul li input[type='checkbox']");
    
    var values = "";
    
    for(i=0; i< childCheckBoxes.length; i++)
    {
        values += childCheckBoxes[i].value + "\n";
    }    
    
    alert(values);
});
*/