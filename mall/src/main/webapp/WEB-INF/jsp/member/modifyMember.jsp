<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 

<title>BOARD MODIFY FORM(spring mvc + mybatis 방식)</title>
</head>
<body>
	<div class="row">
		<div class="col-sm-10">
			<!-- 내용 -->
			<article class="container">
	            <div class="page-header">
					<div class="col-md-6 col-md-offset-3">
						<h3>회원 정보 수정</h3>
					</div>
				</div>
				<div class="col-sm-6 col-md-offset-3">
					<form role="form" method="post" action="${pageContext.request.contextPath}/member/modifyMember">
						<div class="form-group">
	                        <label for="inputName">아이디</label>
	                        <input type="text" class="form-control" name="memberId" value="${member.memberId}" readonly>
	                    </div>
	                    <div class="form-group">
	                        <label for="inputName">비밀번호</label>
	                        <a class="btn btn-default" href="${pageContext.request.contextPath}/member/modifyMemberPw?memberId=${member.memberId}">비밀번호 수정</a>
	                    </div>
	                    <div class="form-group">
	                        <label for="inputName">이름</label>
	                        <input type="text" class="form-control" name="memberName" value="${member.memberName}" readonly>
	                    </div>
	                    <div class="form-group">
	                        <label for="inputMobile">휴대폰 번호</label>
	                        <input type="text" class="form-control" name="memberPhone" value="${member.memberPhone}">
	                    </div>
	                    <div class="form-group">
	                        <label for="InputEmail"> 주소</label>
	                        <input type="text" class="form-control" name="memberAddress" value="${member.memberAddress}">
	                    </div>
	                    <div class="form-group">
		                    <label for="inputtelNO">성별</label>
		                    <input type="text" class="form-control" name="memberGender"  value="${member.memberGender}" readonly>
		                </div>
	                    <div class="form-group">
	                        <label for="inputtel">권한</label>
	                        <input type="text" class="form-control" name="memberLevel" value="${member.memberLevel}" readonly>
	                    </div>
	                    <div class="form-group text-center">
	                        <input type="submit" class="btn btn-primary" value="확인">
	                    </div>
	                </form>
	            </div>
       		</article>
		</div>
	</div>
</body>
</html>

