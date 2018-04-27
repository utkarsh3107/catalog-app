<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home Page</title>
</head>
<body>
	<h2>Hello ${result}</h2>

	<table width="50%">
		<tr>
			<th>Product ID</th>
			<th>Product Name</th>
			<th>Product Description</th>
			<th>Help Products</th>
		</tr>
		<c:forEach items="${productList}" var="product" varStatus="status">
			<tr>
				<td>${product.productId}</td>
				<td>${product.productName}</td>
				<td>${product.productDesc}</td>
				<td>${product.relatedProd}</td>
				<td><a href="productDelete/${product.productId}">Delete</a></td>
            	<td><a href="productEdit/${product.productId}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href = "addProduct.jsp" > Add Product</a>
</body>
</html>