<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href=" ${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
</head>
<body>
	<div class="col-10 offset-1">
		<c:if test="${ not empty sessionScope.status }">
        	<div class="text-danger">${ sessionScope.status }</div>
            <c:remove var="status" scope="session"/>
        </c:if>
		<form:form 
			modelAttribute="product"
			method = "POST"
			action = "${ pageContext.request.contextPath }/admin/product/store"
			enctype="multipart/form-data"
		>
			<input type="hidden" name="_method" value="put" />
			<div class="row">
				<div class="col-9">
					<div class="form-group mt-3">
						<label for="name">Name</label>
					    <form:input path="name" class="form-control" id="name" name="name" autocomplete="off" />
					    <form:errors path="name" element="span" cssClass="text-danger" />
					</div>
					
					<div class="form-group mt-3">
						<label for="price">Price</label>
					    <form:input path="price" class="form-control" id="price" name="price" autocomplete="off" />
					     <form:errors path="price" element="span" cssClass="text-danger" />
					</div>
					
					<div class="form-group mt-3">
						<label for="create_date">Create date</label>
						<form:input path="create_date" name="create_date" class="form-control" readonly="true" value="${ getNow }"/>
						 <form:errors path="create_date" element="span" cssClass="text-danger" />
					</div>
				</div>
				<div class="col-3">
					<img class="mt-3 w-100 h-100" src="${ product.image }">
				</div>
			</div>
			
			<div class="form-group mt-3">
				<label for="category">Category</label>
				<form:select path="category" class="form-select">
				<c:forEach var = "i" items = "${ listCategories }">
					<form:option  value="${ i.id }">${ i.name }</form:option>
				</c:forEach>	
			</form:select>
				 <form:errors path="create_date" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="image">Image</label>
				<form:input path="image" class="form-control"/>
				<form:errors path="image" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="available">Available</label>
				<form:select path="available" id="available" class="form-control">
					<form:option value="1">Available</form:option>
					<form:option value="2">Unavailable</form:option>
				</form:select>
				 <form:errors path="available" element="span" cssClass="text-danger" />
			</div>

			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</div>
		</form:form>
	
	</div>
</body>
</html>