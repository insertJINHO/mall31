<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<h3>첨부파일</h3>
		<form id="modifyForm" action="${pageContext.request.contextPath}/board/removeBoardFile" method="post">
	        <div>
		        <c:if test="${boardFile ne null}">
					<input type="hidden" name="boardFileNo" value="${boardFile.boardFileNo}">
					<a href="${pageContext.request.contextPath}/upload/${boardFile.boardFileSaveName}.${boardFile.boardFileExt}">
						${boardFile.boardFileOriginName}.${boardFile.boardFileExt}
					</a>
					<input class="form-control" name="boardNo" value="${board.boardNo}" type="hidden">
					<input class="btn btn-default" type="submit" value="삭제">
				</c:if>
			</div>
	    </form>
		<c:if test="${boardFile == null}">
			<form id="addForm" action="${pageContext.request.contextPath}/board/addBoardFile" enctype="multipart/form-data" method="post">
		        <div class="form-group">
		            <label for="boardFile">boardFile :</label>
		            <input class="form-control" name="boardNo" value="${board.boardNo}" type="hidden">
		            <input class="form-control" name="boardFile" id="boardFile" type="file"/>
		            <input class="btn btn-default" id="addButton" type="submit" value="첨부"/>
		        </div>
		    </form>
		</c:if>
    </div>
</body>
</html>