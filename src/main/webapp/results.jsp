<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
   <%@include file="includes/head.jsp"%>
</head>
<body class="container">
<table id="userTable" class="mdl-data-table" style="width:100%;">
    <thead>
    <tr>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Username</th>
        <th scope="col">Email</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="user" items="${users}">
<tr>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.username}</td>
    <td>${user.email}</td>
</tr>
</c:forEach>
    </tbody>
</table>

<script>
    new DataTable('#userTable');
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
