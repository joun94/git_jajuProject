//글쓰기
$('#questionWriteBtn').click(function(){
	$('#questionTypeDiv').empty();
	$('#question_subjectDiv').empty();
	$('#question_contentDiv').empty();
	
	if($('#questionType').val() == ''){
		$('#questionTypeDiv').text('질문 유형을 선택해주세요');
		$('#questionTypeDiv').css('color', 'red');
        $('#questionTypeDiv').css('font-size', '10pt');
        $('#questionTypeDiv').css('font-weight', 'bold');
	}else if($('#question_subject').val() == ''){
        $('#question_subjectDiv').text('제목을 입력하세요');
        $('#question_subjectDiv').css('color', 'red');
        $('#question_subjectDiv').css('font-size', '10pt');
        $('#question_subjectDiv').css('font-weight', 'bold');
	}else if($('#question_content').val() == ''){
		$('#question_contentDiv').text('내용을 입력하세요');
		$('#question_contentDiv').css('color', 'red');
		$('#question_contentDiv').css('font-size', '10pt');
		$('#question_contentDiv').css('font-weight', 'bold');
	}else{
		
		var formData = new FormData($('#questionWriteForm')[0]);
		$.ajax({
			type: 'post',
		 	url: '/jaju/serviceCenter/questionWrite',
		 	enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
		 	data: formData,
		 	success: function(){
		 			alert('글 작성이 완료되었습니다');
		 			location.href='/jaju/serviceCenter/questionList?pg=1'
		 	},
		 	error: function(err){
		 		console.log(err);
		 	}
		});
	}
	
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