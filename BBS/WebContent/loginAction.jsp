<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- UserDAO클래스 사용 -->
<%@ page import="user.UserDAO" %>
<!-- 자바스크립트 문장을 작성하기위함 -->
<%@ page import="java.io.PrintWriter" %>
<!-- 건너오는 모든데이터를 UTF-8으로 받을수 있도록함 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!-- 자바빈즈를 사용 -->
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>로그인 처리</title>
</head>

<body>
	<%
 		String userID = null;
		if (session.getAttribute("userID") !=null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID != null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인 되어있습니다.')");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		UserDAO userDAO = new UserDAO();
		//로그인페이지에서 UserID와 UserPassword가 각각 입력이된 값으로 넘어와서
		//로그인함수에 넣어 실행하도록 한다.
		//결과의 값들이 result 객체에 담긴다.
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		if (result == 1) {
			session.setAttribute("userID", user.getUserID());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'main.jsp'");  //로그인성공시 이동페이지
			script.println("</script>");
		}
		else if (result == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");  //이전페이지로 사용자를 돌려보낸다.
			script.println("</script>");
		}
		else if (result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
</body>
</html>
