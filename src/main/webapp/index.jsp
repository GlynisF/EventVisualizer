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
<%@include file="includes/bootstrapScript.js"%>
</body>
</html>