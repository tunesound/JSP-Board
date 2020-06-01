<!--
 TITLE :		디버깅 페이지
-->

<%@page contentType="text/html; charset=euc-kr" %>
<jsp:useBean id="user" scope="session" class="mall.user.UserBean" />

<hr>디버깅 <br><br>
<PRE>
사용자
================================================
<%= user %>

HTTP 요청 파라미터 리스트
================================================
<%
Enumeration params = request.getParameterNames();
String param;
String [] vals;
while(params.hasMoreElements()) {
    param = (String)params.nextElement();
    vals = request.getParameterValues(param);
    out.print(param + "=");
    for(int x=0; x<vals.length; x++) {
		out.print(vals[x]);
        if(x<(vals.length-1)) out.print(", ");
    }
    out.print("<br>");
}
%>

CGI 변수들
================================================
SERVER_NAME.......<%= request.getServerName() %>
SERVER_PROTOCOL...<%= request.getProtocol() %>
SERVER_PORT.......<%= request.getServerPort() %>
REQUEST_METHOD....<%= request.getMethod() %>
PATH_INFO.........<%= request.getPathInfo() %>
PATH_TRANSALATED..<%= request.getPathTranslated() %>
SCRIPT_NAME.......<%= request.getServletPath() %>
QUERY_STRING......<%= request.getQueryString() %>
REMOTE_HOST.......<%= request.getRemoteHost() %>
REMOTE_ADDR.......<%= request.getRemoteAddr() %>
REMOTE_USER.......<%= request.getRemoteUser() %>
AUTH_TYPE.........<%= request.getAuthType() %>
CONTENT_TYPE......<%= request.getContentType() %>
CONTENT_LENGTH....<%= request.getContentLength() %>
HTTP_ACCEPT.......<%= request.getHeader("Accept") %>
HTTP_USER_AGENT...<%= request.getHeader("User-Agent") %>
HTTP_REFERER......<%= request.getHeader("Referer") %>
</PRE>