<!--
 TITLE :		����� ����Ʈ ������
-->
<%@page import="mall.user.*, mall.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="lo" scope="request" class="mall.util.ListObject" />

<html>
<head>
<title>::���θ�::</title>
<LINK href="<%=request.getContextPath()%>/_images/myweb.css" rel="stylesheet">
</head>

<script language="javascript">	
	var search_off = new Image();
	search_off.src = "<%=request.getContextPath()%>/_images/buttons/search.gif";
	var search_on = new Image();
	search_on.src = "<%=request.getContextPath()%>/_images/buttons/search_on.gif";
	
	var advanced_off = new Image();
	advanced_off.src = "<%=request.getContextPath()%>/_images/buttons/advanced.gif";
	var advanced_on = new Image();
	advanced_on.src = "<%=request.getContextPath()%>/_images/buttons/advanced_on.gif";
</script>

<body>
<%@include file="/_frames/main.jsp" %>

<% 	
	//ListObject ù��° �ε����� UserBean �迭�� ����
	UserBean[] list = (UserBean[])lo.get(0);
%>

<form action="<%=request.getContextPath()%>/UserServlet" method="post" name="user">
<input type="hidden" name="cmd" value="USER_LIST">
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD">
	<tr>
		<td>&nbsp;<b>����� ����Ʈ</b></td>
		<td align=right valign="center">			
			����ڸ�:&nbsp;<INPUT type="text" maxLength=10 name="find" size=10>
			<a href="javascript:document.user.submit();"
				ONMOUSEOVER="rollIn('search');" 
				ONMOUSEOUT="rollOut('search');" ><img name="search" src="<%=request.getContextPath()%>/_images/buttons/search.gif" width="58" height="19" alt="Search" border="0"></a>&nbsp;&nbsp;
		</td>
	</tr>	
	<tr bgcolor="#000000"><td colspan="2"><img src="<%=request.getContextPath()%>/_images/spacer.gif" width="1" height="3" alt="" border="0"></td></tr>
</table>
</form>

<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan=2><b><%= list.length %> ���� ����ڰ� �˻��Ǿ����ϴ�.</b></td>
		<td colspan=2 align=right>			
		</td>
	</tr>
	<tr bgcolor="silver">
		<td>&nbsp;</td>
		<td><strong>����ڸ�</strong></td>
		<td><strong>ȸ���</strong></td>
		<td><strong>����� ID</strong></td>
		<td>&nbsp;</td>
	</tr>
<% 
	
	for(int i=0; i< list.length; i++) {
%>
	<tr bgcolor="<%= (i%2 == 0)?"#fcfcfc":"#e5e5e5" %>">
		<td width=25 align=center><%= i+1 %></td>
		<td width=310>
			<a href="<%=request.getContextPath()%>/UserServlet?cmd=MODIFY&key=<%=list[i].getUserId()%>">
				<%= list[i].getName() %></a>
		</td>
		<td width=150><%= list[i].getCompany() %></td>
		<td width=150><%= list[i].getId() %></td>		
		<td width=150><a href="<%=request.getContextPath()%>/UserServlet?cmd=REMOVE&key=<%=list[i].getUserId() %>">����</a></td>				
	</tr>
<% 		
	}	
%>
</table>

</body>
</html>