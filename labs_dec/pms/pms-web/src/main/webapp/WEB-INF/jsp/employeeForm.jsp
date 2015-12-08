<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Form</title>
</head>
<body>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="employeeForm">
   <div id="message" class="error,bodytext"> <c:out value="${requestScope.message}" /></div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		bgcolor="#FFFFFF">
		<tbody>
			<tr>
				<td height="26" align="left" background="images/hdr_bg.jpg"
					class="tabletitle">&nbsp;&nbsp;<a href="#" id="item6Tab" onclick="goToHome($('#item6Tab a'))"><img
						src="images/ic_hom.gif" width="20" height="18" border="0"> </a><a
					href="#" id="item6Tab" onclick="goToHome($('#item6Tab a'))">home</a> &gt; employee Form</td>
			</tr>
			<tr>
				<td><form:form name="employeeForm" id = "employeeFormId"
						onsubmit="submitemployee(this);return false;" method="post" commandName="employee">
						<form:hidden path="id" id="id" />
						<table width="100%" height="390" border="0" cellpadding="0"
							cellspacing="8" class="bodytext">
							<tbody>
								<tr>
									<td width="25%" align="left"><form:label path="name"
											cssErrorClass="formFieldError">Name :</form:label></td>
									<td width="40%" align="left"><form:input path="name" id="name" />
									</td>
									<td width="35%" align="left"><form:errors path="name"
											cssClass="formFieldError" />
									</td>
								</tr>
								<tr>
									<td width="25%" align="left"><form:label
											path="description" cssErrorClass="formFieldError">Description :</form:label>
									</td>
									<td width="40%" align="left"><form:input
											path="description" id="description" />
									</td>
									<td width="35%" align="left"><form:errors
											path="description" cssClass="formFieldError" />
									</td>
								</tr>
								<tr>
									<td width="25%" align="left"><form:label path="rate"
											cssErrorClass="formFieldError">Rate :</form:label></td>
									<td width="40%" align="left"><form:input path="rate" id="rate" />
									</td>
									<td width="35%" align="left"><form:errors path="rate"
											cssClass="formFieldError" />
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center"><br> <br> <input
										type="submit" value="Submit" name="btn_submit" id="btn_submit" />
										<input type="button" value="Cancel" name="btn_cancel"
										id="item6Tab"
										onclick="loadManageemployeeForm($('#item6Tab a'));">
										<br>
										<p>&nbsp;</p>
										<p>&nbsp;</p></td>
								</tr>
							</tbody>
						</table>

					</form:form>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>