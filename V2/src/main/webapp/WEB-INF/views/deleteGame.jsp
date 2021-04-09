<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Delete Game</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style = "background-color: #1f2833;">

<%
	if(session.getAttribute("userName") == null){
		response.sendRedirect("errorPage");
		session.setAttribute("error", "You are not logged in");
	}
%>

    <div class = "container" style="background-color: #0b0c10; ">
        <div class = "jumbotron text-center" style ="background-color:#66fcf1">
            <h1>Delete Game</h1>
            <nav>
                |<a href = "profile">Profile</a>|
                <a href = "addGame">Add Game</a>|
                <a href = "playGame">Play Game</a>|
                <a href = "editGame">Edit Game</a>|
                <a href = "#">Delete Game</a>| 
                <a href = "compare">Compare</a>|
                <a href = "logout">Logout</a>|
            </nav>
        </div>
        <form action="removeGame" method="POST">
           <select name="gameName">
           <c:forEach var="userGame" items="${deleteListBean }">
                <option value="${userGame.gameName}">${userGame.gameName} </option>
                
            </c:forEach>
            </select>
            
            <button type="submit">Delete Game</button>
        </form>
        <div>
    
        </div>
    </div>
</body>
</html>