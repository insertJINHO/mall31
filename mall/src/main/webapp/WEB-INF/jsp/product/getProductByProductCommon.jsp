<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRODUCT(spring mvc + mybatis 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
	<div><a class="btn btn-dark" href="${pageContext.request.contextPath}/">홈으로</a></div>
    <h1>PRODUCT (spring mvc + mybatis 방식)</h1>
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
    <form id="searchForm" method="get">
    	<input type="hidden" name="categoryNo" value="${categoryNo}">
		<div>
			<select name="productColor" id="productColor">
				<option value="">-[필수] 선택-</option>
				<c:forEach var="p" items="${productCommon.products}" >
					<option value="${p.productColor}, ${p.productSize}">${p.productColor}, ${p.productSize}</option>
				</c:forEach>
			</select>
			<select name="productSize">
				<option value="">-[필수] 선택-</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
		</div>	
    	<button id="orderButton" type="button">주문</button>
    	<button id="cartButton" type="button">장바구니</button>
    </form>
</div>
</body>
</html>
