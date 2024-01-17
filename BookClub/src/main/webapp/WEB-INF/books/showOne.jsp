<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><c:out value="${ oneBook.title}" /></h1>

	<a href="/books">Go back to shelves</a>

	<p>
		 <c:out value="${oneBook.user.userName}" /> read <c:out value="${ oneBook.title}"/> by <c:out value="${ oneBook.author}" />
	</p>
	<p>Here are <c:out value="${oneBook.user.userName}" /> thoughts:</p>
	
	<p><c:out value="${oneBook.thoughts}"/></p>

</body>
</html>