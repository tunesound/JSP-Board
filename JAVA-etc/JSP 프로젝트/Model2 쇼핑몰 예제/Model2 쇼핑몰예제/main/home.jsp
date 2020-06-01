<!--
 TITLE :		첫 페이지
-->
<%@page import="mall.product.*, mall.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="lo" scope="request" class="mall.util.ListObject" />

<html>
<head>
<title>::쇼핑몰::</title>
<LINK href="<%=request.getContextPath()%>/_images/myweb.css" rel="stylesheet">
</head>
<body>

<%@include file="/_frames/main.jsp" %>

<% 	
ProductBean[] specials = (ProductBean[])lo.get(0);	
//상품이미지 경로 조회
ServletContext sc = getServletContext();
String imagePath = sc.getInitParameter("imagePath");
%>

<table width="410" border="0" cellspacing="0" cellpadding="0" height="17">
<tr>

<%
for(int i=0; ((i<specials.length)&&(i<8)); i++) { 		
%>
	<%-- 각열에 4개의 상품을 보여준다 --%>
	<%= ((i%4==0)?"</tr><tr>":"") %>
    <td width="98" valign="top">
		<a href="<%=request.getContextPath()%>/SearchServlet?cmd=PRODUCT_DETAIL&key=<%= specials[i].getProductId() %>"><img src="<%=imagePath%><%=specials[i].getImage()%>" width="89" height="82" border="1"></a><br>
		<img height=10 src="<%=request.getContextPath()%>/_images/line2/dot41.gif" width=7>
		<a href="<%=request.getContextPath()%>/SearchServlet?cmd=PRODUCT_DETAIL&key=<%= specials[i].getProductId() %>"><%= specials[i].getProduct()%></a><br>
		<img height=10 src="<%=request.getContextPath()%>/_images/line2/dot41.gif" width=7><%= specials[i].getModel() %><br>
		<img height=10 src="<%=request.getContextPath()%>/_images/line2/dot41.gif" width=7><%= Money.format(specials[i].getPrice()) %><br>
	</td>
<% 
} 
%>
</tr>
</table>
</body>
</html>
		
	