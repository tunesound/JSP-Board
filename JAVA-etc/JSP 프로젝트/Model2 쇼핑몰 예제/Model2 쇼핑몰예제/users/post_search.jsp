<!--
 TITLE :		우편번호 찾기 프레임 페이지  
-->
<%@page contentType="text/html; charset=euc-kr" %>
<html>
<head>
<title>우편번호 찾기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
</head>
<frameset rows="120,1*" cols="*" border="0" framespacing="0" frameborder="NO"> 
	<frame src="post_up.jsp?nm=<%=request.getParameter("nm")%>" name="up" scrolling="NO" noresize frameborder="NO" marginwidth="0" marginheight="0">
  	<frame src="post_down.jsp" name="down" noresize frameborder="NO" marginwidth="0" marginheight="0">
</frameset>
<noframes>
<body bgcolor="#FFFFFF">
</body></noframes>
</html>
