<%@include file="/includes/c-tag-lib.jsp" %>
<html lang="en">
<head>
    <%@include file="/includes/head.jsp" %>
    <link rel="stylesheet" href="css/index_video_container.css"/>
</head>
<body class="container">
<div class="row mt-5">
    <header class="home_header">
        <!-- This div is  intentionally blank. It creates the transparent black overlay over the video which you can modify in the CSS -->
        <div class="overlay"></div>
        <!-- The HTML5 video element that will create the background video on the header -->
        <video playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
            <source src="images/black_paint.mp4" type="video/mp4">
        </video>

        <!-- The header content -->
        <div class="container h-100">
            <div class="d-flex h-100 text-center align-items-center">
                <div class="w-100 text-white">
                    <h1 class="display-3">Event Visualizer</h1>
                    <c:choose>
                        <c:when test="${empty username}">
                            <a href="results">Display All Users</a>
                            <a href="logIn">Log In | Sign Up</a>
                        </c:when>
                        <c:otherwise>
                            <p>Welcome Back, ${username}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

    </header>
</div>

</body>
</div>
</html>

