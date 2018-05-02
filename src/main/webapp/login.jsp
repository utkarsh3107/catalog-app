<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home Page</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />

<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />

<style>
.errorMsg {
	color: #ff0000;
}
.login-form {
	width: 340px;
	margin: 50px auto;
}

.login-form form {
	margin-bottom: 15px;
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.login-form h2 {
	margin: 0 0 15px;
}

.form-control, .btn {
	min-height: 38px;
	border-radius: 2px;
}

.btn {
	font-size: 15px;
	font-weight: bold;
}

.error {
	display: none;
}

.error_show {
	color: red;
}

</style>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
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

<div class="login-form">
	<form id="login" method="post" action="login">
        <h2 class="text-center">Log in</h2>       
        <div class="form-group">        
            <input type="text" id="login_name" name="name" class="form-control" placeholder="Username" />
            <div><span class="error">This field is required</span></div>
        </div>
        <div class="form-group">
            <input id="login_password" type="password" name="password" class="form-control" placeholder="Password">
			<div><span class="error">This field is required</span></div>
        </div>
        <div class="form-group errorMsg">
            <h4 class="text-center alert alert-danger">${errMsg}</h4>     
        </div>  
        <div id="login_submit" class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Log in</button>
        </div>    
    </form>
<!--     <p class="text-center"><a href="#">Create an Account</a></p> -->
</div>
</body>
</html>
