$(function(){
	$("#sortinSelect").val($('#sortinSelected').val()).prop("selected", true);
	
	if($('#memId').val() == ''){
		$.ajax({
			type: 'post',
			url: '/jaju/saleboard/getSaleboardList',
			data: {'pg': $('#pg').val(), 'sortinSelect': $('#sortinSelect').val()},
			dataType: 'json',
			success: function(data){
				//alert(JSON.stringify(data));
				
				$.each(data.list, function(index,items){
					
					$('<li/>').append($('<div/>',{
						class: 'item',
						id: items.sale_seq
					}).append($('<div/>',{
						class: 'thumb'
					}).append($('<a/>',{
						class: 'img'
					}).append($('<img/>',{
						src:"/jaju/storage/" + items.sale_image1,
						width:"308",
						height:"396"
					})))).append($('<a/>',{
						class: 'info'
						
					}).append($('<span/>',{
						class: 'name',
						text: items.sale_subject
						
					})).append($('<span/>',{
						class: 'cost'
					}).append($('<span/>',{
						class:"price",
						text: items.sale_price+'원'
					})))
					)).appendTo($('.inner_listgoods  .list'));
					
				}); //each
				$(document).on('click', '.item', function(){
					if($('#memId').val() == ''){
						alert("먼저 로그인 하세요");
					}else {
						var seq = $(this).attr('id');
						location.href = '/jaju/saleboard/saleboardView?sale_seq='+seq+'&pg=1';
					}
				});
				
				$('#pagingDiv').html(data.saleboardPaging.pagingHTML);
				
			}, error: function(err){
				console.log(err);
				alert('리스트 생성 오류');
				
			}
		});
	} else{
		
		
		$.ajax({   
			type:'post',
			url:'/jaju/mypage/getKeywordList',
			data: {'id':$('#memId').val()},
			dataType:'json',
			success:function(data){
				//alert(JSON.stringify(data));
				
				var keywordSw = 0;
				
				var keyword1 = data.mypageDTO.keyword1;
				var keyword2 = data.mypageDTO.keyword2;
				var keyword3 = data.mypageDTO.keyword3;
				var keyword4 = data.mypageDTO.keyword4;				
				var keyword5 = data.mypageDTO.keyword5;
				
				if((keyword1 != null) || (keyword2 != null) || (keyword3 != null) || (keyword4 != null) || (keyword5 != null)){
					keywordSw = 1;
				}
					
				//alert(keyword1);
				
					$.ajax({
						type: 'post',
						url : '/jaju/mypage/mylocationList',
						data: {
								'id' :$('#memId').val() ,
								},		
						dataType: 'json',
						success: function(data){
							//alert(JSON.stringify(data));
								
							$.each(data.list, function(index,items){
								var location1 = items.location1_addr2;
								var location2 = items.location2_addr2;
								var location3 = items.location3_addr2;
								
								//alert(location1);
								
								$.ajax({
									type: 'post',
									url: '/jaju/saleboard/getSaleboardListLogin',
									data: {'pg': $('#pg').val(), 'sortinSelect': $('#sortinSelect').val(),
										   'location1': location1, 'location2': location2, 'location3': location3,
										   'keyword1':keyword1,'keyword2':keyword2,'keyword3':keyword3,
										   'keyword4':keyword4,'keyword5':keyword5, 'keywordSw': keywordSw},
									dataType: 'json',
									success: function(data){
										//alert(JSON.stringify(data));
										
									$.each(data.list, function(index,items){
										//alert(items.member_sigungu);
										
											$('<li/>').append($('<div/>',{
												class: 'item',
												id: items.sale_seq
											}).append($('<div/>',{
												class: 'thumb'
											}).append($('<a/>',{
												class: 'img'
											}).append($('<img/>',{
												src:"/jaju/storage/" + items.sale_image1,
												width:"308",
												height:"396"
											})))).append($('<a/>',{
												class: 'info'
												
											}).append($('<span/>',{
												class: 'name',
												text: items.sale_subject
												
											})).append($('<span/>',{
												class: 'cost'
											}).append($('<span/>',{
												class:"price",
												text: items.sale_price+'원'
											})))
											)).appendTo($('.inner_listgoods  .list'));
											
										}); //each
										$(document).on('click', '.item', function(){					
											var seq = $(this).attr('id');
											location.href = '/jaju/saleboard/saleboardView?sale_seq='+seq+'&pg=1';
										});
										
										$('#pagingDiv').html(data.saleboardPaging.pagingHTML);
										
									}, error: function(err){
										console.log(err);
										alert('리스트 생성 오류');
										
									}
								});
								
							});
							}, error:function(err){
								console.log(err);
								alert("location 불러오기 에러");
							}
						});// ajax
				
				
			},error:function(err){
				console.log("error발생"+err);
			}
		});//ajax

	}
	
});

$('#searchBtn').click(function(event,str){
	
	
	if($('#searchText').val() == ""){
		alert("검색어를 입력해 주세요");
		$('#searchText').focus();
	} else{
		if($('#memId').val() == ''){
			$.ajax({
				type: 'post',
				url: '/jaju/saleboard/getSearchSaleboardList',
				data: {'pg': $('#searchPg').val(), 'sale_category': $('#selectBox').val(), 'searchText' : $('#searchText').val(), 'sortinSelect': $('#sortinSelect').val()},
				dataType: 'json',
				success: function(data){
					//alert(JSON.stringify(data));
					//alert($('#searchPg').val());
					$('.inner_listgoods .list li').remove();
					
					$.each(data.list, function(index,items){
						
						$('<li/>').append($('<div/>',{
							class: 'item',
							id: items.sale_seq
						}).append($('<div/>',{
							class: 'thumb'
						}).append($('<a/>',{
							class: 'img'
						}).append($('<img/>',{
							src:"/jaju/storage/" + items.sale_image1,
							width:"308",
							height:"396"
						})))).append($('<a/>',{
							class: 'info'
							
						}).append($('<span/>',{
							class: 'name',
							text: items.sale_subject
							
						})).append($('<span/>',{
							class: 'cost'
						}).append($('<span/>',{
							class:"price",
							text: items.sale_price+'원'
						})))
						)).appendTo($('.inner_listgoods  .list'));
						
					}); //each
					$(document).on('click', '.item', function(){
						if($('#memId').val() == ''){
						alert("먼저 로그인 하세요");
						}else {
							var seq = $(this).attr('id');
							location.href = '/jaju/saleboard/saleboardView?sale_seq='+seq+'&pg=1';
					}
					});
					
					$('#pagingDiv').html(data.saleboardPaging.pagingHTML);
					
					if(str != 'research'){
						$('#searchPg').val(1);
					}
					
				}, error: function(err){
					console.log(err);
					alert('검색 리스트 생성 오류');
					
				}
			});
		} else{
			
			
			$.ajax({   
				type:'post',
				url:'/jaju/mypage/getKeywordList',
				data: {'id':$('#memId').val()},
				dataType:'json',
				success:function(data){
					//alert(JSON.stringify(data));
					
					var keywordSw = 0;
					
					var keyword1 = data.mypageDTO.keyword1;
					var keyword2 = data.mypageDTO.keyword2;
					var keyword3 = data.mypageDTO.keyword3;
					var keyword4 = data.mypageDTO.keyword4;				
					var keyword5 = data.mypageDTO.keyword5;
					
					if((keyword1 != null) || (keyword2 != null) || (keyword3 != null) || (keyword4 != null) || (keyword5 != null)){
						keywordSw = 1;
					}
						
					//alert(keyword1);
					
						$.ajax({
							type: 'post',
							url : '/jaju/mypage/mylocationList',
							data: {
									'id' :$('#memId').val() ,
									},		
							dataType: 'json',
							success: function(data){
								//alert(JSON.stringify(data));
									
								$.each(data.list, function(index,items){
									var location1 = items.location1_addr2;
									var location2 = items.location2_addr2;
									var location3 = items.location3_addr2;
									
									//alert(location1);
									
									$.ajax({
										type: 'post',
										url: '/jaju/saleboard/getSearchSaleboardListLogin',
										data: {'pg': $('#searchPg').val(), 'sale_category': $('#selectBox').val(), 'sortinSelect': $('#sortinSelect').val(),
											   'searchText' : $('#searchText').val(), 'sortinSelect': $('#sortinSelect').val(),
											   'location1': location1, 'location2': location2, 'location3': location3},
										dataType: 'json',
										success: function(data){
											$('.inner_listgoods .list li').remove();
											//alert($('#searchPg').val());
											$.each(data.list, function(index,items){
												
												$('<li/>').append($('<div/>',{
													class: 'item',
													id: items.sale_seq
												}).append($('<div/>',{
													class: 'thumb'
												}).append($('<a/>',{
													class: 'img'
												}).append($('<img/>',{
													src:"/jaju/storage/" + items.sale_image1,
													width:"308",
													height:"396"
												})))).append($('<a/>',{
													class: 'info'
													
												}).append($('<span/>',{
													class: 'name',
													text: items.sale_subject
													
												})).append($('<span/>',{
													class: 'cost'
												}).append($('<span/>',{
													class:"price",
													text: items.sale_price+'원'
												})))
												)).appendTo($('.inner_listgoods  .list'));
												
											}); //each
											$(document).on('click', '.item', function(){
												if($('#memId').val() == ''){
												alert("먼저 로그인 하세요");
												}else {
													var seq = $(this).attr('id');
													location.href = '/jaju/saleboard/saleboardView?sale_seq='+seq+'&pg=1';
											}
											});
											
											$('#pagingDiv').html(data.saleboardPaging.pagingHTML);
											
											if(str != 'research'){
												$('#searchPg').val(1);
											}
											
										}, error: function(err){
											console.log(err);
											alert('리스트 생성 오류');
											
										}
									});
									
								});
								}, error:function(err){
									console.log(err);
									alert("location 불러오기 에러");
								}
							});// ajax
					
					
				},error:function(err){
					console.log("error발생"+err);
				}
			});//ajax

		}
	}
	
});

$('#sortinSelect').change(function(){

	$('.inner_listgoods .list li').remove();
	if($('#searchText').val() != ""){
		$('#searchBtn').trigger('click');
	} else{
	
		if($('#memId').val() == ''){
			$.ajax({
				type: 'post',
				url: '/jaju/saleboard/getSaleboardList',
				data: {'pg': 1, 'sortinSelect': $('#sortinSelect').val()},
				dataType: 'json',
				success: function(data){
					//alert(JSON.stringify(data));
					$('.inner_listgoods .list li').remove();
					
					$.each(data.list, function(index,items){
						
						$('<li/>').append($('<div/>',{
							class: 'item',
							id: items.sale_seq
						}).append($('<div/>',{
							class: 'thumb'
						}).append($('<a/>',{
							class: 'img'
						}).append($('<img/>',{
							src:"/jaju/storage/" + items.sale_image1,
							width:"308",
							height:"396"
						})))).append($('<a/>',{
							class: 'info'
							
						}).append($('<span/>',{
							class: 'name',
							text: items.sale_subject
							
						})).append($('<span/>',{
							class: 'cost'
						}).append($('<span/>',{
							class:"price",
							text: items.sale_price+'원'
						})))
						)).appendTo($('.inner_listgoods  .list'));
						
					}); //each
					$(document).on('click', '.item', function(){
						if($('#memId').val() == ''){
							alert("먼저 로그인 하세요");
						}else {
							var seq = $(this).attr('id');
							location.href = '/jaju/saleboard/saleboardView?sale_seq='+seq+'&pg=1';
						}
					});
					
					$('#pagingDiv').html(data.saleboardPaging.pagingHTML);
					
				}, error: function(err){
					console.log(err);
					alert('리스트 생성 오류');
					
				}
			});
		} else{
			
			
			$.ajax({   
				type:'post',
				url:'/jaju/mypage/getKeywordList',
				data: {'id':$('#memId').val()},
				dataType:'json',
				success:function(data){
					//alert(JSON.stringify(data));
					
					var keywordSw = 0;
					
					var keyword1 = data.mypageDTO.keyword1;
					var keyword2 = data.mypageDTO.keyword2;
					var keyword3 = data.mypageDTO.keyword3;
					var keyword4 = data.mypageDTO.keyword4;				
					var keyword5 = data.mypageDTO.keyword5;
					
					if((keyword1 != null) || (keyword2 != null) || (keyword3 != null) || (keyword4 != null) || (keyword5 != null)){
						keywordSw = 1;
					}
						
					//alert(keyword1);
					
						$.ajax({
							type: 'post',
							url : '/jaju/mypage/mylocationList',
							data: {
									'id' :$('#memId').val() ,
									},		
							dataType: 'json',
							success: function(data){
								//alert(JSON.stringify(data));
									
								$.each(data.list, function(index,items){
									var location1 = items.location1_addr2;
									var location2 = items.location2_addr2;
									var location3 = items.location3_addr2;
									
									//alert(location1);
									
									$.ajax({
										type: 'post',
										url: '/jaju/saleboard/getSaleboardListLogin',
										data: {'pg': 1, 'sortinSelect': $('#sortinSelect').val(),
											   'location1': location1, 'location2': location2, 'location3': location3,
											   'keyword1':keyword1,'keyword2':keyword2,'keyword3':keyword3,
											   'keyword4':keyword4,'keyword5':keyword5, 'keywordSw': keywordSw},
										dataType: 'json',
										success: function(data){
											//alert(JSON.stringify(data));
											$('.inner_listgoods .list li').remove();
										$.each(data.list, function(index,items){
											//alert(items.member_sigungu);
											
												$('<li/>').append($('<div/>',{
													class: 'item',
													id: items.sale_seq
												}).append($('<div/>',{
													class: 'thumb'
												}).append($('<a/>',{
													class: 'img'
												}).append($('<img/>',{
													src:"/jaju/storage/" + items.sale_image1,
													width:"308",
													height:"396"
												})))).append($('<a/>',{
													class: 'info'
													
												}).append($('<span/>',{
													class: 'name',
													text: items.sale_subject
													
												})).append($('<span/>',{
													class: 'cost'
												}).append($('<span/>',{
													class:"price",
													text: items.sale_price+'원'
												})))
												)).appendTo($('.inner_listgoods  .list'));
												
											}); //each
											$(document).on('click', '.item', function(){					
												var seq = $(this).attr('id');
												location.href = '/jaju/saleboard/saleboardView?sale_seq='+seq+'&pg=1';
											});
											
											$('#pagingDiv').html(data.saleboardPaging.pagingHTML);
											
										}, error: function(err){
											console.log(err);
											alert('리스트 생성 오류');
											
										}
									});
									
								});
								}, error:function(err){
									console.log(err);
									alert("location 불러오기 에러");
								}
							});// ajax
					
					
				},error:function(err){
					console.log("error발생"+err);
				}
			});//ajax

		}
	}
	
});
