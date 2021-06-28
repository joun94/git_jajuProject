$(function(){
	//이름 
	$.ajax({
		type:'post',
		url:'/jaju/mypage/getMyProfileInfo',
		data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		 {'id':$('#id').val()},
		dataType:'json',
		success:function(data){
			var text
			if(data.memberDTO.member_name==""){
				text = "no name"; 
			}else{
				text=data.memberDTO.member_name;
			}
			
			//alert("name? " +data);
			
			console.log("getMyProfileInfo"+data);
			if(data.memberDTO.member_image==null){
				$('.profile_image').attr('src','/jaju/storage/unnamed.png');
			}
			else{
				$('.profile_image').attr('src','/jaju/storage/'+data.memberDTO.member_image);
			}
			
			$('#name').append($('<p/>',{
				class: 'info',
				text: text+'님',
				style:'font-weight:bold; font-weight: 700; font-size: 20px;color: #47597e; height: 20px; flex-grow: 3; margin-top: 5px;  position: relative;'
			}).append($('<span/>',{
				class: 'expire'
			})));
			
		},error:function(err){
			consoloe.log("err발생"+err);
		}
	});//getMemberName
	
	//매너온도
	$.ajax({
		type:'post',
		url:'/jaju/mypage/getMemberMannerTemp',
		data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		 {'id':$('#id').val()},
		dataType:'text',
		success:function(data){
			
			var text = data;
			text= Number(data);
			
			//alert(typeof text);   
						
			if(data==""){
				text = "no_info"; 
			}else{
				text=' '+data;
			}
			//alert("형 변환 한 data" + text)
			console.log("getMemberMannerTemp"+text);
			if(text==36.5){
				$('#total_grade').append($('<span/>',{
					id:'fas fa-thermometer-half',
				})).append($('<i/>',{
					class:"fas fa-thermometer-half",
					style:"font-size:25px;color:#37B24D;"
				})).append($('<span/>',{
					text: text+'℃',
					style:'color:#37B24D; font-weight:bold; font-weight: 700; font-size: 20px; line-height: 28px; flex-grow: 3; position: relative;'
				}));
			}
			else if(text>=25.5&&text<=35.5){
				$('#total_grade').append($('<span/>',{
					id:'fas fa-thermometer-quarter',
				})).append($('<i/>',{
					class:"fas fa-thermometer-quarter",
					style:"font-size:25px;color:#0D3A65;"
				})).append($('<span/>',{
					text: text+'℃',
					style:'color:#0D3A65; font-weight:bold; font-weight: 700; font-size: 20px; line-height: 28px; flex-grow: 3; position: relative;'
				}));
			}

			else if(text>=35.6&&text<=45.5){
				$('#total_grade').append($('<span/>',{
					id:'fas fa-thermometer-half',
				})).append($('<i/>',{
					class:"fas fa-thermometer-half",
					style:"font-size:25px;color:#FFAD13;"
				})).append($('<span/>',{
					text: text+'℃',
					style:'color:#FFAD13; font-weight:bold; font-weight: 700; font-size: 20px; line-height: 28px; flex-grow: 3; position: relative;'
				}));
			}
			
			else if(text>=45.6&&text<=65.5){
				$('#total_grade').append($('<span/>',{
					id:'fas fa-thermometer-full',
				})).append($('<i/>',{
					class:"fas fa-thermometer-full",
					style:"font-size:25px;color:#F76707;"
				})).append($('<span/>',{
					text: text+'℃',
					style:'color:#F76707; font-weight:bold; font-weight: 700; font-size: 20px; line-height: 28px; flex-grow: 3; position: relative;'
				}));
			}
			
		},error:function(err){
			consoloe.log("err발생"+err);
		}
	});//getMemberMannerTemp
	
	
	$.ajax({
		type:'post',
		url:'/jaju/mypage/mysaleStateCount',
		
		data: {'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		dataType:'json',
		
		success: function(data){
			
			console.log(JSON.stringify(data));
			
			$.each(data.list, function(index, items){
				//console.log(items.dealCount),console.log(items.saleCount),console.log(items.buyCount)
				var sale_text;
				var buy_text;
				if(items.saleCount=="0"){
					sale_text = '판매완료 '+items.saleCount+'건';//"판매를 시작해보세요!"; 
				}else if(items.saleCount!=""){
					sale_text='판매완료 '+items.saleCount+'건';
				}
				
				if(items.buyCount=="0"){
					buy_text = '구매완료 '+items.buyCount+'건';//"구매를 시작해보세요!"
				}else if(items.buyCount!=""){
					buy_text ='구매완료 '+items.buyCount+'건';
				}
			
				$('#sale').append($('<span/>',{
					class: 'info',
					text: sale_text,
					style:'margin-top: 20px; font-weight:bold; font-weight: 700; font-size: 17px;color: #47597e;'
				}));
				
				$('#buy').append($('<span/>',{
					class: 'info',
					text: buy_text,
					style:'font-weight:bold; font-weight: 700; font-size: 17px; color: #47597e; '
				}));
				
				$('#deal').append($('<p/>',{
					class: 'info',
					text: '판매중 '+items.dealCount+'건',
					style:'margin-top: -5px; font-weight:bold; font-weight: 700; font-size: 18px; color: #47597e;  flex-grow: 3; '
				}));
			}); //each	
		},
		error:function(err){
			console.log("saleStateCount에러"+err);
		}	
	});//mysaleStateCount ajax
	
	//찜목록 건수
	$.ajax({
		type:'post',
		url:'/jaju/mypage/getTotalScrapCount',
		data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		 {'id':$('#id').val()},
		dataType:'text',
		success:function(data){
			
			console.log("getTotalScrapCount"+data);
			$('#scrap_count').append($('<p/>',{
				class: 'info',
				text: data+'건'
			}).append($('<span/>',{
				//class: 'expire'
			})));
			
		},error:function(err){
			consoloe.log("err발생"+err);
		}
	});//getTotalScrapCount
	
	//팔로우 건수
	$.ajax({
		type:'post',
		url:'/jaju/mypage/getTotalFollowCount',
		data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		 {'id':$('#id').val()},
		dataType:'text',
		success:function(data){
			
			console.log("getTotalFollowCount"+data);
			$('#follow_count').append($('<p/>',{
				class: 'info',
				text: data+'건'
			}).append($('<span/>',{
				class: 'expire'
			})));
			
		},error:function(err){
			consoloe.log("err발생"+err);
		}
	});//getTotalFollowCount
	
	//메세지 건수
	$.ajax({
		type:'post',
		url:'/jaju/mypage/getTotalMessageCount',
		data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		 {'id':$('#id').val()},
		dataType:'text',
		success:function(data){
			
			console.log("getTotalMessageCount"+data);
			$('#message_count').append($('<p/>',{
				class: 'info',
				text: data+'건'
			}).append($('<span/>',{
				class: 'expire'
			})));
			
		},error:function(err){
			consoloe.log("err발생"+err);
		}
	});//getTotalMessageCount
	
	//읽지않은 메세지 건수
	$.ajax({
		type:'post',
		url:'/jaju/mypage/getNotReadMessageCount',
		data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		 {'id':$('#id').val()},
		dataType:'text',
		success:function(data){
			
			console.log("getNotReadMessageCount"+data);
			$('#expire').append($('<span>',{
				class: 'info',
				text: '읽지않음: '+data+'개',
				style:'font-size: 12px; color: #999; left: 0;'
			}).append($('<span/>',{
				//class: 'expire'
			})));
			
		},error:function(err){
			consoloe.log("err발생"+err);
		}
	});//getTotalMessageCount
	
});

$('#change_image').click(function(){

	var popupWidth=500;
	var popupHeight=620;

	 var popupX = (window.screen.width/2)-(popupWidth/2);
	 // 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

	 var popupY= (window.screen.height/2)-(popupHeight/2);
	 // 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

/* 	 window.open("/jaju/mypage/myMessage", 
			 "my_Message", 
			 "width=500 height=500 left=500 top=700 scrollbars=yes");  */
	
	window.open("/jaju/mypage/mypageChangeImg?id="+$('#id').val(), 
			 "mypageChangeImg", 
			 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY); 
	

});