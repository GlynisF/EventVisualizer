<%--
  Created by IntelliJ IDEA.
  User: GCADAGFISHER
  Date: 10/2/2024
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.html"%>
</head>

<%@include file="includes/nav.html"%>

<body class="container">
<h1>Past, Present & Future Events</h1>
<div class="row-cols-sm-2">

    <div class="card col-sm-3">
        <c:forEach items="${notebooks}" var="notebook">
            <div class="accordion" id="notebook-navigation">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="notebook-${notebook.title}">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#notebook-${notebook.id}" aria-expanded="true" aria-controls="notebook-${notebook.id}">
                                ${notebook.title}
                        </button>
                    </h2>
                    <div id="notebook-${notebook.id}" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#notebook-navigation">
                        <div class="accordion-body">
                            <div class="btn-group-sm" role="group" aria-label="edit notebook buttons">
                                <button type="button" class="btn btn-success">Add Event</button>
                                <button type="button" class="btn btn-secondary">Edit Notebook</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="card col-sm-9">
        <p>Placement Text</p>
    </div>

</div>

<%@include file="includes/bootstrapScript.js"%>
${user}
${notebooks}
</body>
</html>