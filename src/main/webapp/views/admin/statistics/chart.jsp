<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/MaterialDesignBoostrap/bootstrap.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/MaterialDesignBoostrap/mdb.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/MaterialDesignBoostrap/style.css">
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/MaterialDesignBoostrap/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/MaterialDesignBoostrap/popper.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/MaterialDesignBoostrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/MaterialDesignBoostrap/mdb.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

</head>
<body>

	
	<div class="row">
				<div class="col-md-5">
				  	<canvas id="horizontalBar" style="display: block; " class="chartjs-render-monitor"></canvas>
				</div>
	</div>
	
	<script type="text/javascript">
		new Chart(document.getElementById("horizontalBar"), {
			"type": "horizontalBar",
			"data": {
			"labels": ${ listProduct },
			"datasets": [{
			"label": "Danh sách sản phầm được mua nhiều",
			"data": ${ listQuantity },
			"fill": false,
			"backgroundColor": ["rgba(255, 99, 132, 0.2)", "rgba(255, 159, 64, 0.2)",
			"rgba(255, 205, 86, 0.2)", "rgba(75, 192, 192, 0.2)", "rgba(54, 162, 235, 0.2)",
			"rgba(153, 102, 255, 0.2)", "rgba(201, 203, 207, 0.2)"
			],
			"borderColor": ["rgb(255, 99, 132)", "rgb(255, 159, 64)", "rgb(255, 205, 86)",
			"rgb(75, 192, 192)", "rgb(54, 162, 235)", "rgb(153, 102, 255)", "rgb(201, 203, 207)"
			],
			"borderWidth": 1
			}]
			},
			"options": {
			"scales": {
			"xAxes": [{
			"ticks": {
			"beginAtZero": true
			}
			}]
			}
			}
			});
	
	</script>
</body>
</html>