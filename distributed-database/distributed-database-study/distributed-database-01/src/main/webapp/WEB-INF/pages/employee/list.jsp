<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
</head>
<body>
	<table>
		<tr>
			<th>id</th>
			<th>lastName</th>
			<th>email</th>
			<th>gender</th>
		</tr>
		<c:forEach items="${requestScope.employees}" var="employee">
			<tr>
				<td>${employee.id }</td>
				<td>${employee.lastName }</td>
				<td>${employee.email }</td>
				<td>${employee.gender }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>