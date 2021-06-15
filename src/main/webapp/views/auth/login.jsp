<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap4.min.css"/>
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/login.css"/>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.min.js"></script>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
		
	</head>
	<body>
	
		<div class="container">
			<div class="d-flex justify-content-center h-100">
				<div class="card">
					<div class="card-header">
						<h3>Sign In</h3>
					</div>
					<div class="card-body">
						<form:form
							action="${ pageContext.request.contextPath }/login"
							method="POST"
							modelAttribute="user"
						>
							<div class="input-group form-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-user"></i></span>
								</div>
								<form:input path="email" type="text" class="form-control" placeholder="Email"/>
								
							</div>
							<div class="input-group form-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-key"></i></span>
								</div>
								<form:input path="password" type="password" class="form-control" placeholder="Password"/>
							</div>
							<div class="row align-items-center remember">
								<input type="checkbox" name = "remember"/>Remember Me
							</div>
							<div class="form-group">
								<input type="submit" value="Login" class="btn float-right login_btn">
							</div>
						</form:form>
					</div>
					<div class="card-footer">
						<div class="d-flex justify-content-center links">
							Don't have an account?<a href="#">Sign Up</a>
						</div>
						<div class="d-flex justify-content-center">
							<a href="#">Forgot your password?</a>
						</div>
					</div>
					<c:if test="${ not empty sessionScope.error }">
						<div class="alert alert-danger">${ sessionScope.error }</div>
						<c:remove var="error" scope="session"/>
					</c:if>
				</div>
			</div>
		</div>
		
		
		<!--  
		<div >
			<form:form
				modelAttribute= "user"
				action = "${ pageContext.request.contextPath }/home"
				method = "POST"
			>
				<span>Account</span>
				<form:input path="name" type="text" aria-label="Account" class="form-control" placeholder="Username"/>
				<span>Password</span>
				<form:input path="password" type="password" aria-label="Password" class="form-control" placeholder="Password"/>
			</form:form>
		</div>
		-->
	</body>
</html>