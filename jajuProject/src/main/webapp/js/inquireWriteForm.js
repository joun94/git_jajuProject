//1:1문의 글쓰기
$('#inquireWritBtn').click(function(){
	$('#inquiryTypeDiv').empty();
	$('#inquiry_subjectDiv').empty();
	$('#inquiry_telephoneDiv').empty();
	$('#inquiry_contentsDiv').empty();
	

	if($('#inquiryType').val() == ''){
		$('#inquiryTypeDiv').text('문의 유형을 선택해 주세요');
		$('#inquiryTypeDiv').css('color','red');
		$('#inquiryTypeDiv').css('font-size','10pt');
		$('#inquiryTypeDiv').css('font-weight','bold');
	}
	else if($('#inquiry_subject').val() == ''){
		$('#inquiry_subjectDiv').text('제목을 입력해주세요');
		$('#inquiry_subjectDiv').css('color','red');
		$('#inquiry_subjectDiv').css('font-size','10pt');
		$('#inquiry_subjectDiv').css('font-weight','bold');
	}
	else if($('#inquiry_telephone').val() == ''){
		$('#inquiry_telephoneDiv').text('핸드폰 번호를 입력해주세요');
		$('#inquiry_telephoneDiv').css('color','red');
		$('#inquiry_telephoneDiv').css('font-size','10pt');
		$('#inquiry_telephoneDiv').css('font-weight','bold');
	}
	else if($('#inquiry_contents').val() == ''){
		$('#inquiry_contentsDiv').text('내용을 입력해주세요');
		$('#inquiry_contentsDiv').css('color','red');
		$('#inquiry_contentsDiv').css('font-size','10pt');
		$('#inquiry_contentsDiv').css('font-weight','bold');
	}
	else{
		var formData = new FormData($('#fm')[0])
		$.ajax({
			type : 'post',
			url : '/jaju/serviceCenter/inquireWrite',
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			data : formData,
			success : function(){
				alert("문의가 접수되었습니다");
				location.href = '/jaju/serviceCenter/inquireList?pg=1';
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});

//전화번호 숫자받기
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


//이미지 업로드
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