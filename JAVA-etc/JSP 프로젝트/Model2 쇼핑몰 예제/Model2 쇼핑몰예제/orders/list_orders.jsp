<!--
 TITLE :		주문 리스트 페이지 
-->
<%@page import="mall.order.*, mall.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="lo" scope="request" class="mall.util.ListObject" />

<html>
<head>
<title>::쇼핑몰::</title>
<LINK href="<%=request.getContextPath()%>/_images/myweb.css" rel="stylesheet">
</head>

<script language="javascript">	
	var search_off = new Image();
	search_off.src = "<%=request.getContextPath()%>/_images/buttons/search.gif";
	var search_on = new Image();
	search_on.src = "<%=request.getContextPath()%>/_images/buttons/search_on.gif";
</script>

<body>

<%@include file="/_frames/main.jsp" %>

<% 	
	//ListObject 첫번째 인덱스의 OrderBean 배열을 받음
	OrderBean[] list = (OrderBean[])lo.get(0);	
%>

<form action="<%=request.getContextPath()%>/OrderServlet" method="post" name="order">
<INPUT type="hidden" name="cmd" value="ORDER_LIST">
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD">
	<tr>
		<td>&nbsp;<b>주문 리스트</b></td>
		<td align=right valign="center">			
			주문상태:&nbsp;<select name="find" > 
				<option value='1'> 주문미처리</option>
				<option value='2'> 주문처리</option>
				<option value='3'> 전체</option>
			</select>			
			<a href="javascript:document.order.submit();"
				ONMOUSEOVER="rollIn('search');" 
				ONMOUSEOUT="rollOut('search');" ><img name="search" src="<%=request.getContextPath()%>/_images/buttons/search.gif" width="58" height="19" alt="Search" border="0"></a>&nbsp;&nbsp;
		</td>
	</tr>	
	<tr bgcolor="#000000"><td colspan="2"><img src="<%=request.getContextPath()%>/_images/spacer.gif" width="1" height="3" alt="" border="0"></td></tr>
</table>
</form>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan=2><b><%= list.length %> 개의 주문이 검색되었습니다</b></td>
		<td colspan=2 align=right>
			</td>
	</tr>
	<tr bgcolor="silver">
		<td>&nbsp;</td>
		<td><strong>주문번호</strong></td>
		<td><strong>주문자명</strong></td>
		<td><strong>가격</strong></td>
		<td><strong>주문신청일</strong></td>
		<td><strong>상태</strong></td>
		<td>&nbsp;</td>
	</tr>
<% 
	
	for(int i=0; i<list.length; i++) {
%>
	<tr bgcolor="<%= (i%2 == 0)?"#fcfcfc":"#e5e5e5" %>">
		<td><%= i+1 %>. </td>
		<td><a href="<%=request.getContextPath()%>/OrderServlet?cmd=ORDER_DETAIL&key=<%= list[i].getOrderId() %>"><%= list[i].getOrderId() %></a></td>
		<td><%=  list[i].getUserName() %></td>
		<td><%= Money.format(list[i].getOrderTotal()) %></td>
		<td><%= list[i].getOrderDate() %></td>
		<%
		if (user.getIsAdmin() ==1) {
		%>
		<td>[ 
		<% if (list[i].getStatus()==OrderBean.NOT_COMPLETED) { %>
			<a href="<%=request.getContextPath()%>/OrderServlet?cmd=CONFIRM&key=<%=list[i].getOrderId()%>">주문미처리</a>]</td>
		<% }else{ %>
			주문처리] 
		<% } %>
		</td>			
		<%
		}
		else {
		%>
		<td>[ 
		<% if (list[i].getStatus()==OrderBean.NOT_COMPLETED) { %>
			주문미처리]</td>
		<% }else{ %>
			주문처리] 
		<% } %>
		</td>	
		<%
		}
		%>
	</tr>
<% 	
	}	
%>
</table>

</body>
</html>