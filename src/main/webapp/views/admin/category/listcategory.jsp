<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href=" ${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
</head>
<body>
	<a href="${ pageContext.request.contextPath }/admin/category/show">Show</a>
	<div class="mt-5 col-10 offset-1">
        <div class="">
            <a
                class="btn btn-success col-1"
                href="${ pageContext.request.contextPath }/admin/category/create">
                Create
            </a>
        </div>
        <c:if test="${ not empty sessionScope.status }">
        	<div class="text-danger">${ sessionScope.status }</div>
            <c:remove var="status" scope="session"/>
        </c:if>
        <table class="table table-strip table-dark">
             <thead>
                 <tr>
                     <td>Id</td>
                     <td>Name</td>
                     <td colspan="2">Thao tác</td>
                 </tr>
             </thead>

             <tbody>
                 <c:forEach var="item" items="${ listCategory }">
                 	<tr>
	                     <td>${ item.id }</td>
	                     <td>${ item.name }</td>
	                     <td>
	                         <a
	                             class="btn btn-primary"
	                             href="${ pageContext.request.contextPath }/admin/category/edit?id=${ item.id }">Update</a>
	                     </td>
	                     <td>
	                         <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${ item.id }">
								  Delete
								</button>
								<!-- Modal -->					
								<div class="modal fade" id="exampleModal${ item.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog modal-dialog-centered">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title text-dark" id="exampleModalLabel">Chắc chắn</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body text-dark">
								        Bạn chắc chắn? 
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
								        <a id="delete" type="button" class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/category/delete?id=${ item.id }">Xóa</a>
								      </div>
								    </div>
								  </div>
								</div>
	                     </td>
	                 </tr>
                 </c:forEach>
             </tbody>
         </table>
    </div>
</body>
</html>