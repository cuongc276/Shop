<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html >
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

</head>
<body>
	
	<div class="mt-5 col-10 offset-1">
        
        <c:if test="${ not empty sessionScope.status }">
        	<div class="text-danger">${ sessionScope.status }</div>
            <c:remove var="status" scope="session"/>
        </c:if>
        
        <div class="row">
        	<div class="col-4 p-2">
	            <form action="${ pageContext.request.contextPath }/admin/users" method="GET">
	            	<div class="row">
	            		<div class="col-8">
	            			<label for=nameUser>User Name</label>
	            			<input class="form-control" type="text" name="nameUser">
	            		</div>
	            		<div class="col-4">
		            		<button class="btn btn-secondary mt-4">Search</button>
		            	</div>
	            	</div>
	            </form>
	        </div>
	        <div class="col-8 p-2">
		        <form method="GET"
		            action="${ pageContext.request.contextPath }/admin/users">
		            <div class="row">
		                <div class="col-4">
		                    <label>Sắp xếp theo</label> <select name="sort_by"
		                        class="form-control">
		                        <option value="id">Mặc định</option>
		                        <option value="name">Họ Tên</option>
		                        <option value="email">Email</option>
		                        <option value="admin">Tài khoản</option>
		                        <option value="activate">Trạng thái</option>
		                    </select>
		                </div>
		                <div class="col-4">
		                    <label>Thứ tự</label> <select name="sort_direction"
		                        class="form-control">
		                        <option value="asc">Tăng dần</option>
		                        <option value="desc">Giảm dần</option>
		                    </select>
		                </div>
		                <div class="col-4">
			                <button class="btn btn-primary mt-4">Lọc</button>
			                <a href="${ pageContext.request.contextPath }/admin/users"
			                    class="btn btn-danger mt-4" type="reset"> Reset </a>
			            </div>
		            </div>
		        </form>
		    </div>
        </div>
        
        <table class="table table-strip table-dark">
             <thead>
                 <tr>
                     <td>Id</td>
                     <td>Họ tên</td>
                     <td>Email</td>
                     <td>Admin</td>
                     <td colspan="2">Thao tác</td>
                 </tr>
             </thead>
				
             <tbody>
                 <c:forEach var="item" items="${ listUsers.content }">
                 	<tr>
	                     <td>${ item.id }</td>
	                     <td>${ item.name }</td>
	                     <td>${ item.email }</td>
	                     <td>${ item.admin }</td>
	                     
                    	<c:if test="${ item.activate == 1 }">
                    		<td>
		                         <a class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/users/edit?id=${ item.id }">Update</a>     
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
								        <a id="delete" type="button" class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/users/delete?id=${ item.id }">Xóa</a>
								        <a id="restore" type="button" class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/users/soft_delete?id=${ item.id }">Xóa mềm</a>
								      </div>
								    </div>
								  </div>
								</div>
								
		                     </td>
                    	</c:if>
                    	
                    	<c:if test="${ item.activate == 2 }">
                    		<td>
	                             <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal${ item.id }">
								  Restore
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="exampleModal${ item.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
								        <a type="button" class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/users/restore?id=${ item.id }">Khôi phục</a>
								      </div>
								    </div>
								  </div>
								</div>
								
		                     </td>
		                     <td>
	                     	
	                     	<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal1${ item.id }">
							  Delete
							</button>
							
							<!-- Modal -->
							<div class="modal fade" id="exampleModal1${ item.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
							        <a type="button" class="btn btn-danger" href="${ pageContext.request.contextPath }/admin/users/delete?id=${ item.id }">Xóa</a>
							      </div>
							    </div>
							  </div>
							</div>
	                     	
	                     </td>
                    	</c:if>
	                 </tr>
                 </c:forEach>
             </tbody>
         </table>
         <div class="row">
         	<div class="col-4"> Total page: ${ listUsers.totalPages }</div>
	         <div class="col-6">
		          <ul class="pagination">
		              <c:forEach begin="0" end="${ listUsers.totalPages - 1 }"
		                  varStatus="page">
		                  <li class="page-item"><a
		                      href="${ pageContext.request.contextPath }/admin/users/?page=${page.index}"
		                      class="page-link">${ page.index + 1 } </a></li>
		              </c:forEach>
		          </ul>
		     </div>
         </div>
         
    </div>
    
</body>
</html>