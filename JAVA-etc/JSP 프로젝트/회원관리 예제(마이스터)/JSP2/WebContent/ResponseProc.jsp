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

	<h2>이 페이지는 로그인 정보가 넘어오는 페이지 입니다.</h2>
	
	<%
		request.setCharacterEncoding("UTF-8");  //post 방식의 한글처리
	
		String id = request.getParameter("id");  //request의 객체에 담겨진 사용자 정보중 id만 추출
		
		//response.sendRedirect("ResponseRedirect.jsp");  //흐름제어 (request에 담긴 정보가 넘어가지못함)
	%>
	<!-- 실무에서는 session을 사용한다 -->
	<jsp:forward page="ResponseRedirect.jsp" >  <!-- forward로 흐름제어 (request에 담긴정보가 responsesendRedirect.jsp로 넘어간다) -->
		<jsp:param value="mmmm" name="id" /> <!-- request객체의 데이터를 유지시키고 싶거나, 값을 임의적으로 변경하거나, 할때 사용 -->
		<jsp:param value="1234" name="phone" />
	</jsp:forward>
	<!-- 흐름제어 -->
	
	<h3>아이디 = <%=id %></h3>
	
</body>
</html>
