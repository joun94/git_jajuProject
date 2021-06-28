
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/jaju/css/myLocation.css" />

	<%--Geolocation API(GPS)이건 현재 위치 가져오는 api --%>
	<jsp:include page="/mypage/mypageMain.jsp" />





	<!-- 관심지역등록 -->
	<div class="myLocationFormDiv">
	<input type="hidden" id="id" name="id" value="${memId}">
	<!-- controller에서 넘어온 id ,memId 는 임의로 만들어둔 변수명.-->
		<div class="head_aticle">
			<h2 class="tit">&emsp;관심 지역 등록</h2>
		</div>

		<div class="board-header-container">
			<ul class="list-description">
				<li>관심 지역을 등록하는 곳 입니다.</li>
				<!-- <li>관심지역은 기본 주소포함 최대 3개 까지 가능합니다. 시도, 시군구, 동 을 모두 선택해주세요.</li> -->
				<li>관심지역은 기본 주소포함 최대 3개 까지 가능합니다. 시도, 시군구 를 모두 선택해주세요.</li>
			</ul>
		</div>

		<p class="localset_none">
			<strong>'지역추가'</strong> 버튼을 누르고 지역을 설정하십시오.<br>지역은 최대 3개까지 설정
			가능하며, 신규지역 추가 시 관심지역에 따라 매물이 보여집니다.<br>관심지역 1번은 회원님의 주소 입니다.
		</p>

		<!-- select Box 로 선택 -->
		<!-- 	<table border="1" id="myLocationTable" cellpadding="5" width="550" bordercolor="#263343" align="center" frame="hsides" rules="rows">
		<tr>
			<td align="center" width="150px">시도</td>
			<td width="150px" align="center">시.군.구</td>
			<td width="150px" align="center">동</td>
		</tr>
	</table> -->

		<!--3개 만들고   -->

		<!-- 여기에 지도 넣기, script 는 아래쪽에 작성.  -->
	
		<div id="output_location">
							<!-- 이거 뺴기? -->
							<div id="myLocation" style="margin-left: 80px";>회원 주소</div>
							<div id="selected_sido"></div>
							<div id="selected_sigungu"></div>
							<a href="#"><span></span></a>
							<!--  <span id="selected_dong">동</span> -->
		</div>
						
						
		<div id="map"  style="width:100%;height:350px;"></div>

		<table>
			<tr>
				<td>
					<div class="console" id="result_location">
						
						<!-- 동적 드롭 다운으로 선택한 지역 보여주기.-->
						<div id="output_location1"></div>
						<div id="output_location2"></div>
						<div id="output_location3"></div>
					</div>
				</td>
			</tr>

		</table>

		<!-- select box -->
		<br>
		<div id="select_box_div" style="text-align:center;">

			<select name="sido1" id="sido1" style="width: 150px; height: 30px; display: inline-block;"></select>
			<select name="gugun1" id="gugun1" style="width: 150px; height: 30px; display: inline-block;"></select>
			<!-- <select name="dong1" id="dong1"></select> -->
			<!-- 일단은 동부분 생략  -->
			<a style="cursor: pointer;"><span id="lobtn2" class="lobtn2"><strong style="font-size: 18px;vertical-align: unset; ">&emsp;&emsp;지역추가&emsp;&emsp;</strong></span></a>
		</div>
	</div>

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
		
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=466eb1d978f6edc33af11001cc8fd539&libraries=services"></script>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appKey=466eb1d978f6edc33af11001cc8fd539"></script>


	<script src="/jaju/js/myLocation.js"></script>

	<script type="text/javascript">
	$.ajax({
		
		url:'/jaju/mypage/getMemberLocation',
		type:'post',
		data://나중에 session으로 대체
		{
			'id' :$('#id').val() ,
			//'id' :$('#id').val() ,
		},
		dataType: 'json',
		success: function(data){
			
			console.log("mylocationDTO"+JSON.stringify(data));
			if(data.mylocationDTO.location1_addr1==null){
				$('#output_location').hide();
			}
			
			$('#selected_sido').html(data.mylocationDTO.location1_addr1);
			$('#selected_sigungu').html(data.mylocationDTO.location1_addr2);

			
			var address=data.mylocationDTO.location1_addr1+' '+data.mylocationDTO.location1_addr2; 
			
			//alert(address);
			
			/*===========받은 주소로 현재 위치띄우기.===========*/
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 5 // 지도의 확대 레벨
		    };  

			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(address, function(result, status) {
			
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
			        
			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0; ">관심지역 1</div>'
			      		
			        });
			        //infowindow.open(map, marker);
			       infowindow.close();
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});    	
		},
		error: function(err){
			console.log("error발생"+err);
		}
	});
	
		/*이건 현재 위치 가져오는 코드*/
		
		/* var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 5	// 지도의 확대 레벨 
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// HTML5의 geolocation으로 사용할 수 있는지 확인인합니다 
		if (navigator.geolocation) {

			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {

				var lat = position.coords.latitude, // 위도
				lon = position.coords.longitude; // 경도

				var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
				message = '<div style="padding:5px;">현재위치</div>'; // 인포윈도우에 표시될 내용입니다

				// 마커와 인포윈도우를 표시합니다
				DrawCircle(lat,lon,1000);
				displayMarker(locPosition, message);
				
			});

		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
			var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), message = 'geolocation을 사용할수 없어요..'
			displayMarker(locPosition, message);
		}

		// 지도에 마커와 인포윈도우를 표시하는 함수입니다
		function displayMarker(locPosition, message) {

			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map : map,
				position : locPosition
			});

			var iwContent = message, // 인포윈도우에 표시할 내용
			iwRemoveable = true;

			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
				content : iwContent,
				removable : iwRemoveable
			});

			// 인포윈도우를 마커위에 표시합니다 
			infowindow.open(map, marker);

			// 지도 중심좌표를 접속위치로 변경합니다
			map.setCenter(locPosition);
		}
		
		
		function DrawCircle(lat,lon,radius){
			
		console.log(lat,lon,radius);
			
		// 지도에 표시할 원을 생성합니다
		/*var circle = new kakao.maps.Circle({
		    center : new kakao.maps.LatLng(lat, lng),  // 원의 중심좌표 입니다 
		    radius: 100, // 미터 단위의 원의 반지름입니다 
		    strokeWeight: 5, // 선의 두께입니다 
		    strokeColor: '#75B8FA', // 선의 색깔입니다
		    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		    strokeStyle: 'solid', // 선의 스타일 입니다
		    fillColor: '#CFE7FF', // 채우기 색깔입니다
		    fillOpacity: 0.7  // 채우기 불투명도 입니다   
		    
		});
		var circle = new kakao.maps.Circle({
			map: map, // 원을 표시할 지도 객체
			center : new kakao.maps.LatLng(lat, lon), // 지도의 중심 좌표
			radius : 100, // 원의 반지름 (단위 : m)
			fillColor: '#FF0000', // 채움 색
			fillOpacity: 0.5, // 채움 불투명도
			strokeWeight: 3, // 선의 두께
			strokeColor: '#FF0000', // 선 색
			strokeOpacity: 0.9, // 선 투명도 
			strokeStyle: 'solid' // 선 스타일
		});	
		// 지도에 원을 표시합니다 
		circle.setZIndex(1);
		circle.setMap(map); 
		} */
		
	</script>
