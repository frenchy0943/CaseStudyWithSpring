<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Edit Game</title>
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
            <h1>Edit Game</h1>
            <nav>
                |<a href = "profile">Profile</a>|
                <a href = "addGame">Add Game</a>|
                <a href = "playGame">Play Game</a>|
                <a href = "#">Edit Game</a>| 
                <a href = "deleteGame">Delete Game</a>|
                <a href = "compare">Compare</a>|
                <a href = "logout">Logout</a>|
            </nav>
        </div>
        <form action="editGameDetails" method="POST">
           <select name="gameName">
           <c:forEach var="userGame" items="${editListBean }">
                <option value="${userGame.gameName}">${userGame.gameName} </option>
                
            </c:forEach>
            </select>
            
            <label style="color: white">Select a list</label>
            <select name="currentList">
                <option value="backlog">Backlog</option>
                <option value="current">Current</option>
                <option value="completed">Completed</option>
            </select>
            <input type= "number" name = "gameHours" step = 0.01 placeholder="Hours Played" min = "0">
            <input type= "number" name = "timesCompleted" placeholder="Times Completed" min = "0">
            <button type="submit">Update Game</button>
        </form>
        <div>
    
        </div>
    </div>
</body>
</html>