<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JSP 게시판 웹 사이트</title>
</head>

<body>
	<%
		session.invalidate();
	%>
	<script>
		location.href = "main.jsp";
	</script>
</body>
</html>
