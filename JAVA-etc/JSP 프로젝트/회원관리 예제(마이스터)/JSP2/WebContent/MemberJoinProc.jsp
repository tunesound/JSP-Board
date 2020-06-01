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

	<h2>회원 정보 보기</h2>
	
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	
	<!-- request로 넘어는 데이터를 자바 빈즈랑 맵핑 시켜주는 useBean -->
	<jsp:useBean id="mbean" class="bean.MemberBean"> <!-- 객체생성 MemberBean = new MemberBean() 과 같다 -->
	<!-- JSP 내용을 자바빈 클래스(MemberBean을 의미)에 데이터를 맵핑(넣어줌) -->
		<jsp:setProperty name="mbean" property="*" /> <!-- 자동으로 모두 맵핑시켜주세요 -->
	</jsp:useBean>
	
	<h2>당신의 아이디는 <jsp:getProperty property="id" name="mbean" /></h2>
	<h2>당신의 패스워드는 <jsp:getProperty property="pass1" name="mbean"/></h2>
	<h2>당신의 이메일은 <jsp:getProperty property="email" name="mbean"/></h2>


</body>
</html>
