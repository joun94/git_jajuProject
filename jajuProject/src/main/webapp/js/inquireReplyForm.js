//답글쓰기
$('#inquireReplyBtn').click(function(){
	$.ajax({
		type : 'post',
		url : '/jaju/serviceCenter/inquireReply',
		data : {
			'pseq' : $('input[name=pseq]').val(),//원글 번호
			'inquiry_subject':$('#inquiry_subject').val(),
			'inquiryType' : $('#inquiryType').val(),
			'inquiry_contents':$('#inquiry_contents').val()
		},
		success : function(){
			alert("답글쓰기가 완료되었습니다");
			location.href = '/jaju/serviceCenter/inquireList?pg='+$('input[name=pg]').val();
		},
		error : function(){
			console.log(err);
		}
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