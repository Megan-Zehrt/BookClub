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
	<h1>Add a Book to Your Shelf!</h1>
	
	<a href="/books">Go back to shelves</a>

	<form:form action="/books/process/new" method="post" modelAttribute="book">
	
	<!-- DON'T FORGET HIDDEN INPUT -->
	<form:input type="hidden" path="user" value="${user_id}"/>
		<div class="form-group">
			<p>Title: </p>
			<form:input type="text" path="title" class="form-control"></form:input>
			<form:errors path="title" />
		</div>
		<div class="form-group">
			<p>Author: </p>
			<form:input type="text" path="author" class="form-control"></form:input>
			<form:errors path="author" />
		</div>
		<div class="form-group">
			<p>My Thoughts: </p>
			<form:input type="text" path="thoughts" class="form-control"></form:input>
			<form:errors path="thoughts" />
		</div>
		<input type="submit" value="Add Book" class="btn btn-primary" />
	</form:form>
</body>
</html>