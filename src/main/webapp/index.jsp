<%@include file="/includes/c-tag-lib.jsp" %>
<html lang="en" style="cursor: crosshair;">
<head>
    <%@include file="/includes/head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index_video_container.css"/>
</head>
<body class="mx-auto mt-5 mb-0 bg-dark justify-content-center align-items-center overflow-hidden">
<div class="main_header">
<!-- This div is  intentionally blank. It creates the transparent black overlay over the video which you can modify in the CSS -->
<div class="overlay"></div>
<!-- The HTML5 video element that will create the background video on the header -->
<video playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
    <source src="images/black_paint.mp4" type="video/mp4">
</video>
<!-- The header content -->
<div class="container h-100">
    <div class="d-flex h-100 text-center align-items-center">
        <div class="w-100">
            <h1 class="righteous-regular text-white text-opacity-75 mw-auto" style="font-size: 160px;">Event Visualizer</h1>
            <h2><a class="lead mb-0" href="logIn">Login / Signup </a></h2>
            <h2><a class="lead mb-0" href="search.jsp">Search Users</a></h2>
        </div>
    </div>
</div>
</div>
</body>
</html>
