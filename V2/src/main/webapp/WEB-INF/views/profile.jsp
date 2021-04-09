<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Profile</title>
    
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="<c:url value="/resources/css/styling.css" />" rel="stylesheet">

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
            <h1>Profile</h1>
            <nav>
                |<a href = "#">Profile</a>|
                <a href = "addGame">Add Game</a>|
                <a href = "playGame">Play Game</a>|
                <a href = "editGame">Edit Game</a>|
                <a href = "deleteGame">Delete Game</a>|
                <a href = "compare">Compare</a>|
                <a href = "logout">Logout</a>|
            </nav>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Game</th>
                    <th>Hours Played</th>
                    <th>Times Completed</th>
                    <th>Current List</th>
                </tr>
            </thead>
              <tbody>
              <c:forEach var="userGame" items="${profileListBean }">
                <tr>
                    <td>${userGame.gameName}</td>
                    <td>${userGame.gameHours}</td>
                    <td>${userGame.timesCompleted}</td>
                    <td>${userGame.currentList}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>