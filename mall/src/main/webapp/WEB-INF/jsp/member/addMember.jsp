<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>/member/addMember.jsp</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
		var idCheck = 0;
		$(function() {
		    //idCheck 버튼을 클릭했을 때 
		    $("#idCheck").click(function() {
		        //memberid 를 param.
		        var memberId = $("#memberId").val(); 
		        $.ajax({
		            async: true,
		            type : 'POST',
		            data : memberId,
		            url : "/member/idCheck",
		            dataType : "json",
		            contentType: "application/json; charset=UTF-8",
		            success : function(data) {
		                if (data.count > 0) {
		                    alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
		                    $("#divInputId").addClass("has-error")
		                    $("#divInputId").removeClass("has-success")
		                    $("#memberId").focus();
		                } else {
		                    alert("사용가능한 아이디입니다.");
		                    $("#divInputId").addClass("has-success")
		                    $("#divInputId").removeClass("has-error")
		                    $("#memberId").focus();
		                    //아이디가 중복하지 않으면  idCheck = 1 
		                    idCheck = 1;
		                }
		            },
		            error : function(error) {
		                alert("error : " + error);
		            }
		        });
			});
			$("#join").click(function() {
				if(idCheck == 0) { 
					alert("아이디 중복 체크를 해주세요."); 
					$("#memberId").focus(); 
					return false; 
				} else {
			        alert("회원가입을 축하합니다");
			        $("#join").submit();
		        }
			});
		});
	</script>
</head>
<body>
	<div><a class="btn btn-dark" href="${pageContext.request.contextPath}/">홈으로</a></div>
	<div class="row">
		<div class="col-sm-10">
			<!-- 내용 -->
			<article class="container">
	            <div class="page-header">
					<div class="col-md-6 col-md-offset-3">
						<h3>회원 가입</h3>
					</div>
				</div>
				<div class="col-sm-6 col-md-offset-3">
					<form role="form" method="post" action="${pageContext.request.contextPath}/member/addMember">
						<div class="form-group" id="divInputId">
	                        <label for="inputName">아이디</label>
	                        <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력해 주세요">
	                        <input type="button" value="중복확인" id="idCheck">
	                    </div>
	                    <div class="form-group">
	                        <label for="inputName">비밀번호</label>
	                        <input type="password" class="form-control" name="memberPw" placeholder="비밀번호를 입력해 주세요">
	                    </div>
	                    <div class="form-group">
	                        <label for="inputName">이름</label>
	                        <input type="text" class="form-control" name="memberName" placeholder="이름을 입력해 주세요">
	                    </div>
	                    <div class="form-group">
	                        <label for="inputMobile">휴대폰 번호</label>
	                        <input type="text" class="form-control" name="memberPhone" placeholder="휴대폰 번호를 입력해 주세요">
	                    </div>
	                    <div class="form-group">
	                        <label for="InputEmail">주소</label>
	                        <input type="text" class="form-control" name="memberAddress" placeholder="주소를 입력해주세요">
	                    </div>
	                    <div class="form-group">
	                        <label for="InputEmail">이메일</label>
	                        <input type="text" class="form-control" name="memberEmail" placeholder="이메일을 입력해주세요">
	                    </div>
	                    <div class="form-group">
		                    <label for="inputtelNO">성별</label>
	                    	<div class="form-check-inline">
		                        <label for="inputtelNO">
			                    	<input type="radio" class="form-check-input" name="memberGender" value="M">M
			                    </label>
	                    	</div>
		                    <div class="form-check-inline">
		                        <label for="inputtelNO">
			                    	<input type="radio" class="form-check-input" name="memberGender" value="F">F
			                    </label>
		                    </div>
		                </div>
	                    <div class="form-group">
	                        <label for="inputtel">권한</label>
	                        <input type="text" class="form-control" name="memberLevel" value="consumer" readonly>
	                    </div>
                        <label for="inputtel">선택 입력사항</label>
	                    <div class="form-group">
	                        <label for="inputtel">생년월일</label>
	                        <input type="text" class="form-control" name="memberConsumerDateOfBirth" placeholder="0000-00-00">
	                    </div>
	                    <div class="form-group text-center">
	                    	<button class="btn btn-primary" id="join">회원가입</button>
	                        <!-- <input type="button" id="join" class="btn btn-primary" value="회원가입"> -->
	                    </div>
	                </form>
	            </div>
       		</article>
		</div>
	</div>
</body>
</html>