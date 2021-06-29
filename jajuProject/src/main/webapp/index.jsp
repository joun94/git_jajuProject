<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 제이쿼리 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<!-- Slick 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
<link rel="stylesheet" href="/jaju/css/test_index.css">
</head>
</head>
<body> <!-- Add your content of header -->


	<div align="center" id="indexHeader">
		<jsp:include page="/main/header.jsp" />
	</div>
	
	<div>
		<c:if test="${empty display }">
			<jsp:include page="/main/body.jsp"></jsp:include>
		</c:if>
		
		<c:if test="${not empty display }">
			<jsp:include page="${display }"></jsp:include>
		</c:if>
	</div>
	
	<div id="footer">
		<jsp:include page="/main/footer.jsp" />
	</div>		

</body>

</html>