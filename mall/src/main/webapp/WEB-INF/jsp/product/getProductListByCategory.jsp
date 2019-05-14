<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOARD LIST(spring mvc + mybatis 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#searchButton").click(function(){
			$("#searchForm").submit();
		});
	});
</script>
</head>
<body>
<div class="container">
	<div><a class="btn btn-dark" href="${pageContext.request.contextPath}/">홈으로</a></div>
    <h1>PRODUCT COMMON LIST(spring mvc + mybatis 방식)</h1>
    <div>전체행의 수 : ${count}</div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>product_common_name</th>
                <th>product_common_price</th>
                <th>product_common_description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pcl" items="${productCommonList}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/product/getProduct?productCommonNo=${pcl.productCommonNo}">${pcl.productCommonName}</a></td>
                    <td>${pcl.productCommonPrice}</td>
                    <td>${pcl.prWoductCommonDescription}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form id="searchForm" action="${pageContext.request.contextPath}/product/getProductListByCategory" method="get">
    	<input type="hidden" name="categoryNo" value="${categoryNo}">
    	productName 검색어 : <input type="text" name="searchWord">
    	<button id="searchButton" type="button">검색</button>
    </form>
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous">
            	<a href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a>
            </li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next">
            	<a href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a>
            </li>
        </c:if>
    </ul>
</div>
</body>
</html>