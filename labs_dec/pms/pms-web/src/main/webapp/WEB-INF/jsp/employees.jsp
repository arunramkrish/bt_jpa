<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees List</title>
</head>
<body>
	Employees List
	<table>
		<tr>
			<th>Fist Name</th>
			<th>Last Name</th>
			<th>Department Name</th>
		</tr>
		<c:forEach items="${employees }" var="employee">
			<tr>
				<td>${employee.firstName }</td>
				<td>${employee.lastName }</td>
				<td>${employee.department.name }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>