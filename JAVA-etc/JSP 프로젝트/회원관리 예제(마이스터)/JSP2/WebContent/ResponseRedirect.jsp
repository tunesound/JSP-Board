<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
</head>

<body>

	<h2>ResponseRedirect.jsp 페이지 입니다.</h2>
	
	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		
		String phone = request.getParameter("phone");
		
	%>

	<h3>아이디 = <%=id %></h3>

</body>
</html>
