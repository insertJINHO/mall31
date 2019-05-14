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
	<h3>���θ� ���� ������</h3>
	<div>
		<c:if test="${loginMember != null}">
			<a href="${pageContext.request.contextPath}/member/memberInfo">${loginMember.memberName}</a>
		</c:if>
		<c:if test="${loginMember == null}">
			<a href="${pageContext.request.contextPath}/member/addMember">ȸ������</a>
		</c:if>
	</div>
	<div>
		<ol>
			<c:if test="${loginMember == null}">
				<li><a href="${pageContext.request.contextPath}/member/login">�α���</a>
			</c:if>
			<c:if test="${loginMember != null}">
				<li><a href="${pageContext.request.contextPath}/member/logout">�α׾ƿ�</a>
			</c:if>
			<li><a href="${pageContext.request.contextPath}/board/getBoardList">�� ���</a>
		</ol>
	</div>
	<!-- ���θ� �޴� -->
	<div>
		<ul>
			<c:forEach items="${categoryList}" var="cl">
				<li><a href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${cl.categoryNo}">${cl.categoryName}</a>
			</c:forEach>
		</ul>
	</div>
</body>
</html>