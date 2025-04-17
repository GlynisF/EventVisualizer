<%@include file="/includes/c-tag-lib.jsp" %>
<html lang="en" style="cursor: crosshair;">
<head>
    <%@include file="/includes/head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index_video_container.css"/>
</head>
<body class="mx-auto mt-5 mb-0 bg-dark justify-content-center align-items-center overflow-hidden"
      data-mdb-theme="light">
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
            <h2><a class="btn btn-link mb-0" href="logIn">Login / Signup </a></h2>
            <h2><a class="lead mb-0" href="search.jsp">Search Users</a></h2>
        </div>
    </div>
</div>
</div>
<script>

    const fetchCall = async () => {
        const requestBody = JSON.stringify({
            dateOfEvent: "2025-01-01",
            startTime: "21:00:00",
            endTime: "02:00:00",
            description: "Out of town headliners with  support."
        });

    try {
        const response = await fetch("http://localhost:8080/eventvisualizer/app/detail/add/15", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json, text/plain, text/html'
            },
            body: requestBody
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.statusText} ${Response.error()} `);
        }

        const data = await response.text(); // Change from .json() to .text() for debugging
        console.log("Response Data:", data);
        return data;
    } catch (error) {
        console.error("Error:", error);
    }
    };

    // Call the function (for testing)
   // fetchCall();
    /*const fetchCall = async () => {
        try {
            const response = await fetch("http://localhost:8080/eventvisualizer/app/detail/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json, text/html, text/plain",
                },
                body: JSON.stringify({
                        dateOfEvent: "2025-01-01",
                        startTime: "21:00:00",
                        endTime: "02:00:00",
                        description: "Sounds from the underground"
                })
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.statusText} ${Response.error()} `);
            }

            const data = await response.json();
            console.log("Response Data:", data);
            return data;
        } catch (error) {
            console.error("Error:", error);
        }
    };

    // Call the function (for testing)
    fetchCall();*/




</script>
</body>
</html>