
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.CommentBean"%>

<!DOCTYPE html>
<html class="no-js" lang="">

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>後台管理</title>
<%-- style sheet (以下三個style sheet請記得link進來) --%>
<link rel="stylesheet"	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/style.css">

</head>
<body>
	<%-- side Bar (side bar請記得include進來) --%>
	<%@include file="jspf/sidebar.jspf"%>

	<%-- main--%>
	<main id="right-panel" class="right-panel">

		<%-- 網站內容請放main裡面--%>
		
		
		
		
		
	</main>
	<%-- scripts --%>
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/plugins.js"></script>
	<script src="<%=request.getContextPath()%>/js/main.js"></script>
</body>

</html>