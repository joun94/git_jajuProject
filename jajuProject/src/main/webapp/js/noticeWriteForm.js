//글쓰기
$('#noticeWriteBtn').click(function(){
	$('#notice_subjectDiv').empty();
	$('#notice_contentDiv').empty();
	
	if($('#notice_subject').val() == ''){
        $('#notice_subjectDiv').text('제목을 입력하세요');
        $('#notice_subjectDiv').css('color', 'red');
        $('#notice_subjectDiv').css('font-size', '10pt');
        $('#notice_subjectDiv').css('font-weight', 'bold');
	}else if($('#notice_content').val() == ''){
		$('#notice_contentDiv').text('내용을 입력하세요');
		$('#notice_contentDiv').css('color', 'red');
		$('#notice_contentDiv').css('font-size', '10pt');
		$('#notice_contentDiv').css('font-weight', 'bold');
	}else{
		
		var formData = new FormData($('#noticeWriteForm')[0]);
		$.ajax({
			type: 'post',
		 	url: '/jaju/serviceCenter/noticeWrite',
		 	enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
		 	data: formData,
		 	success: function(){
		 			alert('글 작성이 완료되었습니다');
		 			location.href='/jaju/serviceCenter/noticeList?pg=1'
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
    var reviewFileNum = "10";
    
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