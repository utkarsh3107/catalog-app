<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
	width: 900px;
	margin: 50px auto;
}

.error {
	display: none;
}

.error_show {
	color: red;
}

</style>
<script>
	$(document).ready(function() {

		$('#update_productName').on('input', function() {
			var input = $(this);
			var is_name = input.val();
			if (is_name) {
				input.removeClass("invalid").addClass("valid");
			} else {
				input.removeClass("valid").addClass("invalid");
			}
		});

		$('#update_productDesc').on('input', function() {
			var input = $(this);
			var is_name = input.val();
			if (is_name) {
				input.removeClass("invalid").addClass("valid");
			} else {
				input.removeClass("valid").addClass("invalid");
			}
		});

		$('#update_relatedProd').on('input', function() {
			var input = $(this);
			var is_name = input.val();
			if (is_name) {
				input.removeClass("invalid").addClass("valid");
			} else {
				input.removeClass("valid").addClass("invalid");
			}
		});

		$("#update_submit button").click(function(event) {
			var form_data = $("#update").serializeArray();
			var error_free = true;
			for ( var input in form_data) {
				var element = $("#update_" + form_data[input]['name']);
				var valid = element.hasClass("valid");
				var error_element = $("span", element.parent());
				if(element.selector == "#update_productId")
					continue;
				if (!valid) {
					error_element.removeClass("error").addClass("error_show");
					error_free = false;
				} else {
					error_element.removeClass("error_show").addClass("error");
				}
			}
			
			if (!error_free) {
				event.preventDefault();
			}
		});
	});
</script>
</head>
<body>

	<div class="display-form">
		<h4 class="text-left font-weight-bold">Hello ${result}</h4>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="row">
					<div class="col-md-6">
						<h3 class="text-left font-weight-bold">Update Product:
							Product Management</h3>
					</div>
					<div class="col-md-6">
						<form class="pull-right" action="/catalog-app/reloadHome"
							method="get">
							<input type="submit" class="btn btn-default" value="Home Page"></input>
						</form>
					</div>
				</div>
			</div>
			<form:form id="update" method="POST" action="/catalog-app/updateProduct">
			<div class="panel-body">
				<div class="bd-example">
					
						<div class="form-group row">
							<label for="productId" class="col-sm-2 col-form-label">Product
								Id</label>
							<div class="col-sm-10">
								<form:hidden path="productId" id="productId" name="productId" cssClass="valid"/>
								<label id="newId" class="col-sm-8 col-form-label"></label>
							</div>
						</div>
						<div class="form-group row">
							<label for="update_productName" class="col-sm-2 col-form-label">Product
								Name</label>
							<div class="col-sm-10">
								<form:input path="productName" id="update_productName" name="productName" cssClass="valid form-control"/>
								<div>
									<span class="error">This field is required</span>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label for="update_productDesc" class="col-sm-2 col-form-label">Product
								Description</label>
							<div class="col-sm-10">
								<form:input path="productDesc"  id="update_productDesc" name="productDesc" cssClass="valid form-control"/>
								<div>
									<span class="error">This field is required</span>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label for="update_relatedProd" class="col-sm-2 col-form-label">Related
								Products</label>
							<div class="col-sm-10">
								<form:input path="relatedProd" id="update_relatedProd" name="relatedProd" cssClass="valid form-control"/>
								<div>
									<span class="error">This field is required</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="update_submit" class="panel-footer">
					<button type="submit" class="btn btn-primary btn-md">Update Product</button>
				</div>
			</form:form>
			</div>
		
		</div>

<%-- 	<form:form method="POST" action="/catalog-app/updateProduct">
		<table>
			<tr>
				<td></td>
				<td><form:input path="productId" /></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><form:input path="productName" /></td>
			</tr>
			<tr>
				<td>Description :</td>
				<td><form:input path="productDesc" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Edit Save" /></td>
			</tr>
		</table>

	</form:form> --%>
	<script>
	$( document ).ready(function() {
		var value = $("#productId").val();
		$("#newId").text(value);
	});
	</script>
</body>
</html>