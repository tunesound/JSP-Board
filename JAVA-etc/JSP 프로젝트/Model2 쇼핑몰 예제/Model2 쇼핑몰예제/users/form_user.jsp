<!--
 TITLE :		����ڵ�� ������ 
-->
<%@page import = "mall.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="userbean" scope="request" class="mall.user.UserBean" />
<jsp:useBean id="msg" scope="request" class="java.lang.String" />

<html>
<head>
<title>::���θ�::</title>
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

<form action="<%=request.getContextPath()%>/UserServlet" method="post" name="user">
<input type="hidden" name="userId" value="<%= userbean.getUserId() %>">  
<input type="hidden" name="isAdmin" value="<%= userbean.getIsAdmin() %>"> 
<input type="hidden" name="cmd" value="UPDATE">
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD">
	<tr>
		<td><b>�ű� ����� ���</b></td>
		<td width=450 align=right>
 			<a href="<%=request.getContextPath()%>/UserServlet?cmd=USER_LIST"><img name="back" src="<%=request.getContextPath()%>/_images/buttons/back.gif" width="41" height="13" alt="����� ����Ʈ ����" border="0" hspace=15 vspace=5 align=middle></a>
		</td>
		
		<td align=right>
			<a href="javascript:document.user.submit();"><img name="save" src="<%=request.getContextPath()%>/_images/buttons/save.gif" width="58" height="19" alt="" border="0" hspace=15 vspace=5></a>
		</td>
	</tr>
	<tr bgcolor="#000000"><td colspan="3"><img src="<%=request.getContextPath()%>/_images/spacer.GIF" width="1" height="3" alt="" border="0"></td></tr>
</table>
<%
	//����ڵ�ϰ�� ���
	if (!msg.equals("")) {
		out.print("<br><font color='red'>");
		out.print(msg);
		out.print("</font><br>");
	}
%>
<table>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td align="right">����: </td>
		<td><input type="text" size="20" name="name" value="<%= userbean.getName()%>"> </td>
	</tr>
	<tr>
		<td align="right">ȸ���: </td>
		<td><input type="text" size="20" name="company" value="<%= userbean.getCompany()%>"> </td>
	</tr>
	<tr>
		<td align="right">�μ�: </td>
		<td><input type="text" size="20" name="dept" value="<%= userbean.getDept()%>"> </td>
	</tr>
	<tr>
		<td align="right">��å: </td>
		<td><input type="text" size="20" name="title" value="<%= userbean.getTitle()%>"> </td>
	</tr>	
	<tr>
		<td align="right">����� ID: </td>
		<td><input type="text" size="20" name="id" value="<%= userbean.getId()%>"> </td>
	</tr>	
	<tr>
		<td align="right">��й�ȣ: </td>
		<td><input type="text" size="20" name="password" value="<%= userbean.getPassword()%>"> </td>
	</tr>	
	<tr>
		<td align="right">���ڿ���: </td>
		<td><input type="text" size="20" name="email" value="<%= userbean.getEmail()%>"> </td>
	</tr>	
	<tr>
		<td align="right">�ֹε�Ϲ�ȣ: </td>
		<td><input type="text" size="20" name="ssn" value="<%= userbean.getSsn()%>"> </td>
	</tr>	
	<tr>
		<td align="right">����: </td>
		<td><select name="status" > 
			<option value='1' <%if (userbean.getStatus()==1) out.print("selected"); %>>��밡��</option>
			<option value='2' <%if (userbean.getStatus()==2) out.print("selected"); %>>�������</option>				
			</select>			
	    </td>
	</tr>		
	<tr>
		<td align="right">�����ȣ: </td>
		<td><input type="text" size="10" name="zipcode" value="<%= userbean.getZipcode()%>"> <a href="javascript:winopen_zip()">ã��</a></td>
	</tr>	
	<tr>
		<td align="right">�ּ�1: </td>
		<td><input type="text" size="50" name="address1" value="<%= userbean.getAddress1()%>"> </td>
	</tr>	
	<tr>
		<td align="right">�ּ�2: </td>
		<td><input type="text" size="50" name="address2" value="<%= userbean.getAddress2()%>"> </td>
	</tr>	
	<tr>
		<td align="right">ȸ����ȭ: </td>
		<td><input type="text" size="20" name="companyPhone" value="<%= userbean.getCompanyPhone()%>"> </td>
	</tr>	
	<tr>
		<td align="right">����ȭ: </td>
		<td><input type="text" size="20" name="homePhone" value="<%= userbean.getHomePhone()%>"> </td>
	</tr>		
</table>
</form>

</body>
</html>
