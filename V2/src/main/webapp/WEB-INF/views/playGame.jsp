<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Play Game</title>
        <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="<c:url value="/resources/scripts/playGame.js" />"></script>
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
            <h1>Play Game</h1>
            <nav>
                |<a href = "profile">Profile</a>|
                <a href = "addGame">Add Game</a>|
                <a href = "#">Play Game</a>|
                <a href = "editGame">Edit Game</a>|
                <a href = "deleteGame">Delete Game</a>|
                <a href = "compare">Compare</a>|
                <a href = "logout">Logout</a>|
            </nav> 
        </div>
        <form action="updateGameHours" method = "POST">
            <select name="gameName">
			<c:forEach var="game" items="${playListBean }">
                <option value="${game.name}">${game.name} </option>
                
            </c:forEach>
            </select>
            <button type="button" onclick="startTimer()">Start Timer</button>
            <button type="button" onclick="endTimer()">End Timer</button>
            <label style="color: white;" for="Time of session">Time Played this session (in hours):</label>
            <input type="number" name = "gameHours" id = "Time this session" value = 0 readonly>
            <button type="submit">Update Game</button>
        </form>
        <div style = "color: #66fcf1;" id = "note">Note: Timer NOT started. If you update now, your game will NOT be updated!</div>
    </div>
    
    
</body>
</html>