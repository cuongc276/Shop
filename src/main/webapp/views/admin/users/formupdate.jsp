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
			modelAttribute="user"
			method = "POST"
			action = "${ pageContext.request.contextPath }/admin/users/update?id=${ user.id }"
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
						<label for="email">Email</label>
					    <form:input path="email" class="form-control" id="email" name="email" autocomplete="off" />
					     <form:errors path="email" element="span" cssClass="text-danger" />
					</div>
					
					<div class="form-group mt-3">
						<label for="password">Password</label>
						<form:password path="password" value="*********" name="password" class="form-control" readonly="true"/>
						 <form:errors path="password" element="span" cssClass="text-danger" />
					</div>
				</div>
				<div class="col-3">
					<img class="mt-3 w-100 h-100" src="${ pageContext.request.contextPath }/views/storage/${ user.photo }">
				</div>
			</div>
			<div class="form-group mt-3">
				<label for="photo">Image</label>
			    <input type="file" class="form-control" id="photo" name="upload_file">
			     <form:errors path="photo" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="admin">Role</label>
				<form:select path="admin" id="admin" class="form-control">
					<form:option value="1">Admin</form:option>
					<form:option value="2">User</form:option>
				</form:select>
				 <form:errors path="admin" element="span" cssClass="text-danger" />
			</div>
			
			
			
			<div class="form-group mt-3">
				<label for="activate">Active</label>
				<form:select path="activate" id="activate" class="form-control">
					<form:option value="1">Activate</form:option>
					<form:option value="2">Deactivate</form:option>
				</form:select>
				 <form:errors path="activate" element="span" cssClass="text-danger" />
			</div>

			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
				<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModaldetail">
					Chi tiết hoạt động
				</button>
                    <!-- Modal -->					
					<div class="modal fade" id="exampleModaldetail" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-dialog-scrollable">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title text-dark" id="exampleModalLabel">Chi tiết hoạt động</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body text-dark">
					      	
					        <c:forEach var="i" items="${ listOrders }">
					        	<p> Ngày tạo: ${ i.create_date } </p>
					        	<table class="table table-strip table-dark">
					        			<thead>
					        				<tr>
								                <td>Sản phẩm</td>
								                <td>Số lượng</td>
								                <td>Giá</td>
						        			</tr>
					        			</thead>
					        			<tbody>
					        				<c:forEach var="order" items="${ i.order_details }">
						        				<tr>
							        				<td>${ order.product.name }</td>
									                <td>${ order.quantity }</td>
									                <td>${ order.price }</td>
						        				</tr>
						        			</c:forEach>	
					        			</tbody>
					        		</table>
					        </c:forEach>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>						        
					      </div>
					    </div>
					  </div>
					</div>		
                    	
					
	                     
			</div>
		</form:form>
	
	</div>
</body>
</html>