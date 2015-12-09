<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Form</title>

<link href="resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<h2>Employee Registration Form</h2>
		<div class="row error bodytext">
			<c:out value="${requestScope.message}" />
		</div>
		<form:form name="employeeForm" id="employeeForm" method="post"
			commandName="employee" action="employees">
			<form:hidden path="id" id="id" />
			<div class="row">
				<div class="form-group col-md-4 col-sm-6 col-xs-8">
					<label for="firstName">First Name</label>
					<form:input type="text" class="form-control" path="firstName"
						placeholder="First Name"></form:input>
				</div>
			</div>
			<div class="row">
				<div class="form-group  col-md-4 col-sm-6 col-xs-8">
					<label for="lastName">Last Name</label>
					<form:input type="text" class="form-control" path="lastName"
						placeholder="Last Name"></form:input>
				</div>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</body>
</html>