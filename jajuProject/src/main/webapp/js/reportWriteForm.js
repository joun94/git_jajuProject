//글쓰기에 신고아이디, 신고글 뿌리기
$(function(){
	$('#sale_id').val($('#sale_id2').val());
	$.ajax({
		type: 'post',
		url: '/jaju/saleboard/getSaleboardViewReview',
		data: {'sale_seq' : $('#sale_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
		$('#sale_subject').val(data.saleboardDTO.sale_subject);
		
		},
		error: function(err){
			console.log(err);
		}
	});
	
	$.ajax({
		type: 'post',
		url: '/jaju/serviceCenter/getMemberName',
		data: {'report_id' : $('#report_id').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('#report_name').val(data.memberDTO.member_name);
			
			
		},
		error: function(err){
			console.log(err);
		}
	});
});




//글쓰기
$('#bhs_button').click(function(){
	$('#itemcdDiv').empty();
	$('#subjectDiv').empty();
	$('#mobileDiv').empty();
	$('#contentsDiv').empty();
	
	if($('#reportType').val() == ''){
		$('#itemcdDiv').text('신고 유형을 선택해주세요');
		$('#itemcdDiv').css('color', 'red');
        $('#itemcdDiv').css('font-size', '10pt');
        $('#itemcdDiv').css('font-weight', 'bold');
	}else if($('#report_subject').val() == ''){
        $('#subjectDiv').text('제목을 입력하세요');
        $('#subjectDiv').css('color', 'red');
        $('#subjectDiv').css('font-size', '10pt');
        $('#subjectDiv').css('font-weight', 'bold');
	}else if($('#report_tal').val() == ''){
		$('#mobileDiv').text('전화번호를 입력하세요');
		$('#mobileDiv').css('color', 'red');
		$('#mobileDiv').css('font-size', '10pt');
		$('#mobileDiv').css('font-weight', 'bold');
	}else if($('#report_content').val() == ''){
		$('#contentsDiv').text('내용을 입력하세요');
		$('#contentsDiv').css('color', 'red');
		$('#contentsDiv').css('font-size', '10pt');
		$('#contentsDiv').css('font-weight', 'bold');
	}else{
		
		var formData = new FormData($('#reportWriteForm')[0]);
		$.ajax({
			type: 'post',
		 	url: '/jaju/serviceCenter/reportWrite',
		 	enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
		 	data: formData,
		 	success: function(){
		 		alert('신고가 접수되었습니다');
		 		location.href='/jaju/serviceCenter/reportList?pg=1'
		 	},
		 	error: function(err){
		 		console.log(err);
		 	}
		});
	}
});


//휴대폰 번호 유효성 검사
$(function(){
	$(".read_only").on('keydown', function(e){
	
	//숫자만 입력받기
	var pattern = $(this).val().replace(/-/gi,'');
	var num = e.keyCode;
				
	if(pattern.length >= 11 && ((num >= 48 && num <=126) || (num >= 12592 && num <= 12687 || num==32 || num==229 || (num>=45032 && num<=55203)) )){
  	    e.preventDefault();
	}
	
	}).on('blur', function(){ //포커스 아웃일때 실행
        if($(this).val() == '') return;

        //기존 번호에서 -를 삭제
        var pattern = $(this).val().replace(/-/gi,'');
      
        //입력값이 있을때만 실행
        if(pattern != null && pattern != ''){
        	
            //총 핸드폰 자리수는 11글자이거나, 10자여야 한다.
            if(pattern.length==11 || pattern.length==10){   
                
            	//유효성 체크
                var phone_num = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
                if(phone_num.test(pattern)){
					//유효성 체크에 성공하면 -을 넣고 값을 바꿔준다.
					pattern = pattern.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/, "$1-$2-$3");
					$(this).val(pattern);
                }else{
                    alert("유효하지 않은 전화번호 입니다");
                    $(this).val("");
                    $(this).focus();
                }
            }else {
                alert("유효하지 않은 전화번호 입니다");
                $(this).val("");
                $(this).focus();
            }//else
            	
		}//if
	});  
});

//파일 업로드
function add(){
    var table = document.getElementById('table');
    var reviewFileNum = "3";
    
    if (table.rows.length>=parseInt(reviewFileNum)){
        alert("업로드는 최대 "+reviewFileNum+"개만 지원합니다");
        return;
    }
    
    var tr_num = table.rows.length;
    oTr		= table.insertRow( table.rows.length );
    oTr.id	= "tr_"+(tr_num);
    oTd1		= oTr.insertCell(0);
    oTd1.style.textAlign = "center";
    oTd2		= oTr.insertCell(1);
    tmpHTML = "<input type=file name='img[]' style='width:50%' class=line> <a href=\"javascript:del('"+"tr_"+(tr_num)+"')\"><img src='/jaju/jajuImage/upadd2.jpg' align=absmiddle></a>";
    oTd2.innerHTML = tmpHTML;
    calcul();
}

function del(index, ncode){
    var table = document.getElementById('table');
    for (i=0;i<table.rows.length;i++) if (index==table.rows[i].id) table.deleteRow(i);
    calcul();
}

function calcul(){
    var table = document.getElementById('table');
    for (var i=0;i<table.rows.length;i++){
        table.rows[i].cells[0].innerHTML = i+1;
    }
}