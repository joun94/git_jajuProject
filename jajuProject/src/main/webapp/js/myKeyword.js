//초기 페이지 로딩 시 저장한 관심 키워드 불러오기.
$(function(){
	//alert($('#id').val());
	$.ajax({
		type:'post',
		url:'/jaju/mypage/getKeywordList',
		data: {'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		dataType:'json',
		success:function(data){
		//alert(data.mypageDTO.keyword_id);
			console.log("data.mypageDTO"+JSON.stringify(data));
				$('input[value='+data.mypageDTO.keyword1+']').prop('checked',true);
				$('input[value='+data.mypageDTO.keyword2+']').prop('checked',true);
				$('input[value='+data.mypageDTO.keyword3+']').prop('checked',true);
				$('input[value='+data.mypageDTO.keyword4+']').prop('checked',true);
				$('input[value='+data.mypageDTO.keyword5+']').prop('checked',true);			
		},error:function(err){
			console.log(" getKeywordList error발생"+err);
		}
	});//ajax
});

//등록 버튼 누를 시 db연동 후 완료 모달창 띄우기.
$('#sendBtn').click(function(){
	//유효성검사.
	var count = $('input[name=check]:checked').length;

	//alert("count?"+count);
	/*if(count==0){
		alert("키워드를 선택해주세요.");
	}
	else*/ 
	
	if(count>5){
		alert("5개 이상 선택이 불가능합니다.");
		//트리거로 다시 원 상태로 돌리기.
	}else{
		var count = $('input[name=check]:checked').length;
		var checkArray = [];
		$('input[name=check]:checked').each(function(i){
			checkArray.push($(this).val());
		});	
		var checkParams =
		{
			//'id':$('#id').val(), 이제 세션으로 아이디 값 받아와서 넘겨야한다.
			'id':$('#id').val(),
			//'checkList' : checkArray
			'keyword1':checkArray[0],
			'keyword2':checkArray[1],
			'keyword3':checkArray[2],
			'keyword4':checkArray[3],
			'keyword5':checkArray[4]
		};
		
						
		$.ajax({
			url : '/jaju/mypage/updateMyKeyword',
			type : 'post',
			dataType : 'json',
			async: false,
			data : checkParams,
			success:function(data){
				if(data.code=='OK'){
					alert(data.message);
				}else {
					alert(data.message);
				}
			},error:function(err){
				alert('myKeyword error발생'+err);
			}
		});
	}
	
/*	if(count<6){
		
		var count = $('input[name=check]:checked').length;
		
		$.ajax({
			type:'post',
			url:'/jaju/mypage/getKeywordCountNum',
			data:{'id':$('#id').val()}, //controller->jsp->js
			dataType:'text',
			
			success:function(data){
				//data==null==선택가능 개수, 먼저 alert으로 체크.
				alert("db 키워드 null 은 몇개?  = "+ data+"개 입니다.==등록가능한 수");
				
				//체크박스 선택개수
				var count = $('input[name=check]:checked').length;

				if(data=='0'){
					var count = $('input[name=check]:checked').length;
					var checkArray = [];
					$('input[name=check]:checked').each(function(i){
						checkArray.push($(this).val());
					});	
					var checkParams =
					{
						//'id':$('#id').val(), 이제 세션으로 아이디 값 받아와서 넘겨야한다.
						'id':$('#id').val(),
						//'checkList' : checkArray
						'keyword1':checkArray[0],
						'keyword2':checkArray[1],
						'keyword3':checkArray[2],
						'keyword4':checkArray[3],
						'keyword5':checkArray[4]
					};
					
									
					$.ajax({
						url : '/jaju/mypage/updateMyKeyword',
						type : 'post',
						dataType : 'json',
						async: false,
						data : checkParams,
						success:function(data){
							if(data.code=='OK'){
								alert(data.message);
							}else {
								alert(data.message);
							}
						},error:function(err){
							alert('myKeyword error발생'+err);
						}
					});
				}
				//선택수 < 키워드등록가능수  라면 insert하러 가자 myKeyword 주소 불러서 db에 넣기
				else if(data!='0'){
					//updateMyKeyword();
					var checkArray = [];
					$('input[name=check]:checked').each(function(i){
						checkArray.push($(this).val());
					});	
					var checkParams =
					{
						//'id':$('#id').val(), 이제 세션으로 아이디 값 받아와서 넘겨야한다.
						
						'id':$('#id').val(),
						
						//'checkList' : checkArray
						'keyword1':checkArray[0],
						'keyword2':checkArray[1],
						'keyword3':checkArray[2],
						'keyword4':checkArray[3],
						'keyword5':checkArray[4]
					};
					
					$.ajax({
						url : '/jaju/mypage/updateMyKeyword',
						type : 'post',
						dataType : 'json',
						async: false,
						data : checkParams,
						success:function(data){
							if(data.code=='OK'){
								alert(data.message);
							}else {
								alert(data.message);
							}
						},error:function(err){
							alert('updateMyKeyword error발생'+err);
						}
					});
				}//else
				
			},error:function(err){
				alert("getKeywordCountNum 에러발생");
			}
		});
	}//if*/
});
//-------------------------------------------------------------
//다시 선택 시 초기화. 
$('#resetBtn').click(function(){

	var count = $('input[name=check]:checked').length;
	//다중선택값 확인. 
	$('input[name=check]:checked').each(function() {
	      this.checked = true; //checked 처리
	      if(this.checked){//checked 처리된 항목의 값
	        console.log(this.value); 
	      }
	 });
	//선택된 값이 있다면, 다시 초기화시키기.
	if(count>0){
		$('input[name=check]').prop('checked',false);
	}
});

//-------------------------------------------------------------
