<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/6/25
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="/includes/head.jsp"%>
</head>
<body class="container-sm">
<%@include file="/includes/nav.jsp"%>
<div class="bg-dark">
    <div class="display-3">${sessionScope.welcomeMessage}</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<!-- MDB -->
<script>
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/8.2.0/mdb.umd.min.js"
</script>
</body>
</html>
