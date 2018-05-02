<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>

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

		$('#add_pname').on('input', function() {
			var input = $(this);
			var is_name = input.val();
			if (is_name) {
				input.removeClass("invalid").addClass("valid");
			} else {
				input.removeClass("valid").addClass("invalid");
			}
		});

		$('#add_pdesc').on('input', function() {
			var input = $(this);
			var is_name = input.val();
			if (is_name) {
				input.removeClass("invalid").addClass("valid");
			} else {
				input.removeClass("valid").addClass("invalid");
			}
		});

		$('#add_relatedproc').on('input', function() {
			var input = $(this);
			var is_name = input.val();
			if (is_name) {
				input.removeClass("invalid").addClass("valid");
			} else {
				input.removeClass("valid").addClass("invalid");
			}
		});

		$("#add_submit button").click(function(event) {
			var form_data = $("#add").serializeArray();
			var error_free = true;
			for ( var input in form_data) {
				var element = $("#add_" + form_data[input]['name']);
				var valid = element.hasClass("valid");
				var error_element = $("span", element.parent());
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
						<h3 class="text-left font-weight-bold">Add New Product:
							Product Management</h3>
					</div>
					<div class="col-md-6">
						<form id="main" class="pull-right" action="reloadHome"
							method="get">
							<input type="submit" class="btn btn-default" value="Home Page"></input>
						</form>
					</div>
				</div>
			</div>
			<form id="add" method="post" action="addProduct">
			<div class="panel-body">
				<div class="bd-example">
					
						<div class="form-group row">
							<label for="add_pname" class="col-sm-2 col-form-label">Product
								Name</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="add_pname"
									name="pname" placeholder="Product Name">
								<div>
									<span class="error">This field is required</span>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label for="add_pdesc" class="col-sm-2 col-form-label">Product
								Description</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="add_pdesc"
									name="pdesc" placeholder="Product Description">
								<div>
									<span class="error">This field is required</span>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label for="add_relatedproc" class="col-sm-2 col-form-label">Related
								Products</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="add_relatedproc"
									name="relatedproc" placeholder="Related Products">
								<div>
									<span class="error">This field is required</span>
								</div>
							</div>
						</div>

					
				</div>
			</div>
			<div id="add_submit" class="panel-footer">
				<button type="submit" class="btn btn-primary btn-md">Add
					Product</button>
			</div>
			</form>
		</div>
	</div>

</body>
</html>