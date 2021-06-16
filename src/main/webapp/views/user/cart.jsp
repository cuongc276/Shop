<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
	<div class="mt-5 col-10 offset-1 mb-5">
		<h2>Giỏ hàng</h2>
		<c:if test="${ not empty sessionScope.error }">
        	<div class="text-danger">${ sessionScope.error }</div>
            <c:remove var="error" scope="session"/>
        </c:if>
        <div class="row">
        	<div class="col-8">
        		<table class="table table-strip table-dark">
	             <thead>
	                 <tr>
	                     <td>Tên sản phẩm</td>
	                     <td>Giá</td>
	                     <td>Số lượng</td>
	                     <td colspan="2">Thao tác</td>
	                 </tr>
	             </thead>
	
	             <tbody>
	                 <c:forEach var="item" items="${ listOrderDetails }">
	                 	<tr>
		                     <td>${ item.product.name }</td>
		                     <td>${ item.price }</td>
		                     <td>${ item.quantity }</td>
		                     <td>
		                         <a class="btn btn-primary" href="${ pageContext.request.contextPath }/user/cart/add?id=${ item.product.id }">+</a>     
		                    </td>
		                    <td>
		                         <a class="btn btn-primary" href="${ pageContext.request.contextPath }/user/cart/remove?id=${ item.product.id }">-</a>     
		                    </td>
		                 </tr>
	                 </c:forEach>
	             </tbody>
	         </table>
        	</div>
        	<div class="col-4">
        		<h3>Total: ${ total } $</h3>
        		<form:form action="${ pageContext.request.contextPath }/user/cart/pay" method="GET" modelAttribute="order">
        			<div class="form-group mt-3">
						<label for=address>Address</label>
					    <form:input path="address" class="form-control" id="address" name="address" autocomplete="off" />
					    <form:errors path="address" element="span" cssClass="text-danger" />
					</div>
        			<div class="d-flex justify-content-center">
			         	<button class="btn btn-primary">Thanh toán</button> 
			         	<a class="btn btn-primary ml-2" href="${ pageContext.request.contextPath }/user/cart/clear">Clear</a>          
			         </div>
        		</form:form>
        	</div>
        </div>
         
			 
    </div>
	

</body>
</html>