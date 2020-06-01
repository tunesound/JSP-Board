<!--
 TITLE :		로그인 페이지 
-->
<%@page import="mall.user.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="msg" scope="request" class="java.lang.String" />

<html>
<head>
<title>::쇼핑몰::</title>
<LINK href="<%=request.getContextPath()%>/_images/myweb.css" rel="stylesheet">
</head>
<body>

<%@include file="/_frames/main.jsp" %>

<form name="login_form" action="<%=request.getContextPath()%>/MainServlet" method="post">
<table width="100%" height="90%" border=0>
<%
if (!msg.equals("")) {
%>
	<tr>
		<td><font color="red"><%=msg %> </font></td>
	</tr>
<%
}
%>
	<tr>
		<td valign="middle" align="center">
			<input type="hidden" name="cmd" value="LOGIN">
			<table cellspacing=0 cellpadding=0 border=0>
				<tr>
					<td>
						<table border=0 >
							<tr>
								<td bgcolor="#990000" width=120 align=center>
									<font color="#ffffff"><B>사용자 Id</B></font>
								</td>
								<td>
									<input type="text" size="20" name="username" value="" onkeyup="if(event.keyCode==13)document.login_form.password.focus();">
								</td>
							</tr>
							<tr>
								<td bgcolor="#990000" width=120 align=center>
									<font color="#ffffff"><B>비밀번호</B></font>
								</td>
								<td>
									<input type="password" size="20" name="password" onkeyup="if(event.keyCode==13)document.login_form.submit();">
								</td>
							</tr>
							<tr>
								<td><font size="+3">&nbsp;</font></td>
								<td valign="bottom">
									<a href="javascript:document.login_form.submit();"><img name="login" src="<%=request.getContextPath()%>/_images/buttons/login.gif" width="58" height="19" alt="" border="0"></a>
								</td>
							</tr>
						</table>
					</td>					
				</tr>
			</table>		
		</td>
	</tr>
</table>
</form>

<script language="javascript">
	document.login_form.username.focus();	
</script>

</body>
</html>
