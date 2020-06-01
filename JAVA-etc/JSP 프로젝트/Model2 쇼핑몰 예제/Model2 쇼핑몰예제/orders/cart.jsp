<!--
 TITLE :		장바구니 페이지
 DESCRIPTION :	 
-->
<%@page import="mall.product.*, mall.order.*, mall.util.*, java.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="cart" scope="session" class="java.util.HashMap" />
<jsp:useBean id="loItem" scope="request" class="mall.util.ListObject" />
<jsp:useBean id="loPb" scope="request" class="mall.util.ListObject" />

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

	var save_off = new Image();
	save_off.src = "<%=request.getContextPath()%>/_images/buttons/save.gif";
	var save_on = new Image();
	save_on.src = "<%=request.getContextPath()%>/_images/buttons/save_on.gif";
		
	var back_off = new Image();
	back_off.src = "<%=request.getContextPath()%>/_images/buttons/back.gif";
	var back_on = new Image();
	back_on.src = "<%=request.getContextPath()%>/_images/buttons/back_on.gif";
	
	function goOrder() {
		if(<%=cart.size()%> == 0) {
			alert("장바구니가 비어있습니다.");
			return;
		}		
		document.checkout.action = "CartServlet";
		document.checkout.submit();
	}	
</script>

<body>
<%@include file="/_frames/main.jsp" %>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD">
	<tr>		
		<td>&nbsp;<b>장바구니</b></td>
		<td align=right valign="center">
			<a href="<%=request.getContextPath()%>/SearchServlet?cmd=SEARCH_LIST" title="뒤로가기"
				ONMOUSEOVER="rollIn('back');" 
				ONMOUSEOUT="rollOut('back');" ><img name="back" src="<%=request.getContextPath()%>/_images/buttons/back.gif" width="41" height="13" alt="쇼핑계속하기" border="0" hspace=15 vspace=5 align=middle></a>
		</td>
	</tr>
	<tr bgcolor="#000000">
		<td colspan="2"><img src="<%=request.getContextPath()%>/_images/spacer.GIF" width="1" height="3" alt="" border="0">
		</td>
	</tr>
</table>

<form name="cart" action="<%=request.getContextPath()%>/CartServlet" method="POST">
<input type="hidden" name="cmd" value="UPDATE">
<table width="100%" cellpadding="0" cellspacing="0">
	
	<tr bgcolor="silver">
		<td><strong>삭제</strong></td>
		<td><strong>상품명</strong></td>
		<td><strong>판매자명</strong></td>		
		<td><strong>판매단위</strong></td>	
		<td><strong>단가</strong></td>	
		<td><strong>수량</strong></td>	
		<td><strong>소계</strong></td>	
		<td>&nbsp;</td>
	</tr>
<% 
	//장바구니의 총금액
	double total_amount = 0;

	ItemBean[] itemList = (ItemBean[])loItem.get(0);
	ProductBean[] pbList = (ProductBean[])loPb.get(0);
	for(int i=0; i<itemList.length; i++) {		
		total_amount = total_amount + (pbList[i].getPrice() * itemList[i].getQty());	
%>
	<tr bgcolor="<%= (i%2 == 0)?"#fcfcfc":"#e5e5e5" %>">
		<td>
			<input id='cartitems' type='checkbox' name='cartitems' value=<%=itemList[i].getProductId()%>>
		</td>
		<td>
			<a href="<%=request.getContextPath()%>/SearchServlet?cmd=PRODUCT_DETAIL&key=<%= itemList[i].getProductId() %>"><%= pbList[i].getProduct() %></a>
		</td>				
		<td><%= pbList[i].getSeller() %></td>
		<td><%= pbList[i].getUnit() %></td>
		<td><%= Money.format(pbList[i].getPrice()) %></td>
		<td><input type="text" name="qty" SIZE="3" value=<%= itemList[i].getQty() %>></td>
		<td><%= Money.format(pbList[i].getPrice() * itemList[i].getQty()) %></td>
	</tr>
		
<% 	}
	
%>
	
	<tr>
		<td>&nbsp;</td>		
	</tr>	
	<tr>
		<td>선택한 상품수 :</td>
		<td><%= pbList.length %></td>
	</tr>	
	<tr>
		<td>총금액 :</td>
		<td><%= Money.format(total_amount) %>원</td>
	</tr>	
	<tr>
		<td>&nbsp;</td>		
	</tr>	
	<tr>
		<td>
			<input type=image src="<%=request.getContextPath()%>/_images/icon1-76.gif" width="64" height="19" border=0 >&nbsp;&nbsp;&nbsp;
			<a href="javascript:goOrder()";><img src="<%=request.getContextPath()%>/_images/icon-02.gif" width="79" height="19" border="0" name="Image13"></a>
		</td>
	</tr>	
</table>
</form>

<form name="checkout" action="<%=request.getContextPath()%>/CartServlet" method="POST">
	<input type="hidden" name="cmd" value="CHECKOUT">
</form>

</body>
</html>
