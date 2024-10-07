<%@include file="includes/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="includes/head.html"%>
<script src="js/entities/user.js" type="text/javascript"></script>
    <script src="js/fetch.js" type="text/javascript"></script>
</head>

<body class="container">
<h2 class="row">Hello World!</h2>

<form class="row" method="post" id="userForm" name="userSignUp">
    <input type="text" name="firstName" id="firstName" placeholder="name">
    <input type="text" name="lastName" id="lastName" placeholder="last name">
    <input type="text" name="password" id="password" placeholder="password">
    <input type="text" name="username" id="username" placeholder="username">
    <input type="date" name="dateOfBirth" id="dateOfBirth">
    <button class="btn btn-sm btn-primary" type="submit" name="addUser" id="addUser">Submit</button>
</form>
<button class="btn btn-sm btn-primary" type="button" name="delete" id="delete">Submit</button>
<button class="btn btn-sm btn-primary" type="button" name="getB" id="getB">Submit</button>

<p>Click the link below to view a list of all users in the directory</p>
<a href="searchUser" class="col-sm btn btn-primary">Get All Users</a><br/><br/>

<p>To search for a specific user, enter the user's last name below</p>
<div class="row">
    <form action="searchUser" method="get">
        <input class="col-sm-4" type="text" name="searchTerm" id="searchTerm" placeholder="Enter last name">
        <button type="submit" id="submitButton" class="col-sm mw-50 btn btn-secondary">Search Users</button>
    </form>
</div>
<%@include file="includes/bootstrapScript.js"%>
</body>
</html>