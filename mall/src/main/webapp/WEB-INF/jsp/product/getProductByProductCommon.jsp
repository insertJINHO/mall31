<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRODUCT(spring mvc + mybatis 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(function(){
		$("#productColor").change(function(){
			console.log('productColor change 테스트');
			$("#optionForm").submit();
		});
		$("#productSize").change(function(){
			console.log('productSize change 테스트');
			$("#optionForm").submit();
		});
		$(document).keydown(function(event) {
			if (event.keyCode == '116' || event.ctrlKey == true && (event.keyCode == '82' || event.keyCode == '116')) {
				console.log('keydown 테스트');
				location.href='/product/getProductByProductCommon?productCommonNo='+${productCommon.productCommonNo};
			}
		});
	});
</script>
</head>
<body>
<div class="container">
	<div><a class="btn btn-dark" href="${pageContext.request.contextPath}/">홈으로</a></div>
    <h1>PRODUCT (spring mvc + mybatis 방식)</h1>
	<!-- 상품 기본 정보 출력 -->
    <table class="table table-striped">
        <tr>
			<th>product_common_name</th>
			<td>${productCommon.productCommonName}</td>
		</tr>
		<tr>
			<th>product_common_price</th>
			<td>${productCommon.productCommonPrice}</td>
		</tr>
		<tr>
			<th>product_common_description</th>
			<td>${productCommon.productCommonDescription}</td>
		</tr>
    </table>
    <form id="optionForm" action="${pageContext.request.contextPath}/product/getProductByProductCommon" method="get">
    	<input type="hidden" name="productCommonNo" value="${productCommon.productCommonNo}">
		<!-- color, size 출력 -->
		<div>
			<!-- color 중복 값을 제거하기 위한 변수 -->
			<c:set var="color" value="color"></c:set>
			<!-- color 선택 -->
			<select name="productColor" id="productColor">
				<option value="">-[필수] Color 선택-</option>
				<!-- color 출력 -->
				<c:forEach var="product" items="${productCommon.products}" >
					<!-- color 중복 값 제거 -->
					<c:if test="${product.productColor ne color}">
						<!-- color 출력 후 선택한 color 고정 -->
	        			<option value="${product.productColor}" <c:if test="${productColor==product.productColor}">selected</c:if>>${product.productColor}</option>
	        			<c:set var="color" value="${product.productColor}"></c:set>
	        		</c:if>
				</c:forEach>
			</select>
			<!-- size 출력 -->
			<select name="productSize">
				<option value="">-[필수] Size 선택-</option>
				<c:forEach var="productSize" items="${productSize}" >
						<option value="${productSize}">${productSize}</option>
				</c:forEach>
			</select>
		</div>
    </form>
   	<form id="" action="" method="post">
   		<input type="hidden" name="" value="${orderProductCommon}">
	    <c:forEach var="order" items="${orderProductCommon}">
	   		상품 정보
	   		<button id="cartButton" type="button">1</button>+
	   		<input type="hidden" name="productPrice${0}" value="계산된 값">
	    </c:forEach>
   		<input type="hidden" name="totalPrice" value="계산된 값">
    	<button id="orderButton" type="button" onclick="javascript: optionForm.action='/manage/update';"/>주문</button>
    	<button id="cartButton" type="button">장바구니</button>
   	</form>
</div>
</body>
</html>
