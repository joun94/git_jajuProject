$(function(){
	
	$.ajax({
		url:'/jaju/mypage/getMyScrapList',
		type:'post',
		data:
		{
			'id':$('#id').val(),
			//'id':'hihi',
			'pg':$('#pg').val()
			//'pg':'2'
		},
		dataType:'json',
		
		success:function(data){
			
			console.log(JSON.stringify(data));
			//$('#row').remove();
			
			if(JSON.stringify(data.list)=='[]' && data.myScrap_Paging != null){
				alert("찜한 상품이 없습니다.");
				$('.delete_btn_div').remove();
				
				$('<tr/>').append($('<td/>',{
					align:'center',
					text:'찜목록이 없습니다.',
					style:'height:250px;'
				})).appendTo($('#scrapListTable'));
				
			}
			
			else {
			
			$.each(data.list,function(index,items){ 
				var text;
				if(items.sale_state=='0'){
					text='판매중';
				}else if(items.sale_state=='1'){
					text='판매완료';
				}
				//items=="[]"  data=="list[]"
			$('<ul/>',{
					style: 'margin: 0 5px; width: 30%; height: 32%;',
					id:'scrap_ul'
				})
				
				.append($('<div/>',{
					style:' color: #fff; position: relative; z-index: 6; left: 5px;  top: 10px; cursor:pointer; ',
					id:'check_div'
				}).append($('<input>',{
					type:'checkbox',
					name:'check',
					class:'check',
					value:items.scrap_seq,
					style:'zoom:1.5; color: #fff; position: absolute;  cursor:pointer;  top: -5px; right: 12px; display:none; ',
					/*value:items.follow_seq*/
				}))
				
				).append($('<li/>',{
					style: 'width: 100%; height: 100%;'

				})
					.append($('<img/>',{
					src:'/jaju/storage/'+items.sale_image1,
					style:'width:100%; height:105%; transform:scale(1.0);	transition: transform .5s; cursor:pointer; ',
					class:"img_"+items.scrap_seq,
				})))
				.append($('<li/>',{
					text:items.sale_subject,
					style:'font-weight:bold; font-size:16px; margin-top: 10px; margin-bottom: -5px; cursor:pointer; ',

				})).append($('<li/>',{
					text:text,
					style:'margin-top: 3px; margin-bottom: -5px; cursor:pointer; ',

				})).append($('<li/>',{
					text:items.scrap_date,
					style:'margin-top: 3px;'
				})).appendTo($('.add_scrap_pic'));
				
/*				$('li > img').hover(function(){
				    $(this).css('transform','scale(1.1)');
				    $(this).css('transition','transform .5s');
				});*/
				
				//img값에 hover 속성 주기.
				$('li > img').on({
				    mouseover: function(){
				        $(this).css('transform','scale(1.05)');
					    $(this).css('transition','.5s');
				    },
				     mouseleave: function(){
				        $(this).css('transform','scale(1.0)');
				    },
				    click: function(){
				        $(this).off('mouseleave');
				    }
				});
				
				$('.img_'+items.seq).click(function(){//seq, pg 
					location.href='';//스크랩 이미지 클릭 시 해당 게시글로 이동하게 하기
				});
				
				//전체 선택 또는 해제
/*				$('#all').click(function(){

					//alter($('#all').attr('checked'));//checked 속성이 없어서 undefind으로 나온다.
					//alert($('#all').prop('checked'));//attr이 아니라 값으로 가져와야한다. 

					if($('#all').prop('checked')){
						
						$('.check').prop('checked',true);
					}else {
						$('.check').prop('checked',false);
					}
				});*/
			
			
				
			});//each
			
			$('#ScrapPagingDiv').html(data.myScrap_Paging.pagingHTML);

			
			}//else
			
		},error:function(err){
			console.log("error발생"+err);
		}
	});
});

//편집 클릭 시 
$(document).on('click', '#check_items', function(){
	$('.check').toggle();//toggle
	$('#delete_btn').toggle();
});

$('#delete_btn').click(function(){
	var count = $('input[name=check]:checked').length;
	//alert($('input[name=check]:checked').prop('tagName'));==input
	//alert($('input[name=check]:checked').parent().next().children().prop('tagName'));==img
	//alert($('input[name=check]:checked').parent().next().children().prop());
	//alert($('input[name=check]:checked').val());
	
/*		$('#delete_btn').click(function(){		});
*/
		var count = $('input[name=check]:checked').length;
		//유효성검사
		if(count==0){
			alert("삭제할 항목을 선택 하세요");
		}
		else {
			if(confirm("정말로 삭제 하시겠습니까?")){
				$('#myScrapForm').submit();
				alert('삭제 완료 되었습니다.');
				//location.reload();
			}else{
				alert('삭제에 실패했습니다. 다시 선택해주세요.');
			}
		}
		

	
});
