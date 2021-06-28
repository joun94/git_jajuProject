<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/myRecode.css" />
<style type="text/css">
*, *:after, *:before {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}
/*
.list_order {
	padding-top: 10px;
	border-top: 2px solid #333;
}
*/
.list_order .date {
	/*padding-top: 20px;*/
	font-size: 16px;
	line-height: 24px;
	font-weight: 700;
	color: #666;
}

.list_order .order_goods {
	position: relative;
	margin-top: 10px;
	padding: 0 20px;
	border: 1px solid #dddfe1;
}

.list_order .deal_name {
	padding: 20px 0 13px;
	border-bottom: 1px solid #dddfe1;
}

.list_order .deal_name a {
	display: block;
	overflow: hidden;
	background: url()
		no-repeat 100% 1px;
	font-size: 16px;
	line-height: 24px;
	font-weight: 700;
	color: #000;
	cursor: pointer;
}

.list_order .order_info {
	overflow: hidden;
	padding: 14px 0 0px;
}

.list_order .thumb {
	float: left;
	width: 130px;
    height: 130px;
    margin-bottom: 12px;
}

.list_order .thumb img {
	width: 100%;
	height: 100%;
}

.list_order .desc {
	overflow: hidden;
	padding-top: 1px;
}

.list_order .order_info dl:first-child {
	padding-top: 3px;
}

.order_info dl {
	overflow: hidden;
	padding-top: 6px;
}

.list_order .order_info dt {
	float: left;
	padding-right: 15px;
	font-size: 12px;
	color: #000;
	line-height: 20px;
	text-align: left;
    width: 80px;
}

.list_order .order_info dd {
	float: left;
	font-size: 14px;
	line-height: 20px;
	font-weight: 700;
	color: #000;
}

.list_order .order_status {
	display: table;
	position: absolute;
	right: 20px;
	bottom: 0;
	height: 114px;
	vertical-align: middle;
}

.list_order .inner_status {
	display: table-cell;
	vertical-align: middle;
}

.list_order .order_info .end {
	color: #514859;
}
.order_info .status {
	color: #5f0080;
}
ol, ul {
	list-style-type: none;
}

#currentPaging {
	display: inline-block;
    width: 34px;
    height: 34px;
    border: 1px solid #ddd;
    border-left: 1;
    vertical-align: top;
    margin-top: 15px;
    margin-bottom: 15px;
    font-weight: 700;
    line-height: 34px;
}
#currentPaging:hover{
	border: 1px solid #47597e;
}
#paging {
	display: inline-block;
    width: 34px;
    height: 34px;
    border: 1px solid #ddd;
    border-left: 1;
    vertical-align: top;
    margin-top: 15px;
    margin-bottom: 15px;
    font-weight: 700;
    line-height: 34px;
}
#paging:hover,#prev_paging:hover,#next_paging:hover{
	border: 1px solid #47597e;
}
#prev_paging{
    background-image: url(https://res.kurly.com/pc/etc/old/images/common/icon-pagination-prev.png);
	width: 34px;
    height: 34px;
    margin-top: 15px;
    margin-bottom: 15px;
    vertical-align: top;
    display: inline-block;
    background-repeat: no-repeat;
    background-position: 50% 50%;
    text-indent: -9999px;
    border: 1px solid #ddd;
}
#next_paging{
    background-image: url(https://res.kurly.com/pc/etc/old/images/common/icon-pagination-next.png);
    width: 34px;
    height: 34px;
    margin-top: 15px;
    margin-bottom: 15px;
    vertical-align: top;
    display: inline-block;
    background-repeat: no-repeat;
    background-position: 50% 50%;
    text-indent: -9999px;
    border: 1px solid #ddd;
}
.section_review{
	border-bottom:1px solid #ededed;

}
</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
    <input type="hidden" name="pg" id="pg" value="${pg}">
    <input type="hidden" name="followId" id="followId" value="${followId}">

	<jsp:include page="/mypage/mypageMain.jsp" />

	<div class="section_review">
	
			<div class="head_aticle">
				<h2 class="tit">&emsp;팔로우 프로필정보</h2>
			</div>
	
			<div class="board-header-container">
			<ul class="list-description">
				<li>${followId}님의 프로필입니다.</li>
			</ul>
		</div>

		<div class="tabTable" align="left" id="profileTable">
			<!-- 동적 태그 들어오는 곳  -->
			<table border="1" id="myDealRecordTable" cellpadding="5" width="750px" bordercolor="" align="center" frame="hsides" rules="rows">
				<tr class="title">

				</tr>
			</table>
		</div>
	</div>


	<div class="section_review">
	
			<div class="head_aticle">
				<h2 class="tit">&emsp;팔로우 판매목록</h2>
			</div>
	
			<div class="board-header-container">
				<ul class="list-description">
					<li>${followId}님의 판매목록입니다.</li>
				</ul>
			</div>
	
			<div class="tabTable" align="left" id="tabTable">
				<!-- 동적 태그 들어오는 곳  -->
				<table border="1" id="myDealRecordTableBottom" cellpadding="5" width="750px" bordercolor="#ededed" align="center" frame="hsides" rules="rows">
					<tr class="title">
	
					</tr>
				</table>
			</div>
	
			<div style="margin: 25px; width: 700px; text-align: center;"
				id="myDealRecodePagingDiv"></div>
		</div>


		
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
<script src="/jaju/js/myFollowSaleList.js"></script>
<script>
function myRecodePaging(pg){
	location.href="myFollowSaleList?followId="+$('#followId').val()+"&pg="+pg;
}
</script>
</body>
</html>