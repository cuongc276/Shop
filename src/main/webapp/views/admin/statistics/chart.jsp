<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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

</head>
<body>

	
	<div class="col-md-5">
	  <canvas id="horizontalBar" style="display: block; width: 431px; height: 215px;" class="chartjs-render-monitor"></canvas>

	</div>
	
	<script type="text/javascript">
		new Chart(document.getElementById("horizontalBar"), {
			"type": "horizontalBar",
			"data": {
			"labels": ["Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Grey"],
			"datasets": [{
			"label": "My First Dataset",
			"data": [22, 33, 55, 12, 86, 23, 14],
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