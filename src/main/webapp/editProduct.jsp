<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/resources/css/OM.css"
	rel="stylesheet" />

<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

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
	<div class="container">
		<div class="orangeText boldText padding10">Update Product: Product
			Management</div>
		<div class="headerBarblock">
			<div class="floatLeft paddingLeft10">Added Items</div>
			<div class="clear"></div>
		</div>
		<div class="headercontentblock1">
			<form:form id="update" method="POST" action="/catalog-app/updateProduct">
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth20  blueText">
						<label class="selectWidth70" for="productId">Product Id</label>
					</div>
					<div class="floatLeft selectWidth30">
						<form:hidden path="productId" id="productId" name="productId" cssClass="valid"/>
						<label id="newId"></label>
					</div>
					<div class="clear"></div>
				</div>
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth20  blueText">
						<label class="selectWidth70" for="update_productName">Product Name</label>
					</div>
					<div class="floatLeft selectWidth15">
						<form:input path="productName" id="update_productName" name="productName" cssClass="valid"/> <span
							class="floatLeft error">This field is required</span>
					</div>
					<div class="clear"></div>
				</div>
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth20  blueText">
						<label class="selectWidth70" for="update_productDesc">Product
							Description</label>
					</div>
					<div class="floatLeft selectWidth15">
						<form:input path="productDesc"  id="update_productDesc" name="productDesc" cssClass="valid"/> <span
							class="floatLeft error">This field is required</span>
					</div>
					<div class="clear"></div>
				</div>
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth20 blueText">
						<label class="selectWidth70" for="update_relatedProd">Related
							Products</label>
					</div>
					<div class="floatLeft selectWidth15">
						<form:input path="relatedProd"  id="update_relatedProd" name="relatedProd" cssClass="valid"/>
						<span class="floatLeft error">This field is required</span>
					</div>
					<div class="clear"></div>
				</div>

				<div class="clear"></div>
				<div class="omblueLine"></div>
				<div class="container1">
					<div class="spacer2"></div>
					<div id="update_submit" class="floatLeft selectWidth15">
						<button type="submit" class="blueButton">Update Product</button>
					</div>
					<div class="clear"></div>
				</div>
			</form:form>
		</div>

		<div class="headercontentblock0"></div>

		<div class="spacer2"></div>
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