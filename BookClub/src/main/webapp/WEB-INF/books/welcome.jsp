<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome, ${oneUser.userName}</h1>

	<p>Books from everyone's shelves:</p>

	<a href="/logout">logout</a>
	<a href="/books/new">+ Add to my shelf!</a>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author Name</th>
				<th>Posted By</th>
			</tr>
		</thead>
		<c:forEach items="${AllBook}" var="book">
			<tbody>
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><a href="/books/show/${book.id}"><c:out
								value="${book.title}" /></a></td>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.user.userName }" /></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>