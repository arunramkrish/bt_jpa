<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<title>Employees List</title>
</head>
<body>
	<div class="row">
		<div class="panel panel-default col-md-6 col-sm-4 col-xs-12">
			<div class="panel-heading">Employees List</div>
			<div class="panel-body">
				<p>List of employees</p>
			</div>
			<!-- Table -->
			<div>
				<table class="table">
					<tr>
						<th>Fist Name</th>
						<th>Last Name</th>
						<th>Department Name</th>
					</tr>
					<c:forEach items="${employees }" var="employee">
						<tr>
							<td>${employee.firstName }</td>
							<td>${employee.lastName }</td>
							<td>${employee.department.departmentName }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</body>
</html>