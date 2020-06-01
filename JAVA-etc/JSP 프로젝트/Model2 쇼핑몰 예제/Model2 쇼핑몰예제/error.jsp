<!--
 TITLE :		에러 페이지 
 DESCRIPTION :	 
-->
<%@page import="java.io.*, mall.util.*" %>
<%@page isErrorPage="true" %>
<%@page contentType="text/html; charset=euc-kr" %>

<h3>실행중 에러가 발생하였습니다.</h3>
<br>
<%= exception %>
<br>
<pre>
<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</pre>
<br>
