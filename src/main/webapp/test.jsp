<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/18/25
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdn.jsdelivr.net/npm/mdb-ui-kit@8.2.0/css/mdb.min.css"
            rel="stylesheet"
    />
     <link rel="stylesheet" href="app/theme.min.css"/>
</head>
<body class="container">
<header>
    <!-- Tabs navs -->
    <ul class="nav nav-tabs nav-fill mb-3" id="ex-with-icons" role="tablist">
        <li class="nav-item" role="presentation">
            <a data-mdb-tab-init class="nav-link active" id="ex-with-icons-tab-1" href="#ex-with-icons-tabs-1" role="tab"
               aria-controls="ex-with-icons-tabs-1" aria-selected="true"><i class="fas fa-chart-pie fa-fw me-2"></i>Sales</a>
        </li>
        <li class="nav-item" role="presentation">
            <a data-mdb-tab-init class="nav-link" id="ex-with-icons-tab-2" href="#ex-with-icons-tabs-2" role="tab"
               aria-controls="ex-with-icons-tabs-2" aria-selected="false"><i class="fas fa-chart-line fa-fw me-2"></i>Subscriptions</a>
        </li>
        <li class="nav-item" role="presentation">
            <a data-mdb-tab-init class="nav-link" id="ex-with-icons-tab-3" href="#ex-with-icons-tabs-3" role="tab"
               aria-controls="ex-with-icons-tabs-3" aria-selected="false"><i class="fas fa-cogs fa-fw me-2"></i>Settings</a>
        </li>
    </ul>
    <!-- Tabs navs -->

    <!-- Tabs content -->
    <div class="tab-content" id="ex-with-icons-content">
        <div class="tab-pane fade show active" id="ex-with-icons-tabs-1" role="tabpanel" aria-labelledby="ex-with-icons-tab-1">
            Tab 1 content
        </div>
        <div class="tab-pane fade" id="ex-with-icons-tabs-2" role="tabpanel" aria-labelledby="ex-with-icons-tab-2">
            Tab 2 content
        </div>
        <div class="tab-pane fade" id="ex-with-icons-tabs-3" role="tabpanel" aria-labelledby="ex-with-icons-tab-3">
            Tab 3 content
        </div>
    </div>
    <!-- Tabs content -->
</header>

<script
        type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/mdb-ui-kit@8.2.0/js/mdb.umd.min.js"
></script>
</body>
</html>
