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

	<table border="1" width="800">
		<!-- Tpp 부분 -->
		<tr height="150">
			<td align="center" colspan="2">
			<jsp:include page="Top.jsp">
				<jsp:param value="aaa" name="id" />
			</jsp:include>
			</td>
		</tr>
		
		<!-- Left 부분 -->
		<tr height="400">
			<td align="center" width="200">
			<jsp:include page="Left.jsp" />
			</td>
		
		<!-- Center 부분 -->
			<td align="center" width="600">
			<jsp:include page="Center.jsp" />
			</td>
		</tr>
		
		<!-- Bottom 부분 -->
		<tr height="100">
			<td align="center" colspan="2">
			<jsp:include page="Bottom.jsp" />
			</td>
		</tr>
	</table>

</body>
</html>
