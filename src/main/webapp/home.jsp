<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>

<link href="${pageContext.request.contextPath}/resources/css/OM.css"
	rel="stylesheet" />

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tmpl.js"></script>


<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />

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

</script>

<style>
.logoutButton {
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	border-radius: 2px;
	border: none;
	background-image: -moz-linear-gradient(top, #0ba4e0, #067ab4, #064a84);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #0ba4e0),
		color-stop(.5, #067ab4), color-stop(1, #064a84));
	background-image: -ms-linear-gradient(top, #0ba4e0 0, #067ab4 50%, #064a84 100%);
	-webkit-box-shadow: 1px 2px 4px #333;
	-moz-box-shadow: 1px 2px 4px #333;
	box-shadow: 1px 2px 4px #333;
		background: #ff8080;
	text-align: center;
	color: #fff;
	text-shadow: 0 -1px 1px #c0c0c0;
	cursor: pointer;
	font-weight: 100;
	text-decoration: none
	
}
</style>

</head>
<body onload="reloadPage()">



	<div class="container">
		<h3 align="left" class="boldText padding10">Hello ${result}</h3>
		<div class="alignCenter">${msg}</div>
		<div class="orangeText boldText padding10">Home Page: Product
			Management</div>
		<div class = "floatRight">
			<form action="logout" method="get">
				<input type="submit" class="logoutButton" value="Logout"></input>
			</form>
		</div>	
		<div class="headerBarblock">
			<div class="floatLeft paddingLeft10">Product Inventory</div>
			<div class="clear"></div>
		</div>
		<!-- Below class to show LEFT and Right border -->
		<div class="headercontentblock1">
			<div class="alignCenter padding15 greybgContent">
				<div class="floatLeft selectWidth10  blueText">
					<div class="floatLeft selectWidth15">&nbsp;</div>
					<div class="floatRight selectWidth15">&nbsp;</div>
					<div class="clear"></div>
				</div>
				<div class="floatLeft selectWidth45">Product ID</div>
				<div class="floatLeft selectWidth15">Product Name</div>
				<div class="floatLeft selectWidth15">Product Description</div>
				<div class="floatLeft selectWidth15">Related Product</div>
				<div class="clear"></div>
			</div>
			<script id="table-template" type="text/x-jquery-tmpl">
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth10  blueText">
						<div class="floatLeft selectWidth15">
							<a href="productEdit/{{= productId}}">Edit</a>
						</div>
						<div class="floatRight selectWidth15">
							<a href="productDelete/{{= productId}}" class="floatRight trashBlack"></a>
						</div>
						<div class="clear"></div>
					</div>
					<div class="floatLeft selectWidth45">{{= productId}}</div>
					<div class="floatLeft selectWidth15">{{= productName}}</div>
					<div class="floatLeft selectWidth15">{{= productDesc}}</div>
					<div class="floatLeft selectWidth15">{{= relatedProd}}</div>
					<div class="clear"></div>
				</div>	
			</script>
			<div id="ajax-product-results"></div>

			<div class="omblueLine"></div>
			<div class="container1">
				<div class="spacer2"></div>
				<div class="floatLeft selectWidth15">
					<a href="addProduct.jsp" class="blueButton"> Add Product</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- End to show LEFT and Right border -->
		<!-- Below class to show Bottom border with LEFT and RIGHT rounded corners. -->
		<div class="headercontentblock0"></div>
		<!-- End to show Bottom border with LEFT and RIGHT rounded corners. -->
		<div class="spacer2"></div>

	</div>
</body>
</html>