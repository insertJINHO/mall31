<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>index.jsp</title>
</head>
<body>
	<h1>INDEX</h1>
	<h3>쇼핑몰 메인 페이지</h3>
	<div>
		<c:if test="${loginMember != null}">
			<a href="${pageContext.request.contextPath}/member/memberInfo">${loginMember.memberName}</a>
		</c:if>
		<c:if test="${loginMember == null}">
			<a href="${pageContext.request.contextPath}/member/addMember">회원가입</a>
		</c:if>
	</div>
	<div>
		<ol>
			<c:if test="${loginMember == null}">
				<li><a href="${pageContext.request.contextPath}/member/login">로그인</a>
			</c:if>
			<c:if test="${loginMember != null}">
				<li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
			</c:if>
			<li><a href="${pageContext.request.contextPath}/board/getBoardList">글 목록</a>
		</ol>
	</div>
	<!-- 쇼핑몰 메뉴 -->
	<div>
		<ul>
			<c:forEach items="${categoryList}" var="cl">
				<li><a href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${cl.categoryNo}">${cl.categoryName}</a>
			</c:forEach>
		</ul>
	</div>
</body>
</html>