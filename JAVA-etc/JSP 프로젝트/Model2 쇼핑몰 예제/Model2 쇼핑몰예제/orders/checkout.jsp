<!--
 TITLE :		주문신청 페이지 
-->
<%@page import="mall.order.*, mall.product.*, mall.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="order" scope="request" class="mall.order.OrderBean" />
<jsp:useBean id="userbean" scope="request" class="mall.user.UserBean" />
<jsp:useBean id="item" scope="request" class="mall.order.ItemBean" />

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

</script>

<body>

<%@include file="/_frames/main.jsp" %>

<%	
	userbean.init(order.getUserId());	
%>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD">
	<tr height=30>
		<td>&nbsp;<b>주문신청완료</b></td>
	</tr>
	<tr bgcolor="#000000"><td colspan="2"><img src="<%=request.getContextPath()%>/_images/spacer.GIF" width="1" height="3" alt="" border="0"></td></tr>
</table>
<table>
	<tr>
		<td align="right">주문번호:</td>
		<td><%= order.getOrderId() %></td>
	</tr>
	<tr>
		<td align="right">주문자명:</td>
		<td><%= userbean.getName() %></td>
	</tr>
	<tr>
		<td align="right">주문일자:</td>
		<td><%= order.getOrderDate() %></td>
	</tr>
	<tr>
		<td align="right">상태:</td>
		<td><% if (order.getStatus()==OrderBean.NOT_COMPLETED) out.print("주문미처리"); else out.print("주문처리"); %></td>
	</tr>
	<tr>
		<td align="right">주문총액:</td>
		<td><%= Money.format(order.getOrderTotal())%>원</td>
	</tr>
	<tr>
		<td align="right">배송지 주소:</td>
		<td><%= userbean.getZipcode()%>&nbsp;<%= userbean.getAddress1()%>&nbsp;<%= userbean.getAddress2()%>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"></td>
		<td></td>
	</tr>
	<tr>
		<table width="100%" cellpadding="0" cellspacing="0">			
			<tr bgcolor="silver">
				<td>&nbsp;</td>				
				<td><strong>상품명</strong></td>
				<td><strong>판매단위</strong></td>
				<td><strong>단가</strong></td>
				<td><strong>수량</strong></td>		
				<td><strong>소계</strong></td>						
				<td>&nbsp;</td>
			</tr>
			<%
			ItemBean[] itemList = item.findByOrderId(order.getOrderId());
			for(int i=0; i<itemList.length; i++) {			
				ProductBean pb = new ProductBean();
				pb.init(itemList[i].getProductId());
			%>
			<tr bgcolor="<%= (i%2 == 0)?"#fcfcfc":"#e5e5e5" %>">
				<td>&nbsp;</td>
				<td> <%=pb.getProduct() %></td>
				<td> <%=pb.getUnit() %></td>
				<td> <%=Money.format(pb.getPrice()) %></td>
				<td> <%=itemList[i].getQty() %></td>
				<td> <%=Money.format(pb.getPrice()*itemList[i].getQty()) %></td>
				<td>&nbsp;</td>
			</tr>
			<%
			}
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/MainServlet?cmd=HOME"><img src="<%=request.getContextPath()%>/_images/icon1-05.gif" width="39" height="19" border="0" name="Image13"> </a> </td>
			</tr>			
		</table>
	</tr>
</table>

</body>
</html>