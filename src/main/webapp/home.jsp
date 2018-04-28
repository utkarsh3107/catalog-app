<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>

<link href="${pageContext.request.contextPath}/resources/css/OM.css"
	rel="stylesheet" />


</head>
<body>

	<div class="container">
		<h3 align="left" class= "boldText padding10" >Hello ${result}</h3>
		<div class="orangeText boldText padding10">Home Page: Product
			Management</div>
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
			<c:forEach items="${productList}" var="product" varStatus="status">
				<div class="alignCenter padding15">
					<div class="floatLeft selectWidth10  blueText">
						<div class="floatLeft selectWidth15"><a href="productEdit/${product.productId}">Edit</a></div>
						<div class="floatRight selectWidth15"><a class="floatRight trashBlack" href="productDelete/${product.productId}"></a></div>
						<div class="clear"></div>
					</div>
					<div class="floatLeft selectWidth45">${product.productId}</div>
					<div class="floatLeft selectWidth15">${product.productName}</div>
					<div class="floatLeft selectWidth15">${product.productDesc}</div>
					<div class="floatLeft selectWidth15">${product.relatedProd}</div>
					<div class="clear"></div>
				</div>
			</c:forEach>
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