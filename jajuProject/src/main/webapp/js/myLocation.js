$('document').ready(function() {
	 var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
	  var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
	   var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
	   var area3 = ["대덕구","동구","서구","유성구","중구"];
	   var area4 = ["광산구","남구","동구",     "북구","서구"];
	   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
	   var area6 = ["남구","동구","북구","중구","울주군"];
	   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
	   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
	   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
	   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
	   var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
	   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
	   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
	   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
	   var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
	   var area16 = ["서귀포시","제주시","남제주군","북제주군"];

	 

	// 시/도 선택 박스 초기화

	   $("select[name^=sido]").each(function() {
	    $selsido = $(this);
	    $.each(eval(area0), function() {
	     $selsido.append("<option value='"+this+"'>"+this+"</option>");
	    });
	    $selsido.next().append("<option value=''>구/군 선택</option>");
	   });

	   

	   // 시/도 선택시 구/군 설정

	   $("select[name^=sido]").change(function() {
	    var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
	    var $gugun = $(this).next(); // 선택영역 군구 객체
	    $("option",$gugun).remove(); // 구군 초기화
	    
	 
	    if(area == "area0")
	     $gugun.append("<option value=''>구/군 선택</option>");
	    else {
	     $.each(eval(area), function() {
	      $gugun.append("<option value='"+this+"'>"+this+"</option>");
	     });
	    }
	  });
	   
	   
		  

	  });

//지역 설정 (지역등록)
$('#lobtn2').click(function(){
		
	   //alert($('option:selected',this).text());
	
	   //console.log(   ($('#id').val())   );
	   //console.log(($('#sido1').val()));
	   //console.log(($('#gugun1').val()));
	 //alert( ($('#sido1').val())  );
	 //alert( ($('#gugun1').val())  );
	   
	  //지역추가 버튼 누를때로 .. ajax로 데이터 보내기  저장(db보내기는) 그냥보내기.. 불러오기는 json
		$.ajax({
			type:'post',
			url:'/jaju/mypage/mylocationCount',
			data:
			{
				'id':$('#id').val() ,
			},
			dataType:'json',
			
			success: function(data){
				//console.log(JSON.stringify(data));
				$.each(data.list, function(index, items){
			
				//alert(items.location2_addr1 + items.location3_addr1 )	
				
				var count = items.location2_addr1 + items.location3_addr1;
					//alert(count);
				
				
				if(count==11){
					alert("주소는 3개까지 등록이 가능합니다. 변경을 원할 시 삭제해주세요.");
					return;
				}
				if($('#sido1').val()=="시/도 선택"){
					alert("시/도를 선택해주세요.");
					return;
				
				}if(count==01 || count==00) {
					
						
				$.ajax({
					type:'post',
					url:'/jaju/mypage/mylocationWrite',
					data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
					{
					'id' :$('#id').val() ,
					'sido1' :$('#sido1').val() ,
					'gugun1' :$('#gugun1').val()
					},
					//dataType:'json',
					async: false,
					success:function(){
						alert('2번 지역설정 완료.');
						//console.log(JSON.stringify(data));
						location.reload();
					},
					error:function(err){
						console.log("error발생"+err);
					}
				});//ajax
				
				
					
				}else if(count==10){
					
					
					$.ajax({
						type:'post',
						url:'/jaju/mypage/mylocationWrite2',
						data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
						{
						'id' :$('#id').val() ,
						'sido1' :$('#sido1').val() ,
						'gugun1' :$('#gugun1').val()
						},
						//dataType:'json',
						async: false,
						success:function(){
							alert('3번 지역설정 완료.');
							//console.log(JSON.stringify(data));
							location.reload();
							
						},
						error:function(err){
							console.log("error발생"+err);
						}
					});//ajax
				}//if
				});//each
				
			},
			error:function(err){
				console.log("error발생"+err);
			}
		});//ajax
	});

// db저장된 주소 목록 
$(function(){
	$.ajax({
		type:'post',
		url:'/jaju/mypage/mylocationCount',
		data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
		{
			'id':$('#id').val() ,
		},
		dataType:'json',
		
		success: function(data){
			console.log(JSON.stringify(data));
			$.each(data.list, function(index, items){
		
			//alert(items.location2_addr1 + items.location3_addr1 )	
			
			var count = items.location2_addr1 + items.location3_addr1;
				//alert(count);
			
			if(count==00) {
				$('#output_location2').hide();
				$('#output_location3').hide();
			}
				
				$.ajax({
					type: 'post',
					url : '/jaju/mypage/mylocationList',
					data: //나중에 session으로 대체
					{
					'id' :$('#id').val() ,
					//'id' :$('#id').val() ,
					},
					
					dataType: 'json',
					success: function(data){
						console.log(JSON.stringify(data));
					
						//$('#select_box_div').remove();
						
						$.each(data.list, function(index, items){	
						
							$('#output_location1').append($('<div/>',{
								text:'관심지역 1',
								id:'myLocation1'
							}))
							.append($('<span/>',{
								id:"location1_addr1",
								value:items.location1_addr1,
								text:items.location1_addr1,
							})).append($('<span/>',{
								id:"location1_addr2",
								value:items.location1_addr2,
								text:items.location1_addr2
							})).append($('<a/>',{
								class: "cancelBtn1A"
								
								}).append($('<span/>',{
										class:"cancelBtn1",
										id:"cancelBtn1",
										text:" "+""+" ",
										style:'vertical-align: text-bottom; color:#47597e; font-weight:bold; font-size:18px; '

								}))
							).appendTo($('#result_location'));
							
							
							
							$('#output_location2').append($('<div/>',{
								text:'관심지역 2',
								id:'myLocation2'
							})).append($('<span/>',{
								id:"location2_addr1",
								value:items.location2_addr1,
								text:items.location2_addr1,
								style:'text-align: center;'
							})).append($('<span/>',{
								id:"location2_addr2",
								value:items.location2_addr2,
								text:items.location2_addr2,
								style:'text-align: center;'
							})).append($('<a/>',{
								class:"cancelBtn2A",
								style:"cursor:pointer;"
								}).append($('<span/>',{
										class:"cancelBtn2",
										id:"cancelBtn2",
										text:" "+"삭제"+" ",
										style:'vertical-align: text-bottom; color:#47597e; font-weight:bold; font-size:18px; '
								}))
							).appendTo($('#result_location'));
							
							
							
							$('#output_location3').append($('<div/>',{
								text:'관심지역 3',
								id:'myLocation3'
							})).append($('<span/>',{
								id:"location3_addr1",
								value:items.location3_addr1,
								text:items.location3_addr1,
								style:'text-align: center;'
							})).append($('<span/>',{
								id:"location3_addr2",
								value:items.location3_addr2,
								text:items.location3_addr2,
								style:'text-align: center;'
							})).append($('<a/>',{
								class:"cancelBtn3A",
								style:"cursor:pointer;"
								}).append($('<span/>',{
										
									class:"cancelBtn3",
									id:"cancelBtn3",
									text:" "+"삭제"+" ",
									style:'vertical-align: text-bottom; color:#47597e; font-weight:bold; font-size:18px; '

								}))
							).appendTo($('#result_location'));
							
							
							if(count==00) {
								$('#output_location2').hide();
								$('#output_location3').hide();
							}else if(count==10) {
								$('#output_location2').show();
								$('#output_location3').hide();
							}else if(count==01) {
								$('#output_location2').hide();
								$('#output_location3').show();
							}else if(count==11) {	
								$('#output_location2').show();
								$('#output_location3').show();
							}//if				
						});//each
		
					},
					error: function(err){
						console.log("error발생"+err);
					}
				});	
						});//each
		},
		error:function(err){
			console.log("error발생"+err);
		}
	});//ajax	
});


$(document).on("click","#cancelBtn2",function(){
	if(confirm("정말로 삭제하시겠습니까?")){
		$.ajax({
			type:'post',
			url:'/jaju/mypage/mylocationDelete',
			data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
			{
			'id' :$('#id').val()
			},
			//dataType:'json',
			async: false,
			success:function(){
				alert('2번 지역 삭제 완료.');
				//console.log(JSON.stringify(data));
				location.reload();
				
			
			},
			error:function(err){
				console.log("error발생"+err);
			}
		});//ajax
	}else{}
});

$(document).on("click","#cancelBtn3",function(){
	if(confirm("정말로 삭제하시겠습니까?")){
		$.ajax({
			type:'post',
			url:'/jaju/mypage/mylocationDelete2',
			data: //{'id':$('#id').val()},//id값 가져가야 해당 로그인 한 사람의 관심정보 가져올 수 있음.
			{
			'id' :$('#id').val()
			},
			//dataType:'json',
			async: false,
			success:function(){
				alert('3번 지역 삭제 완료.');
				//console.log(JSON.stringify(data));
				location.reload();
				
			
			},
			error:function(err){
				console.log("error발생"+err);
			}
		});//ajax
	}else{}
		
});	

//location.jsp에 지도 불러오기 , 1. 현재 주소를 지도에 담아 표시 하기  / 2. 선택한 주소도 지도로 표시