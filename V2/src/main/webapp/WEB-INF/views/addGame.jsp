<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Add Game</title>
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
            <h1>Add Game to Profile</h1>
            <nav>
                |<a href = "profile">Profile</a>|
                <a href = "#">Add Game</a>|
                <a href = "playGame">Play Game</a>|
                <a href = "editGame">Edit Game</a>|
                <a href = "deleteGame">Delete Game</a>|
                <a href = "compare">Compare</a>|
                <a href = "logout">Logout</a>|
            </nav>
        </div>
        <form action="addNewGame" method="POST">
            <input type="text" name="gameName" placeholder="Name of Game" required = "required"/>
            <input type= "number" name = "gameHours" step = 0.01 placeholder="Hours Played" required = "required" min = "0"/>
            <input type= "number" name = "timesCompleted" placeholder="Times Completed" required = "required" min = "0"/>
            <label style="color: white">Select a list</label>
             <select name="currentList">
                <option value="backlog">Backlog</option>
                <option value="current">Current</option>
                <option value="completed">Completed</option>
            </select>
            <button type="submit" value="ok" name="save">Add Game</button>
        </form>
        
        <div style = "color: #66fcf1">Note: Games are added to our database as users add them to their profile. Please make sure your game is spelled correctly before adding to your profile to get full functionality out of the application.</div>
        
        <table>
            <thead>
                <tr>
                    <th>Games Currently in Database</th>
                    
                </tr>
            </thead>
                <tbody>
                <c:forEach var="game" items="${gameListBean }">
               		<tr>
                    	<td>${game.name}</td>
                	</tr>
                </c:forEach>
                </tbody>
        </table>

    </div>
</body>
</html>