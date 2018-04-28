<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>

<link href="${pageContext.request.contextPath}/resources/css/OM.css"
	rel="stylesheet" />

<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

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
	
/* 		$('#ajaxSubmit').click(function(){
			var form = $('#add');
			alert("In");
			
			 $.ajax({
		           type: form.attr('method'),
		           url: form.attr('action'),
		           data: form.serialize(), // serializes the form's elements.
		           success: function(data)
		           {
		        	   
		               alert(data); // show response from the php script.
		           }
		         });
		}); */
	
	});
</script>
<style>
.homeButton {
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border-radius: 3px;
	border: none;
	background-image: -moz-linear-gradient(top, #0ba4e0, #067ab4, #064a84);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #0ba4e0),
		color-stop(.5, #067ab4), color-stop(1, #064a84));
	background-image: -ms-linear-gradient(top, #0ba4e0 0, #067ab4 50%, #064a84 100%);
	-webkit-box-shadow: 1px 2px 4px #333;
	-moz-box-shadow: 1px 2px 4px #333;
	box-shadow: 1px 2px 4px #333;
	background: blue;
	text-align: center;
	color: #fff;
	text-shadow: 0 -1px 1px #c0c0c0;
	cursor: pointer;
	font-weight: 100;
	text-decoration: none
}
</style>

</head>
<body>
	<div class="container">
		<div class="orangeText boldText padding10">Add New Product:
			Product Management</div>
		<div class="headerBarblock">
			<div class="floatLeft paddingLeft10">Added Items</div>
			<div class="floatRight">
				<form id="main" method="get" action="reloadHome">
					<input type="submit" name="submit" class="homeButton" value="Home Page"/>
				</form>
			</div>
			<div class="clear"></div>
		</div>
		<div class="headercontentblock1">
			<form id="add" method="post" action="addProduct">
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth20  blueText">
						<label class="selectWidth70" for="add_pname">Product Name</label>
					</div>
					<div class="floatLeft selectWidth15">
						<input type="text" id="add_pname" name="pname"></input> <span
							class="floatLeft error">This field is required</span>
					</div>
					<div class="clear"></div>
				</div>
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth20  blueText">
						<label class="selectWidth70" for="add_pdesc">Product
							Description</label>
					</div>
					<div class="floatLeft selectWidth15">
						<input type="text" id="add_pdesc" name="pdesc"></input> <span
							class="floatLeft error">This field is required</span>
					</div>
					<div class="clear"></div>
				</div>
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth20 blueText">
						<label class="selectWidth70" for="add_relatedproc">Related
							Products</label>
					</div>
					<div class="floatLeft selectWidth15">
						<input type="text" id="add_relatedproc" name="relatedproc"></input>
						<span class="floatLeft error">This field is required</span>
					</div>
					<div class="clear"></div>
				</div>

				<div class="clear"></div>
				<div class="omblueLine"></div>
				<div class="container1">
					<div class="spacer2"></div>
					<div id="add_submit" class="floatLeft selectWidth15">
						<button type="submit" class="blueButton">Add Product</button>
						<!-- <button type="button" class="blueButton" id="ajaxSubmit">Ajax Add Product</button> -->
					</div>
					<div class="clear"></div>
				</div>
			</form>
		</div>

		<div class="headercontentblock0"></div>

		<div class="spacer2"></div>
	</div>


</body>
</html>