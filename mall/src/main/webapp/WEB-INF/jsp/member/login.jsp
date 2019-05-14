<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>/member/login.jsp</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div><a class="btn btn-dark" href="${pageContext.request.contextPath}/">홈으로</a></div>
	<div class="container">
		<div align="center"><h1>회원 로그인</h1></div>
		<!-- pageContext.request.contextPath 현재 프로젝트까지의 주소 -->
		<!-- pageContext.request.contextPath}/admin/member/login 해당 주소로 post방식으로 값을 넘긴다 -->
		<form method="post" action="${pageContext.request.contextPath}/member/login">
			<div align="center">
				<table class="form-group">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="memberId" value="user"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="memberPw" value="1234"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="로그인" class="btn btn-primary btn-block"></td>
					</tr>
				</table>
			</div>
		</form>
		<div align="center">
			<a href="${pageContext.request.contextPath}/member/findId">아이디 찾기</a> / 
			<a href="${pageContext.request.contextPath}/member/findPw">비밀번호 찾기</a>
		</div>
		<div align="center">
			<a href="${pageContext.request.contextPath}/member/addMember">회원 가입</a> 
		</div>
	</div>
</body>
</html>