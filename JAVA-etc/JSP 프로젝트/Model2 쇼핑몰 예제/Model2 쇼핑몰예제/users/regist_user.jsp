<!--
 TITLE :		사용자 등록 페이지
-->
<%@page import = "mall.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="userbean" scope="request" class="mall.user.UserBean" />
<jsp:useBean id="msg" scope="request" class="java.lang.String" />

<html>
<head>
<title>::쇼핑몰::</title>
<LINK href="<%=request.getContextPath()%>/_images/myweb.css" rel="stylesheet">
</head>

<script language="javascript">
	var save_off = new Image();
	save_off.src = "<%=request.getContextPath()%>/_images/buttons/save.gif";
	var save_on = new Image();
	save_on.src = "<%=request.getContextPath()%>/_images/buttons/save_on.gif";
		
	var back_off = new Image();
	back_off.src = "<%=request.getContextPath()%>/_images/buttons/back.gif";
	var back_on = new Image();
	back_on.src = "<%=request.getContextPath()%>/_images/buttons/back_on.gif";
	
	function winopen_zip() {	   
		zip = "<%=request.getContextPath()%>/users/post_search.jsp?nm=company" ; 
		window.open(zip, "zip", "width=470,height=350,status=auto,menubar=no,toolbar=no,scrollbars=yes" ); 
	}
	
</script>

<body>
<%@include file="/_frames/main.jsp" %>

<form action="<%=request.getContextPath()%>/MainServlet" method="post" name="user">
<input type="hidden" name="userId" value="<%= userbean.getUserId() %>">  
<input type="hidden" size="30" name="status" value="<%= userbean.getStatus()%>">
<input type="hidden" name="isAdmin" value="<%= userbean.getIsAdmin() %>"> 
<input type="hidden" name="cmd" value="UPDATE">

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD">
	<tr>
		<td>
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>회원등록</b>
		</td>
		<td align=right>
			<a href="javascript:document.user.submit();"><img name="save" src="<%=request.getContextPath()%>/_images/buttons/save.gif" width="58" height="19" alt="" border="0" hspace=15 vspace=5></a>
		</td>
	</tr>
	<tr bgcolor="#000000"><td colspan="2"><img src="<%=request.getContextPath()%>/_images/spacer.GIF" width="1" height="3" alt="" border="0"></td></tr>
</table>
<%
	//회원등록결과 출력
	if (!msg.equals("")) {
		out.print("<br><font color='red'>");
		out.print(msg);
		out.print("</font><br>");
	}
%>
<table>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td align="right">성명: </td>
		<td><input type="text" size="20" name="name" value="<%= userbean.getName()%>"> </td>
	</tr>
	<tr>
		<td align="right">회사명: </td>
		<td><input type="text" size="20" name="company" value="<%= userbean.getCompany()%>"> </td>
	</tr>
	<tr>
		<td align="right">부서: </td>
		<td><input type="text" size="20" name="dept" value="<%= userbean.getDept()%>"> </td>
	</tr>
	<tr>
		<td align="right">직책: </td>
		<td><input type="text" size="20" name="title" value="<%= userbean.getTitle()%>"> </td>
	</tr>	
	<tr>
		<td align="right">사용자 ID: </td>
		<td><input type="text" size="20" name="id" value="<%= userbean.getId()%>"> </td>
	</tr>	
	<tr>
		<td align="right">비밀번호: </td>
		<td><input type="text" size="20" name="password" value="<%= userbean.getPassword()%>"> </td>
	</tr>	
	<tr>
		<td align="right">전자우편: </td>
		<td><input type="text" size="20" name="email" value="<%= userbean.getEmail()%>"> </td>
	</tr>	
	<tr>
		<td align="right">주민등록번호: </td>
		<td><input type="text" size="20" name="ssn" value="<%= userbean.getSsn()%>"> </td>
	</tr>			

	<tr>
		<td align="right">우편번호: </td>
		<td><input type="text" size="10" name="zipcode" value="<%= userbean.getZipcode()%>"> <a href="javascript:winopen_zip()">찾기</a></td>
	</tr>	
	<tr>
		<td align="right">주소1: </td>
		<td><input type="text" size="50" name="address1" value="<%= userbean.getAddress1()%>"> </td>
	</tr>	
	<tr>
		<td align="right">주소2: </td>
		<td><input type="text" size="50" name="address2" value="<%= userbean.getAddress2()%>"> </td>
	</tr>	
	<tr>
		<td align="right">회사전화: </td>
		<td><input type="text" size="20" name="companyPhone" value="<%= userbean.getCompanyPhone()%>"> </td>
	</tr>	
	<tr>
		<td align="right">집전화: </td>
		<td><input type="text" size="20" name="homePhone" value="<%= userbean.getHomePhone()%>"> </td>
	</tr>		
</table>
</form>

</body>
</html>