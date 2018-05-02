<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />

<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />
	
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tmpl.js"></script>
<style>

.display-form {
	width: 1200px;
	margin: 50px auto;
}

</style>


<script>
	
	function reloadPage(){
		$.ajax({
			type : "GET",
			url : "reloadProdHome",
			data: "",
			success : function(data) {
				console.log(JSON.stringify(data));
				jQuery("#table-template").tmpl(data).appendTo("#ajax-product-results");
			}
		});
	}
	
	function reloadPage1(){
		$.ajax({
			type : "GET",
			url : "reloadProdHome",
			data: "",
			success : function(data) {
				console.log(JSON.stringify(data));
				jQuery("#grid-template").tmpl(data).appendTo("#ajax-product-list");
			}
		});
	}

</script>

</head>
<body onload="reloadPage1()">

				<script id="grid-template" type="text/x-jquery-tmpl">
				<tr>
					<td><a href="productEdit/{{= productId}}">Edit</a></td>
					<td><a href="productDelete/{{= productId}}" class="glyphicon glyphicon-remove"></a></td>
					<td>{{= productId}}</td>
					<td>{{= productName}}</td>
					<td>{{= productDesc}}</td>
					<td>{{= relatedProd}}</td>
				</tr>

				</script>

	<div class="display-form">
		<h4 class="text-left font-weight-bold">Hello ${result}</h4>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="row"> 
				<div class="col-md-6">
					<h3 class="text-left font-weight-bold">Home Page: Product Management</h3>
					
				</div>
				<div class="col-md-6">
					<form class="pull-right" action="logout" method="get">
						<input type="submit" class="btn btn-default" value="Logout"></input>
					</form>
				</div>
				</div>
			</div>
			<div>
				<div class="text-center">${msg}</div>
			</div>
			
			<div class="panel-body">

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">Product Inventory</div>

					<!-- Table -->
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th scope="col"></th>
								<th scope="col"></th>
								<th scope="col">Product ID</th>
								<th scope="col">Product Name</th>
								<th scope="col">Product Description</th>
								<th scope="col">Related Product</th>
							</tr>
						</thead>
						<tbody id="ajax-product-list">
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel-footer">			
				<a href="addProduct.jsp" class="btn btn-primary btn-md"> Add Product</a>
			</div>
		</div>
	</div>
</body>
</html>