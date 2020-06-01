<!--
 TITLE :		우편번호 찿기 후 프레임 하단에 결과 출력 
-->
<%@ page import="mall.util.*" %>
<%@page contentType="text/html; charset=euc-kr" %>

<jsp:useBean id="zip" scope="request" class="mall.util.ZipCode" />

<html>
<head>
<title>우편번호 찾기</title>
<LINK href="<%=request.getContextPath()%>/_images/myweb.css" rel="stylesheet">
</head>

<script language="javascript">
function Check(f1,f2,f5) {
	ff1=eval('top.opener.document.user.zipcode');	
	ff5=top.opener.document.user.address1;

	ff1.value = f1+"-"+f2;
	ff5.value = f5;
	top.close();
}
</script>
<body>

<center>
<%	
    String	dongName = "%" + request.getParameter("dongName") + "%";		
	ZipCode[] ziplist = zip.findBy(dongName);
	
    out.println("<table border=1 width=420 bordercolor=#ffffff bgcolor=#c0e0d5 cellspacing=1 cellpadding=1>");
    out.println("<tr><td align=center width=35 height=23>번호</td>");
    out.println("<td align=center width=70 height=23>우편번호</td>");
    out.println(" <td align=center width=305 height=23>주소</td></tr>" );        

	for (int i=0; i<ziplist.length; i++) {
	
		String post_no = ziplist[i].getPostalCode();
	 	String juso = ziplist[i].getRegion1() + " " + ziplist[i].getRegion2();
%>   
		<tr bgcolor=ccccff>
       		<td align=center> <%=i+1%></td>    
			<td align=center>
				<%if ((request.getParameter("nm")).equals("company")) {%>
				<a href='javascript:Check("<%=post_no.substring(0,3)%>","<%=post_no.substring(3,6)%>","<%= juso%>")'>
    			<%=post_no.substring(0,3)%>-<%=post_no.substring(3,6)%></a>
				<%}%>
			</td>
    		<td align=center><%=juso%></td>
       </tr>
    <%
	}
    out.println("</table>");	   
%>

<br>
</center>
</body>
</html>


