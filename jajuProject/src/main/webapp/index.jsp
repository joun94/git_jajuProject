<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/test_index.css">
<style type="text/css">

</style>
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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</html>