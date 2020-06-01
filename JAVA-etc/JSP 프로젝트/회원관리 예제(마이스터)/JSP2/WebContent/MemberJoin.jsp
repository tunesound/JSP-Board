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

	<h2>회원가입</h2>
	
	<form action="MemberJoinProc.jsp" method="post">
		<table width="500" border="1">
			<tr height="50">
				<td width="150" align="center">아이디</td>
				<td width="350" align="center"><input type="text" name="id" size="40" placeholder="아이디를 넣으세요">
				</td>
			</tr>
			<tr height="50">
				<td width="150" align="center">패스워드</td>
				<td width="350" align="center"><input type="password" name="pass1" size="40" placeholder="비밀번호는 영문과 숫자만 넣어주세요">
				</td>
			<tr height="50">
				<td width="150" align="center">이메일</td>
				<td width="350" align="center"><input type="email" name="email" size="40" placeholder="이메일을 입력해주세요">
				</td>
			</tr>
			<tr height="50">
				<td width="150" align="center">전화번호</td>
				<td width="350" align="center"><input type="tel" name="tel" size="40" placeholder="전화번호를 입력해주세요">
				</td>
			</tr>
			<tr height="50">
				<td width="150" align="center">주소</td>
				<td width="350" align="center"><input type="text" name="address" size="40" placeholder="주소를 입력해주세요">
				</td>
			</tr>
			<tr height="50">
				<td align="center" colspan="2"> <input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>

</body>
</html>
