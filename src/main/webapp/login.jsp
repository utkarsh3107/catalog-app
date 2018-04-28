<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home Page</title>
<link href="${pageContext.request.contextPath}/resources/css/OM.css"
	rel="stylesheet" />

<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />

<style>
.errorMsg {
	color: #ff0000;
}
</style>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script>
		$(document).ready(function() {
				$('#login_name').on('input', function() {
					var input=$(this);
					var is_name=input.val();
					if(is_name){input.removeClass("invalid").addClass("valid");}
					else{input.removeClass("valid").addClass("invalid");}
				});
				
				$('#login_password').on('input', function() {
					var input=$(this);
					var is_name=input.val();
					if(is_name){input.removeClass("invalid").addClass("valid");}
					else{input.removeClass("valid").addClass("invalid");}
				});
		
			$("#login_submit button").click(function(event){
				var form_data=$("#login").serializeArray();
				var error_free=true;
				for (var input in form_data){
					var element=$("#login_"+form_data[input]['name']);
					var valid=element.hasClass("valid");
					var error_element=$("span", element.parent());
					if (!valid){error_element.removeClass("error").addClass("error_show"); error_free=false;}
					else{error_element.removeClass("error_show").addClass("error");}
				}
				if (!error_free){
					event.preventDefault(); 
				}
			});			
		});
	</script>

</head>
<body>



	<form id="login" method="post" action="login">

		<div class="container3">
			<div class="loginForm ">
				<h3 align="center">Login Page</h3>
				<div class="selectWidth95">
					<label for="login_name">Name:</label> <input type="text"
						id="login_name" name="name"></input><br> <span class="error">This
						field is required</span>
				</div>
				<div class="spacer1"></div>
				<div class="selectWidth95">
					<label for="login_password">Password:</label> <input
						type="password" id="login_password" name="password"></input><br>
					<span class="error">This field is required</span>
				</div>
				<div class="errorMsg alignCenter">${errMsg}</div>
				<div id="login_submit" class="container alignCenter">
					<button class="loginButtonblue" type="submit">Submit</button>
				</div>
				
			</div>
		</div>
	</form>

</body>
</html>
