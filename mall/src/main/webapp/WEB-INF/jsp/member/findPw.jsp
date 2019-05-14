<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>/member/findPw.jsp</title>
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
		<div align="center"><h1>비밀번호 찾기</h1></div>
		<!-- pageContext.request.contextPath 현재 프로젝트까지의 주소 -->
		<!-- pageContext.request.contextPath}/admin/member/login 해당 주소로 post방식으로 값을 넘긴다 -->
		<form method="post" action="${pageContext.request.contextPath}/member/findPw">
			<div align="center">
				<table class="form-group">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="memberId" value="user"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="memberEmail" value="dkwkvkr@naver.com"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="확인" class="btn btn-primary btn-block"></td>
					</tr>
				</table>
			</div>
		</form>
		<c:if test="${!empty resultMember}">
			<div align="center"><h3>비밀번호 찾기 결과입니다.</h3></div><br>
			<div align="center">
				<table class="form-group">
					<tr>
						<c:choose>
							<c:when test="${empty resultMember}">
								 가입 정보를 찾을 수 없습니다.
							</c:when>
							<c:otherwise>
								입력하신 정보에 해당하는 아이디의 임시 비밀번호를 아래의 이메일로 발송합니다.</p>
								<form action="${pageContext.request.contextPath}/sendPwToEmail" method="post">
									<input type="hidden" name="memberId" value="${resultMember.memberId}">
									<input type="hidden" name="memberEmail" value="${resultMember.memberEmail}">
									${resultMember.memberEmail} <input class="btn btn-primary" type="submit" value="비밀번호 발송">
								</form>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td><a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/member/login">확인</a></td>
					</tr>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>