<!--
 TITLE :		�޴� ���� ������
-->
<%@page errorPage="/error.jsp"  %>
<%@page pageEncoding="euc-kr" %>

<!-- ����� ���� ���ǹ��� �ڹٺ� -->
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
//�α��� ���� ���� ���
if (user.getUserId() == 0) {
%>
	<tr bgcolor="#666699" height="20">
		<td>	
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=HOME" onMouseOver="show('menu1','','show')" onMouseOut="show('menu1','','hide')">
			<font color="#ffffff">&nbsp;&nbsp;&nbsp;Ȩ</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=ADD&key=0" onMouseOver="show('menu2','','show')" onMouseOut="show('menu2','','hide')">
			<font color="#ffffff">ȸ�����</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/SearchServlet?cmd=SEARCH_LIST" onMouseOver="show('menu3','','show')" onMouseOut="show('menu3','','hide')">
			<font color="#ffffff">��ǰ�˻�</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/CartServlet?cmd=CART" onMouseOver="show('menu4','','show')" onMouseOut="show('menu4','','hide')">
			<font color="#ffffff">��ٱ���</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/OrderServlet?cmd=ORDER_LIST" onMouseOver="show('menu5','','show')" onMouseOut="show('menu5','','hide')">
			<font color="#ffffff">�ֹ���Ȳ��ȸ</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=LOGIN" onMouseOver="show('menu6','','show')" onMouseOut="show('menu6','','hide')">
			<font color="#ffffff">�α���</font></a>
		</td>
	</tr>
	</table>
	<br>
<%
}
//�α����� ���
else {
	//�������ΰ��
	if (user.getIsAdmin() == 1) {
	%>
		<tr  bgcolor="#666699" height="20">
			<td>	
				<a href="" onMouseOver="show('menu1','','show')" onMouseOut="show('menu1','','hide')">
				<font color="#ffffff">&nbsp;&nbsp;����ڰ���</font></a>&nbsp;&nbsp;
				<font color="#ffffff">|</font>&nbsp;&nbsp;
				<a href="" onMouseOver="show('menu2','','show')" onMouseOut="show('menu2','','hide')">
				<font color="#ffffff">��ǰ����</font></a>&nbsp;&nbsp;
				<font color="#ffffff">|</font>&nbsp;&nbsp;
				<a href="" onMouseOver="show('menu3','','show')" onMouseOut="show('menu3','','hide')">
				<font color="#ffffff">�ֹ�����</font></a>&nbsp;&nbsp;		
				<font color="#ffffff">|</font>&nbsp;&nbsp;
				<a href="<%=request.getContextPath()%>/MainServlet?cmd=LOGOUT" onMouseOver="show('menu4','','show')" onMouseOut="show('menu4','','hide')">
				<font color="#ffffff">�α׾ƿ�</font></a>				
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
					<font color="#ffffff">����ڵ��</font></a><br>
					<a href="<%=request.getContextPath()%>/UserServlet?cmd=USER_LIST">
					<font color="#ffffff">����ڸ���Ʈ</font></a><br>
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
					<font color="#ffffff">��ǰ���</a><br>
					<a href="<%=request.getContextPath()%>/ProductServlet?cmd=PRODUCT_LIST">
					<font color="#ffffff">��ǰ����Ʈ</font></font></a><br>
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
					<font color="#ffffff">�ֹ�����Ʈ</font></a><br>
					</div>
				</td>
			</tr>
		</table>
		</div>
		<br>
	<% 
	}
	//�Ϲ� ������� ���
	else {
	%>
	<tr bgcolor="#666699" height="20">
		<td>	
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=HOME" onMouseOver="show('menu1','','show')" onMouseOut="show('menu1','','hide')">
			<font color="#ffffff">&nbsp;&nbsp;&nbsp;Ȩ</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=MODIFY&key=<%=user.getUserId() %>" onMouseOver="show('menu2','','show')" onMouseOut="show('menu2','','hide')">
			<font color="#ffffff">ȸ����������</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/SearchServlet?cmd=SEARCH_LIST" onMouseOver="show('menu3','','show')" onMouseOut="show('menu3','','hide')">
			<font color="#ffffff">��ǰ�˻�</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/CartServlet?cmd=CART" onMouseOver="show('menu4','','show')" onMouseOut="show('menu4','','hide')">
			<font color="#ffffff">��ٱ���</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/OrderServlet?cmd=ORDER_LIST" onMouseOver="show('menu5','','show')" onMouseOut="show('menu5','','hide')">
			<font color="#ffffff">�ֹ���Ȳ��ȸ</font></a>&nbsp;&nbsp;
			<font color="#ffffff">|</font>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/MainServlet?cmd=LOGOUT" onMouseOver="show('menu6','','show')" onMouseOut="show('menu6','','hide')">
			<font color="#ffffff">�α׾ƿ�</font></a>
		</td>
	</tr>
	</table>
	<br>
	<%
	}
}
%>
