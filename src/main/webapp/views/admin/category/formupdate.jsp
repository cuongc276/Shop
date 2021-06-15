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
			modelAttribute="category"
			method = "POST"
			action = "${ pageContext.request.contextPath }/admin/category/update?id=${ category.id }"
			enctype="multipart/form-data"
		>
			<input type="hidden" name="_method" value="put" />
			<div class="form-group mt-3">
				<label for="name">Name</label>
			    <form:input path="name" class="form-control" id="name" name="name" autocomplete="off" />
			    <form:errors path="name" element="span" cssClass="text-danger" />
				<c:if test="${ not empty sessionScope.error }">
		        	<div class="text-danger">${ sessionScope.error }</div>
		            <c:remove var="error" scope="session"/>
		        </c:if>
			</div>
			

			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</div>
		</form:form>
	
	</div>
</body>
</html>