<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
</head>

<body>

	<table width="800">
		<tr height="100">
		<!-- 로고이미지 -->
			<td colspan="4" align="center" width="260">
			<img alt="" src="img/logo.jpg" width="200" height="70">
			</td>
			<td colspan="4" align="center">
			<font size="10"> 낭만캠핑 </font>
			</td>
		</tr>
		<tr height="50">
			<td width="110" align="center"> 텐트 </td>
			<td width="110" align="center"> 의자 </td>
			<td width="110" align="center"> 식기류 </td>
			<td width="110" align="center"> 침낭 </td>
			<td width="110" align="center"> 테이블 </td>
			<td width="110" align="center"> 화롯대 </td>
			<td width="140" align="center"> <%=request.getParameter("id") %> </td>
	</table>

</body>
</html>
