<!--
 TITLE :		���� ������ 
 DESCRIPTION :	 
-->
<%@page import="java.io.*, mall.util.*" %>
<%@page isErrorPage="true" %>
<%@page contentType="text/html; charset=euc-kr" %>

<h3>������ ������ �߻��Ͽ����ϴ�.</h3>
<br>
<%= exception %>
<br>
<pre>
<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</pre>
<br>
