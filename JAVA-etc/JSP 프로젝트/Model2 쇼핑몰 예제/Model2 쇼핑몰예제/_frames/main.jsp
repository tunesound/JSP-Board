<!--
 TITLE :		메뉴 구성 페이지
-->
<%@page errorPage="/error.jsp"  %>
<%@page pageEncoding="euc-kr" %>

<!-- 사용자 정보 세션범위 자바빈 -->
<jsp:useBean id="user" scope="session" class="mall.user.UserBean" />

<script language="javascript" src="<%=request.getContextPath()%>/_js/imageChange.js"></script>

<table cellspacing=0 cellpadding=0 border=0>
<table width="100%">
<tr>
	<td><img src="http://image.hanmail.net/hanmail/top/0212/logo_xmas.gif"></img>
	       <img src="http://wstatic.naver.com/www/images3/lg_2002christmas.gif"></img>
	</td>	
</tr>
<% 
//로그인 하지 않은 경우
if (user.getUserId() == 0) {
%>
	<tr bgcolor="#666699" height="20">
		<td>	
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=HOME" onMouseOver="show('menu1','','show')" onMouseOut="show('menu1','','hide')">
			<font color="#ffffff">&nbsp;&nbsp;&nbsp;홈</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=ADD&key=0" onMouseOver="show('menu2','','show')" onMouseOut="show('menu2','','hide')">
			<font color="#ffffff">회원등록</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/SearchServlet?cmd=SEARCH_LIST" onMouseOver="show('menu3','','show')" onMouseOut="show('menu3','','hide')">
			<font color="#ffffff">상품검색</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/CartServlet?cmd=CART" onMouseOver="show('menu4','','show')" onMouseOut="show('menu4','','hide')">
			<font color="#ffffff">장바구니</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/OrderServlet?cmd=ORDER_LIST" onMouseOver="show('menu5','','show')" onMouseOut="show('menu5','','hide')">
			<font color="#ffffff">주문현황조회</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=LOGIN" onMouseOver="show('menu6','','show')" onMouseOut="show('menu6','','hide')">
			<font color="#ffffff">로그인</font></a>
		</td>
	</tr>
	</table>
	<br>
<%
}
//로그인한 경우
else {
	//관리자인경우
	if (user.getIsAdmin() == 1) {
	%>
		<tr  bgcolor="#666699" height="20">
			<td>	
				<a href="" onMouseOver="show('menu1','','show')" onMouseOut="show('menu1','','hide')">
				<font color="#ffffff">&nbsp;&nbsp;사용자관리</font></a>&nbsp;&nbsp;
				<font color="#ffffff">|</font>&nbsp;&nbsp;
				<a href="" onMouseOver="show('menu2','','show')" onMouseOut="show('menu2','','hide')">
				<font color="#ffffff">상품관리</font></a>&nbsp;&nbsp;
				<font color="#ffffff">|</font>&nbsp;&nbsp;
				<a href="" onMouseOver="show('menu3','','show')" onMouseOut="show('menu3','','hide')">
				<font color="#ffffff">주문관리</font></a>&nbsp;&nbsp;		
				<font color="#ffffff">|</font>&nbsp;&nbsp;
				<a href="<%=request.getContextPath()%>/MainServlet?cmd=LOGOUT" onMouseOver="show('menu4','','show')" onMouseOut="show('menu4','','hide')">
				<font color="#ffffff">로그아웃</font></a>				
			</td>
		</tr>
		</table>
		
		<div id=menu1 style="position:absolute; left:12px; top:67px; z-index:1; visibility: hidden"  onMouseOut="show('menu1','','hide')" onMouseOver="show('menu1','','show')">
		<table border=0 cellpadding=5 cellspacing=0 width=70>
			<tr>
				<td height=11></td>
			</tr>
			<tr>
				<td bgcolor=#666699 nowrap>
					<div>
					<a href="<%=request.getContextPath()%>/UserServlet?cmd=ADD&key=0">
					<font color="#ffffff">사용자등록</font></a><br>
					<a href="<%=request.getContextPath()%>/UserServlet?cmd=USER_LIST">
					<font color="#ffffff">사용자리스트</font></a><br>
					</div>
				</td>
			</tr>
		</table>
		</div>
		
		<div id=menu2 style="position:absolute; left:96px; top:67px; z-index:1; visibility: hidden"  onMouseOut="show('menu2','','hide')" onMouseOver="show('menu2','','show')">
		<table border=0 cellpadding=5 cellspacing=0 width=70>
			<tr>
				<td height=11></td>
			</tr>
			<tr>
				<td bgcolor=#666699>
					<div><a href="<%=request.getContextPath()%>/ProductServlet?cmd=ADD&key=0">
					<font color="#ffffff">상품등록</a><br>
					<a href="<%=request.getContextPath()%>/ProductServlet?cmd=PRODUCT_LIST">
					<font color="#ffffff">상품리스트</font></font></a><br>
					</div>
				</td>
			</tr>
		</table>
		</div>
		
		<div id=menu3 style="position:absolute; left:172px; top:67px; z-index:1; visibility: hidden"  onMouseOut="show('menu3','','hide')" onMouseOver="show('menu3','','show')">
		<table border=0 cellpadding=5 cellspacing=0 width=70>
			<tr>
				<td height=11></td>
			</tr>
			<tr>
				<td bgcolor=#666699 nowrap>
					<div><a href="<%=request.getContextPath()%>/OrderServlet?cmd=ORDER_LIST">
					<font color="#ffffff">주문리스트</font></a><br>
					</div>
				</td>
			</tr>
		</table>
		</div>
		<br>
	<% 
	}
	//일반 사용자인 경우
	else {
	%>
	<tr bgcolor="#666699" height="20">
		<td>	
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=HOME" onMouseOver="show('menu1','','show')" onMouseOut="show('menu1','','hide')">
			<font color="#ffffff">&nbsp;&nbsp;&nbsp;홈</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=MODIFY&key=<%=user.getUserId() %>" onMouseOver="show('menu2','','show')" onMouseOut="show('menu2','','hide')">
			<font color="#ffffff">회원정보수정</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/SearchServlet?cmd=SEARCH_LIST" onMouseOver="show('menu3','','show')" onMouseOut="show('menu3','','hide')">
			<font color="#ffffff">상품검색</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/CartServlet?cmd=CART" onMouseOver="show('menu4','','show')" onMouseOut="show('menu4','','hide')">
			<font color="#ffffff">장바구니</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/OrderServlet?cmd=ORDER_LIST" onMouseOver="show('menu5','','show')" onMouseOut="show('menu5','','hide')">
			<font color="#ffffff">주문현황조회</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=LOGOUT" onMouseOver="show('menu6','','show')" onMouseOut="show('menu6','','hide')">
			<font color="#ffffff">로그아웃</font></a>
		</td>
	</tr>
	</table>
	<br>
	<%
	}
}
%>
