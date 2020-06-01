<!--
 TITLE :		첫 페이지로 이동 
 DESCRIPTION :	 
-->
<%
	response.sendRedirect(request.getContextPath() + "/MainServlet?cmd=HOME");
%>
