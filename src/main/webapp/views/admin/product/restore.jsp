<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
	<a href="${ pageContext.request.contextPath }/admin/product/show">Show</a>
	<div class="mt-5 col-10 offset-1">
        <div class="">
            <a
                class="btn btn-success col-1"
                href="${ pageContext.request.contextPath }/admin/product/create">
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
                     <td>Tên sản phẩm</td>
                     <td>Giá</td>
                     <td>Hình ảnh</td>
                     <td>Ngày tạo</td>
                     <td>Danh mục</td>
                     <td colspan="2">Thao tác</td>
                 </tr>
             </thead>

             <tbody>
                 <c:forEach var="item" items="${ listProducts }">
                 	<tr>
	                     <td>${ item.id }</td>
	                     <td>${ item.name }</td>
	                     <td>${ item.price }</td>
	                     <td>${ item.image }</td>
	                     <td>${ item.create_date }</td>
	                     <td>${ item.category.name }</td>
							
						<td>
	                     
                             <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal1">
							  Restore
							</button>
							
							<!-- Modal -->
							<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title text-dark" id="exampleModalLabel">Modal title</h5>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body text-dark">
							        Bạn chắc chắn?
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							        <a type="button" class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/product/restore?id=${ item.id }">Khôi phục</a>
							      </div>
							    </div>
							  </div>
							</div>
							
	                     </td>
	                     
	                     <td>
	                     	
	                     	<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
							  Delete
							</button>
							
							<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title text-dark" id="exampleModalLabel">Modal title</h5>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body text-dark">
							        Bạn chắc chắn?
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							        <a type="button" class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/product/delete?id=${ item.id }">Xóa</a>
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