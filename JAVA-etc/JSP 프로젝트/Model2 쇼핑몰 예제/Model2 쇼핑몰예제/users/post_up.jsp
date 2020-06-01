<!--
 TITLE :		우편번호 찾기 페이지 
-->
<%@page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>우편번호 찾기</title>
<LINK href="<%=request.getContextPath()%>/_images/myweb.css" rel="stylesheet">
</head>

<script language="javascript">
<!--
function MM_goToURL() {
  	for (var i=0; i< (MM_goToURL.arguments.length - 1); i+=2) {
    	eval(MM_goToURL.arguments[i]+".location='"+MM_goToURL.arguments[i+1]+"'");
 	}
	document.MM_returnValue = false;
}

function closeWindow() {
	window.top.close();
}
function zip_submit() {
	var dongName=document.form1.dongName.value;
	if (dongName == "") {
		alert("동이름을 입력하세요");	
		document.form1.dongName.focus();
		return false;
	}
    form1.submit();
	return true;
}
-->
</script>

<body bgcolor="#ffffff" onload="javascript:document.form1.dongName.focus();">
<form method="post" name ="form1" target="down" action ="postcode.jsp?nm=<%=request.getParameter("nm")%>">
<table width="425" border="0" cellspacing="0" cellpadding="0">
	<tr>
    	<td colspan="2" height="3"></td>
  	</tr>
 	<tr> 
    	<td colspan="2"></td>
  	</tr>
  	<tr> 
    	<td width="142">&nbsp;</td>
    	<td width="283"> 
      		<table border="0" cellspacing="0" cellpadding="0" width="155" height="108">
        		<tr> 
          			<td>&nbsp;</td>
          			<td>&nbsp;</td>
          			<td>&nbsp;</td>
        		</tr>
       			<tr align="middle"> 
          			<td colspan="3"> 
            			<table border="0" cellspacing="1" cellpadding="1" bgcolor="#000000" width="151">
              				<tr bgcolor="#ffffff"> 
               					<td height="23" bgcolor="#effef0" width="58"> 
                  					<div align="center"><font color="#000000">동이름</font></div>
                				</td>
                				<td height="23" width="84"> 
                  					<input type=text name="dongName" size="10" maxlength="20" >
                				</td>
              				</tr>
            			</table>
          			</td>
        		</tr>
       			<tr> 
          			<td height="5"></td>
          			<td height="5"></td>
          			<td height="5"></td>
        		</tr>
        		<tr align="right"> 
          			<td colspan="3"> 
           				<div align="center">
			  			<input type=submit name=command value='확인' onclick='javascript:zip_submit()'>
              			<input type=button name=cancel value='취소'onclick='javascript:closeWindow()' >
						</div>
          			</td>
       			</tr>
      		</table>
    	</td>
  	</tr>
</table>
<table width="425" border="0" cellspacing="0" cellpadding="0">
  	<tr> 
    	<td colspan="2" height="5"></td>
  	</tr>
</table>
</form>
</body>
</html>
