<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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

	
	
	<div class="container">
		<div class="col-4 mt-4"><h2>Danh sách sản phẩm</h2></div>
		<div class="row mt-5">
			<div class="col-4">
	            <form action="${ pageContext.request.contextPath }/home" method="GET">
	            	<div class="row">
	            		<div class="col-8">
	            			<label for=nameUser>Name</label>
	            			<input class="form-control" type="text" name="productName" value="${ productName }">
	            		</div>
	            		<div class="col-4">
		            		<button class="btn btn-secondary mt-4">Search</button>
		            	</div>
	            	</div>
	            </form>
	        </div>
	        <div class="col-8">
		        <form method="GET"
		            action="${ pageContext.request.contextPath }/home">
		            <div class="row">
		            	<div class="col-3">
		                    <label>Số sản phẩm hiển thị</label> 
		                    <select name="limit"
		                        class="form-control">
		                        <option value="2">2</option>
		                        <option value="5">5</option>
		                        <option value="10">10</option>
		                    </select>
		                </div>
		                <div class="col-3">
		                    <label>Sắp xếp theo</label> <select name="sort_by"
		                        class="form-control">
		                        <option value="id" >Mặc định</option>
		                        <option value="name" >Tên</option>
		                        <option value="price" >Price</option>
		                    </select>
		                </div>
		                <div class="col-3">
		                    <label>Thứ tự</label> <select name="sort_direction"
		                        class="form-control">
		                        <option value="asc" >Tăng dần</option>
		                         <option value="desc">Giảm dần</option>
		                    </select>
		                </div>
		                <div class="col-3">
			                <button class="btn btn-primary mt-4">Lọc</button>
			                <a href="${ pageContext.request.contextPath }/home"
			                    class="btn btn-danger mt-4" type="reset"> Reset </a>
			            </div>
		            </div>
		        </form>
		    </div>
        </div>
		<div class="row">
			<c:forEach var="i" items="${ pageProducts.getContent() }">
				<div class="card col-4 mt-5 ml-3" style="width: 16rem;">
				  <img class="card-img-top mt-1" src="${ i.image }" alt="Card image cap">
				  <div class="card-body">
				    <h5 class="card-title">${ i.name }</h5>
				    <p class="card-text">Price: ${ i.price } $</p>
				    <a href="${ pageContext.request.contextPath }/user/order?id=${ i.id }" class="btn btn-primary">Add Cart</a>
				  </div>
				</div>
			</c:forEach>
		</div>
		<div class="row mt-5 ml-5 mb-5">
	        <div class="col-4"> Total page: ${ pageProducts.totalPages }</div>
	         <div class="col-6">
		          <ul class="pagination">
		              <c:forEach begin="0" end="${ pageProducts.totalPages - 1 }"
		                  varStatus="page">
		                  <li class="page-item"><a
		                      href="${ pageContext.request.contextPath }/home/?page=${page.index}"
		                      class="page-link">${ page.index + 1 } </a></li>
		              </c:forEach>
		          </ul>
		     </div>
        </div>
	</div>

	
	

</body>
</html>